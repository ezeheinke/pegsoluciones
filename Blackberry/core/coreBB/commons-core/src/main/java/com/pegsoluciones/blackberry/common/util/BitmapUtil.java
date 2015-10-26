package com.pegsoluciones.blackberry.common.util;

import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.component.Dialog;

import com.pegsoluciones.blackberry.common.ui.ScreenInfo;
import com.pegsoluciones.blackberry.common.web.ImagesDownloader;

public abstract class BitmapUtil {

    public static Bitmap getImagen(String nombre){
    	Bitmap b = Bitmap.getBitmapResource(ScreenInfo.getResDir() + nombre);
    	return b;    
    }	
    
	public static Point getSizePara(int w, int h, int wContenedor,int hContenedor) {
		
		double proporcionW = (double)wContenedor / (double)w;		
		double proporcionH = (double)hContenedor / (double)h;
		
		if(proporcionW < proporcionH)
			return new Point(proporcionW*(double)w,proporcionW*(double)h);
		return new Point(proporcionH*(double)w,proporcionH*(double)h);
	}
    
    /**
     * @param ruta
     * @return retorna un Bitmap desde la ruta pasada por parametro
     * desde la carpeta "curve", "storm", "bold" o "pearl"
     * segun en el equipo que se este corriendo la app
     */
    public static Bitmap getImagenParaEsteEquipo(String ruta){
    	Bitmap b = Bitmap.getBitmapResource(ScreenInfo.getResDir() 
    			+ PosicionesUtil.UbicarEn("curve/", "storm/", "bold/", "pearl/")
    			+ ruta);
    	return b;    
    }	    
	
	public static Bitmap resize(String path,int w,int h){

		FileConnection file = null;
		InputStream in = null;
		try
		{
    		String filePath = path.startsWith("file:///") ? path : ("file:///" + path);
    		file = (FileConnection) Connector.open( filePath );

	    	if (!file.exists()) {
	            return null;
	        }
	        
	    	in = file.openInputStream();
	        int len = 0;
	    	len = (int) file.fileSize();
	    	
	        byte[] buffer = new byte[ len ];
	    	in.read( buffer, 0, len );
	
	    	EncodedImage encodedImage = EncodedImage.createEncodedImage(buffer, 0, len);
	    	encodedImage = ImagesDownloader.scale(encodedImage,w,h);
	    	return encodedImage.getBitmap();
		}
		catch(Throwable e){
			Dialog.inform("Error al enviar foto: " + e.getMessage());
			e.printStackTrace();}
		finally
		{
			try
			{
				if(in != null) in.close();
				if(file != null) file.close();
			}
			catch(Throwable e) {e.printStackTrace();} ;
		}
		return null;
	  }    
    
}
