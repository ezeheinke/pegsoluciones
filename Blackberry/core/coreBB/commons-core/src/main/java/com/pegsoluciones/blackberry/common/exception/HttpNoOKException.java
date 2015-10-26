package com.pegsoluciones.blackberry.common.exception;

public class HttpNoOKException extends Exception {

	String msj;
	String url;
	int responseCode;
	
	public HttpNoOKException(String msj, String url, int responseCode) {
		super(msj);
		this.msj = msj;
		this.url = url;
		this.responseCode = responseCode;
	}

}
