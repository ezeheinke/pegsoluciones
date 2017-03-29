package com.pozasoft.android.osferroviarios.core;

public interface ServiceListener {

	public void onComplete(Object response) ;

	public void onError(ServiceError e);
	
}
