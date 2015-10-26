package com.pegsoluciones.blackberry.common.service;

/**
 * Error Description
 * 
 * @author PEG Soluciones S.A.
 * @since 1.0
 */
public class ServiceError {

	private String message;
	private Throwable cause;

	public ServiceError(String message) {
		this.message = message;
	}
	
	public ServiceError(String message, Throwable throwable) {
		this(message);
		this.cause = throwable;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the cause
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * @param cause the cause to set
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}
	
}
