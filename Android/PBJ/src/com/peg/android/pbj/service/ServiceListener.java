package com.peg.android.pbj.service;

public interface ServiceListener {

	public void onComplete(Object response) ;

	public void onError(ServiceError e);
	
}
