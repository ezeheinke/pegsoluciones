package com.pegsoluciones.blackberry.common.application;

import net.rim.device.api.system.Application;

public abstract class BlackberryApplication {

	
	/**
	 * Initialization code. This code will be called
	 * before enter in the thread loop.
	 */
	abstract protected void setUp();
	
	
	
	/**
	 * Enters the event dispatcher
	 * 
	 * @param app The UI application
	 */
	protected void start(Class clazz) {
		
		this.setUp();
		
		try {
			Application app = (Application) clazz.newInstance();
			app.enterEventDispatcher();
		} catch (Exception ex) {
			throw new RuntimeException("Wrong UI Application class: " + ex.toString());
		}
	}
	
}
