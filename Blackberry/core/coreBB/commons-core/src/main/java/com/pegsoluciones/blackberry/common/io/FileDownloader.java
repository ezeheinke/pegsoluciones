package com.pegsoluciones.blackberry.common.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.file.FileConnection;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Status;

import com.pegsoluciones.blackberry.common.device.StringConexionUtil;

public class FileDownloader  {

    public static interface Listener {
        public void downloadComplete( String url, String localName );
        public void downloadFailed  ( String url, String localName, String message );
    } 
    
    public FileDownloader() {
    }
    
        
    synchronized private FileConnection createTmpFile( String localName ) throws IOException {

        int i = 0;
        String tmpFileName = localName + "." + i + ".tmp";
        FileConnection tmpFile = (FileConnection) Connector.open( "file:///" + tmpFileName );

        while ( tmpFile.exists() ) {
            tmpFile.close();
            i++;
            tmpFileName = localName + "." + i + ".tmp";
            tmpFile = (FileConnection) Connector.open( "file:///" + tmpFileName );            
        }
        tmpFile.create();        
        tmpFile.setWritable(true);
        return tmpFile;        
    
    }
    
    


    public void download( final String url, final String localName ) {
           
    	HttpConnection conn = null;
	   FileConnection file = null;
	   InputStream is = null;
	   FileConnection tmpFile = null;
	   
	   try
	   {
            String cnx = StringConexionUtil.urlConexion( url );
                         
            // Conecta para descargar el archivo.
            conn = (HttpConnection) Connector.open(  cnx, Connector.READ_WRITE, true );
            int rc = conn.getResponseCode();
            if ( rc != HttpConnection.HTTP_OK ) {
                throw new RuntimeException( "Error del server: " + rc );
            }

            // Abre Archivo temporal para escritura
            tmpFile = createTmpFile( localName);
            OutputStream os = tmpFile.openOutputStream(); 

            // Descarga el archivo.
            is = conn.openInputStream();
            int read, fileSize = 0;
            byte[] buffer = new byte[ 4 * 1024 ];
            while ( (read = is.read( buffer )) > 0 ) {

                os.write( buffer, 0, read );
                fileSize += read;
            }
            // Cierra la conexi�n.
            is.close();
            conn.close();
            is = null;
            conn = null; 

            // Cierra el archivo.
            os.flush();
            os.close();
            
            // Si existe el archivo anterior lo elimina.
            file = (FileConnection) Connector.open( "file:///" + localName );
            String renName = file.getName();
            if ( file.exists() ) {
                file.delete();
            }
            file.close();
            
            // renombra el archivo temporal como el archivo resultante
            tmpFile.rename( renName );
            tmpFile.close();
	   }
	   catch(Throwable e){ 
		   Status.show("Server error try later");
	       while(true)
	    	   UiApplication.getUiApplication().getActiveScreen().close();
	   }
	   finally
	   { 
		   try
		   {
			   if( is != null) is.close();
			   if( file != null) file.close();
			   if( tmpFile != null) tmpFile.close();
			   if( conn != null) conn.close();
		   }
		   catch(Throwable e){e.printStackTrace();}
	   }
    }


    public void downloadAsync( String url, String localName, Listener listener ) {

        final String furl          = url;
        final String flocalName    = localName;
        final Listener flistener     = listener;

        Thread th = new Thread( ) {
            public void run() {
            
                download( furl, flocalName );

                try {
                    flistener.downloadComplete( furl, flocalName );
                }
                catch ( Exception ex ) {
                    // Si hacen algo mal en el callback no queremos que nos de downloadFailed.
                }    
            
            }
        };
        try {
            th.start();
        }
        catch( Exception ex ) {
            if ( flistener != null ) {
                flistener.downloadFailed( furl, flocalName, ex.toString() );
            }            
        }
    }
}

