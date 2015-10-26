package com.pegsoluciones.blackberry.common.event;

/**
 * 
 * 
 * 
 * @author PEG Soluciones S.A.
 *
 */
public interface EventObserver {

	
	/**
	 * 
	 * 
	 * 
	 * @param topic
	 * @param parameter
	 */
	void onEvent(String topic, Object parameter);
	
	
	
}
