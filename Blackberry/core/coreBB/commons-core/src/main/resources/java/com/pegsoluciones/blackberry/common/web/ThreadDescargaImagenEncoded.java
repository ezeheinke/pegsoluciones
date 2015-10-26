package com.pegsoluciones.blackberry.common.web;

import net.rim.device.api.system.EncodedImage;

public class ThreadDescargaImagenEncoded implements Runnable {

	private String url;
	private EsperaImagenEncodedInterface esperaImagenImplemetador;
	private int w;
	private int h;
	private boolean conTamanio = false;
	
	public ThreadDescargaImagenEncoded(String url,EsperaImagenEncodedInterface esperaImagenImplemetador) {	
		this.url = url;
		this.esperaImagenImplemetador = esperaImagenImplemetador;
	}
	
	public ThreadDescargaImagenEncoded(String url,int w, int h, EsperaImagenEncodedInterface esperaImagenImplemetador) {	
		this.url = url;
		this.esperaImagenImplemetador = esperaImagenImplemetador;
		this.w = w;
		this.h = h;
		this.conTamanio = true;
	}

	public void run(){
			EncodedImage image = DescargaImagen.descargarEncodedImagen(url);
		
		// Despues que se descargo la imagen se la paso al objeto que me la pidip
		esperaImagenImplemetador.catchImagen(image, url);
	}

}
