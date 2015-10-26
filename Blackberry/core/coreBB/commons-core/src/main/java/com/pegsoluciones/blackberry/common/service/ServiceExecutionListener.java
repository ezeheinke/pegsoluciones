package com.pegsoluciones.blackberry.common.service;


/**
 * This interface acts like a way of async event handler for
 * service layer. All services work in a async way, calling 
 * the <code>onCallComplete</code> callback when the response
 * is ready for the user.
 * 
 * @author PEG Soluciones S.A.
 * @since 1.0
 */
public interface ServiceExecutionListener {

	
	/**
	 * This method is called when the service has
	 * a response available for the user.
	 * 
	 * @param parameters received from the service
	 */
	void onCallComplete(Object parameters);
	
	
	/**
	 * This method is called when an error
	 * happened within the service's call
	 * 
	 * @param error The error description
	 */
	void onError(ServiceError error);
	
	
}
