package com.peg.blackberry.planetaboca.service;

import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;

public interface TwitterService {

	/**
	 * @param listener
	 * @param user
	 * 
	 * le devuelve al listener un vector donde el primer elemento es un TwitterUser
	 * y el segundo los Twetts
	 */
	public void getTwetts(ServiceExecutionListener listener,String user);
	
	public void getTwetts(ServiceExecutionListener listener,String user,String since
			,String count);
	
}
