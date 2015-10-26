package com.pegsoluciones.blackberry.common.service;

import com.pegsoluciones.blackberry.common.concurrent.ThreadPool;

/**
 * Common service functionality.
 * 
 * @author PEG Soluciones S.A.
 * @since 1.0
 */
public class AbstractService {
	
	/** Number of threads created by the pool */
	private static final int FIXED_THREADS_SIZE = 4;
	
	/** Thread pool shared by all services */
	private static final ThreadPool threadPool = new ThreadPool(FIXED_THREADS_SIZE);
	
	/** Notifier of events */
	private ServiceExecutionNotifier notifier;
	
	
	/**
	 * Set the method used by service layer to 
	 * update UI layer
	 * 
	 * @param notifier Set the notifier
	 */
	public void setServiceNotifier(ServiceExecutionNotifier notifier) {
		
		this.notifier = notifier;
	}
	
	
	/**
	 * Execute busisness logic in a separate thread
	 * 
	 * @param task To be executed in a thread queue.
	 */
	protected void run(Runnable task) {
		
		threadPool.assign(task);
		
	}
	
	/**
	 * This method has to be called when this service wants
	 * to notify that there is a response ready to be consumed.
	 *  
	 * @param listener Callback interface
	 * @param parameters
	 */
	protected void complete(final ServiceExecutionListener listener, final Object parameters) {
		
		notifier.callComplete(listener, parameters);
		
	}
	
	/**
	 * This method has to be called when this service wants
	 * to notify that there was an error in the service's execution.
	 * 
	 * @param listener
	 * @param cause
	 */
	protected void fail(final ServiceExecutionListener listener, final ServiceError cause) {
		
		notifier.callFail(listener, cause);
		
	}
	
}
