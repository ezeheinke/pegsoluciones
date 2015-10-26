package com.pegsoluciones.blackberry.common.ui.field;


import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;

import com.pegsoluciones.blackberry.common.web.DescargaImagenesManager;
import com.pegsoluciones.blackberry.common.web.EsperaImagenInterface;

/**
 * @author poza pablo
 * 
 * Bitmap field que le pasas una url de la imagen que va a mostrar la descarga
 * asincronicamente
 *
 */
public class ExBitmapField extends BitmapField implements EsperaImagenInterface{
	
	
	/**
	 * @param b
	 * @param url
	 */
	public ExBitmapField(Bitmap b,String url){
		this(b,url,FOCUSABLE);
	}

	/**
	 * @param url
	 */
	public ExBitmapField(String url){
		super();
		DescargaImagenesManager.descargarImagenThread(url, this);
	}
	
	/**
	 * @param b
	 * @param url
	 * @param flags
	 */
	public ExBitmapField(Bitmap b, String url, long flags) {
		super(b,flags);
		DescargaImagenesManager.descargarImagenThread(url, this,0);
	}
	
	/**
	 * @param b imagen que mostrar mientras descarga la imagen
	 * @param url de la imagen
	 * @param flags del fiel
	 * @param ancho que va a tener la imagen descargada 
	 * @param alto que va a tener la imagen descargada 
	 */
	public ExBitmapField(Bitmap b, String url, long flags,int ancho, int alto) {
		super(b,flags);		
		DescargaImagenesManager.descargarImagenThread(url,ancho,alto, this);		
	}

	/**
	 * @param url de la imagen que va a mostrar
	 * @param flags del field
	 */
	public ExBitmapField(String url, long flags) {
		this(new Bitmap(0,0),url, flags);
	}


	public void catchImagen(Bitmap b, String ruta) {
		if(b != null)
			synchronized(UiApplication.getUiApplication().getAppEventLock()){
				this.setBitmap(b);
		}
		
	}

}
