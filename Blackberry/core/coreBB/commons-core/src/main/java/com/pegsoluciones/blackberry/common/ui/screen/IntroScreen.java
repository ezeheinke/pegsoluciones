package com.pegsoluciones.blackberry.common.ui.screen;


import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.container.FullScreen;

import com.pegsoluciones.blackberry.common.exception.CatcherObjectInterface;
import com.pegsoluciones.blackberry.common.exception.UtilExceptions;



/**
 * @author poza pablo
 * 
 * tiene el implementado el comportamiento normal de una pantalla de intro
 * solo debe implementar cuando no se quiere mostras mas la intro
 *
 */
public abstract class IntroScreen extends FullScreen implements CatcherObjectInterface {

	
	Thread thread;
	int time;
	Field field;
	Screen scr,thisScreen;	
	

	
	/**
	 * 
	 * Retorna una pantalla que estara abierta por time segundos con field
	 * como hijo, cuando se cierra pushea a scr
	 * 
	 * @param time
	 * @param field
	 * @param scr
	 */
	public IntroScreen(final int time, Field field) {
		super(DEFAULT_CLOSE);
		this.time = time;
		this.field = field;
		thisScreen=this.getScreen();
		add(field);
		
		new Thread(){
			public void run() {
				try {										
					Thread.sleep(time * 1000);					
				} catch (InterruptedException e) {
					// 
					e.printStackTrace();
				}
					llamarPantalla();
			}}.start();
		
	}

	/**
	 * @param thread
	 * @param fondo
	 * @param scr
	 */
	public IntroScreen(final Thread  thread,Bitmap fondo) {
		this(thread,new BitmapField(fondo));
	}
	
	/**
	 * @param Thread thread
	 * @param Field field
	 * 
	 * Devuelve una pantalla que se muestar hasta que termine la ejecucion de
	 * thread, hay que sobre escribir el metodo fin, para especificar que hacer 
	 * cuando termine el hilo.
	 */
	public IntroScreen(final Thread  thread,Field field) {
		super(DEFAULT_CLOSE);		
		add(field);
		this.thread = thread;				

		
		UiApplication.getUiApplication().invokeLater( new Runnable(){
			public void run() {
				try {					
					thread.join();
				} catch (InterruptedException e) {					 
					e.printStackTrace();
				}
					llamarPantalla();
			}});
	}

	
	private void llamarPantalla(){		
		UiApplication.getUiApplication().invokeLater(new Runnable() {
		public void run(){			
			try{
				fin();
			}catch(Exception e){
				System.out.println("Hola: " + e);
			}			
		}}
	);
	}


	public IntroScreen(int time, Bitmap bitmap){
		this(time,new BitmapField(bitmap));
	}


	// Sobrescribir este metodo para cambiar el modo en que se llama a siguiente pantalla la pantalla 
	public abstract void fin();





	public void setScreen(Screen screen) {
		scr = screen;
	}

	public void catchObject(Object o) {
		UtilExceptions.catchException(((Exception)o));		
	}
}
