package com.pegsoluciones.blackberry.common.service;

/**
 * 
 * 
 * 
 * @author PEG Soluciones S.A.
 *
 */
public interface ServiceExecutionNotifier {

	/**
	 * 
	 * 
	 * 
	 */
	void callComplete(ServiceExecutionListener listener, Object parameters);
	
	
	/**
	 * 
	 * 
	 * 
	 */
	void callFail(ServiceExecutionListener listener, ServiceError cause);
	
}
