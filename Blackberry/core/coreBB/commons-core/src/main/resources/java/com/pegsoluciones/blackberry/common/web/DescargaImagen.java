package com.pegsoluciones.blackberry.common.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import net.rim.device.api.io.IOUtilities;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;

import com.pegsoluciones.blackberry.common.device.StringConexionUtil;

/**
 * 
 */
public class DescargaImagen {


	public static Bitmap descargarImagen(String url, int w, int h) {

		EncodedImage img = descargarEncodedImagen(url);
		
		if(img	!= null){
			Bitmap bmp = ImagesDownloader.scale(img, w, h).getBitmap();
			return bmp;
		}
		else{
			return null;
		}
	}       


	public static Bitmap descargarImagen(String url) {

		EncodedImage descargarEncodedImagen = descargarEncodedImagen(url);
		if(descargarEncodedImagen == null)
			return null;
		Bitmap bmp = descargarEncodedImagen.getBitmap();
 	    return bmp;
	}
    
   
   public static EncodedImage descargarEncodedImagen(String url) {

		HttpConnection httpConnection = null;
		InputStream httpInput = null;
		EncodedImage img = null;

		try {

			httpConnection = get(url);
			if(httpConnection == null)
				return null;
			httpInput = httpConnection.openInputStream();
			byte[] b = IOUtilities.streamToBytes(httpInput);
			img = EncodedImage.createEncodedImage(b, 0, b.length);
			return img;
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println(e.toString());
			return null;
		} finally {
			if (httpInput != null)
				try {
					httpInput.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			if (httpConnection != null)
				try {
					httpConnection.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					}
		}
		
	}
   
   public static HttpConnection get(String url) throws IOException,InterruptedIOException{
   	
   	String urlFinal = StringConexionUtil.urlConexion(url) + ";ConnectionTimeout=60000";
   	HttpConnection conn = (HttpConnection) Connector.open( urlFinal);
        
   	int rc = conn.getResponseCode();
       if ( rc != HttpConnection.HTTP_OK ) {
           return null;
       }
       
   	return conn;
   }
   
   
      
   
}