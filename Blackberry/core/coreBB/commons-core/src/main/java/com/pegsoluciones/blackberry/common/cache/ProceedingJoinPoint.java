package com.pegsoluciones.blackberry.common.cache;

/**
 * 
 * 
 * 
 * @author PEG Soluciones S.A.
 * @since 1.0
 *
 */
public interface ProceedingJoinPoint {

	
	/**
	 * 
	 * @param object This object represents the real parameters
	 * 			     which are passed to the real function.
	 * @return Cached results
	 */
	Object proceed();
	
	
	/**
	 * 
	 * @param object This object represents the real parameters
	 * 			     which are passed to the real function.
	 * @return
	 */
	Object[] arguments();
	
	
	
}
