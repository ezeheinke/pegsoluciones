package com.pegsoluciones.blackberry.common.io;

import javax.microedition.io.file.*;
import javax.microedition.io.*;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;


import java.io.*;
import java.util.Enumeration;

public class IOUtils {

    public final static String STORE_HOME = "SDCard/BlackBerry/";
    

    private static String _wsUrl = null;
    private static String _imgUrl = null;
    private static String _fullDir = null;
    private static String _nombreApp = null;
    
    
 
    
    public static String getNombreApp() {
		return _nombreApp;
	}
	public static void setNombreApp(String nombreApp) {
		IOUtils._nombreApp = nombreApp;
	}

	public static void setWsUrl( String url ) {
    _wsUrl = url;
	}
	
	public static String getWsUrl(  ) {
	    return _wsUrl;
	}
	
	public static void setImgUrl( String url ) {
	    _imgUrl = url;
	}
	public static String getImgUrl(  ) {
	    return _imgUrl;
	}
	
	
	public static String getDir( ) {
	    return _fullDir + "/";
	}
    
    public static void createDir( String dirName ) {
    	
    	FileConnection fc = null;
    	try
    	{
	        //  : si termina con "/" sacarla.
	    	_fullDir = dirName;
	        
	        // Se fija si ya existe un archivo con ese nombre y lo borra.
	        fc = (FileConnection) Connector.open( "file:///" + dirName );
	        if ( fc.exists() && !fc.isDirectory() ) {
	            fc.delete();
	        }
	        fc.close();
	        
	        // Crea el directorio para la aplicación.
	        fc = (FileConnection) Connector.open( "file:///" + dirName + "/" );
	        if ( !fc.exists() ) {
	            fc.mkdir();
	        }
	        fc.close();
    	}
    	catch(Throwable e)
    	{
    		System.out.println("Error al intentar crear el directorio " + dirName); 
    	}
    	finally{ if(fc != null)
			try 
    		{
				fc.close();
			} 
    		catch (IOException e) {
				e.printStackTrace();
			}}
    }


    public static void deleteDir(  ) {

    	FileConnection fc = null;
    	
        try {
            // Se fija si ya existe un archivo con ese nombre y lo borra.
            fc = (FileConnection) Connector.open( "file:///" + getDir() );
            if ( fc.exists() && fc.isDirectory() ) {

                Enumeration files = fc.list( "*", true );
                fc.close();
            
                while(files.hasMoreElements())  {
                    
                    String file = (String) files.nextElement();
    
                    try {
                    
                        FileConnection subFC = (FileConnection)Connector.open( "file:///" + getDir() + file);
                        subFC.delete();
                        subFC.close();
                    }
                    catch( Exception ex ) {
                        System.out.println( "No se pudo eliminar archivo. " + ex.toString() );
                    }
                }
            }
        }
        catch(Throwable e)
        { 
        	System.out.println("Error al intentar eliminar el directorio de la aplicación"); 
        }
    	finally
    	{ 
    		if(fc != null)
			try 
    		{
				fc.close();
			} 
    		catch (IOException e) {	e.printStackTrace(); }
    	}
    }
    

    public static void createAppDirOrDie( String relativeDir ) {
    
        try {
            
            String fullDir = STORE_HOME + relativeDir;
            
            createDir( fullDir );
            
            // Ok, se generó el dir de la aplicación.
            _fullDir = fullDir;
            

        }
        catch( Exception ex ) {
            
            final String msg = "No se puede iniciar.Por favor, revise en la configuración de su BlackBerry "
            		+ "que la aplicación tenga suficientes permisos de Conexión e interacción.";          
            
            UiApplication.getUiApplication().invokeLater( new Runnable() {
                public void run() {
                	Dialog.alert(msg);
                }
            } );
            
            UiApplication.getUiApplication().invokeLater( new Runnable() {
                public void run() {
                    try {
                        Thread.sleep( 5000 );
                    }
                    catch ( Exception ex ) {
                    } 
                    //endApplication();
                    System.exit( 0 );
                }
            } );
            return;
        }

    }
    

} 

