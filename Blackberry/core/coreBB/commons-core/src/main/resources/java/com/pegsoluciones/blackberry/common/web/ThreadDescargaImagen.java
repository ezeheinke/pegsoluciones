package com.pegsoluciones.blackberry.common.web;

import net.rim.device.api.system.Bitmap;

public class ThreadDescargaImagen implements Runnable {

	private String url;
	private EsperaImagenInterface esperaImagenImplemetador;
	private int w;
	private int h;
	private boolean conTamanio = false;
	
	public ThreadDescargaImagen(String url,EsperaImagenInterface esperaImagenImplemetador) {	
		this.url = url;
		this.esperaImagenImplemetador = esperaImagenImplemetador;
	}
	
	public ThreadDescargaImagen(String url,int w, int h, EsperaImagenInterface esperaImagenImplemetador) {	
		this.url = url;
		this.esperaImagenImplemetador = esperaImagenImplemetador;
		this.w = w;
		this.h = h;
		this.conTamanio = true;
	}

	public void run(){
		Bitmap b;
		if (conTamanio)
			b = DescargaImagen.descargarImagen(url,w,h);
		else
			b = DescargaImagen.descargarImagen(url);
		
		// Despues que se descargo la imagen se la paso al objeto que me la pidip
		esperaImagenImplemetador.catchImagen(b, url);
	}

}
