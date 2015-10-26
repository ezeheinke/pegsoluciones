package com.pegsoluciones.blackberry.common.concurrent;

/**
 * A task represents logic to be executed in a diferent thread.
 * Uses the {@link TaskWorker} class in order to queue the
 * operation.
 * To understand how its works please see {@link Runnable}
 * 
 * @author PEG Soluciones S.A.
 * @since 1.0
 */
public interface Task {

	/**
	 * Implements logic to be executed
	 */
	void execute(); 
	
	
}
