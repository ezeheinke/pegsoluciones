package com.pegsoluciones.blackberry.common.web;

import com.pegsoluciones.blackberry.common.concurrent.ThreadPool;

/**
 * @author Pablo Poza
 * 
 * 
 *
 */
public class DescargaImagenesManager {

	private static final int FIXED_THREADS_SIZE = 4;
	
	/** Thread pool shared by all services */
	private static final ThreadPool threadPool = new ThreadPool(FIXED_THREADS_SIZE);

	public static void descargarImagenThread(String url, int w, int h ,EsperaImagenInterface esperaImagenImplemetador){
		
		threadPool.assign(new ThreadDescargaImagen(url,w,h,esperaImagenImplemetador));
	}
	
	public static void descargarImagenThread(String url, EsperaImagenInterface esperaImagenImplemetador){
		threadPool.assign(new ThreadDescargaImagen(url,esperaImagenImplemetador));
	}
	
	public static void descargarImagenThread(String url, EsperaImagenInterface esperaImagenImplemetador,int index){
		threadPool.assign(new ThreadDescargaImagen(url,esperaImagenImplemetador));
	}
	
	public static void descargarImagenThreadEncoded(String url, EsperaImagenEncodedInterface esperaImagenImplemetador){
		threadPool.assign(new ThreadDescargaImagenEncoded(url,esperaImagenImplemetador));
	}
}
