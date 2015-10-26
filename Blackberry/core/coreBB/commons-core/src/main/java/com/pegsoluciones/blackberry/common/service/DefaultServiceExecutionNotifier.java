package com.pegsoluciones.blackberry.common.service;

public class DefaultServiceExecutionNotifier implements
		ServiceExecutionNotifier {

	public void callComplete(ServiceExecutionListener listener,
			Object parameters) {
		
		listener.onCallComplete(parameters);
	}


	public void callFail(ServiceExecutionListener listener, ServiceError cause) {
		
		listener.onError(cause);
	}

}
