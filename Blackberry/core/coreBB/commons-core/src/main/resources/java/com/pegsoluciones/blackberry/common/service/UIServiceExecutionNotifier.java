/**
 * 
 */
package com.pegsoluciones.blackberry.common.service;

import net.rim.device.api.system.Application;
import net.rim.device.api.ui.UiApplication;

/**
 * @author PEG Soluciones S.A.
 *
 */
public class UIServiceExecutionNotifier implements ServiceExecutionNotifier {

	public void callComplete(final ServiceExecutionListener listener, 
			final Object parameters) {
		
		synchronized (Application.getEventLock()) {
			
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				
				public void run() {
				
					listener.onCallComplete(parameters);
					
				}
				
			});
			
		}
	}


	public void callFail(final ServiceExecutionListener listener, 
			final ServiceError cause) {

		UiApplication.getUiApplication().invokeLater(new Runnable() {
			
			public void run() {
			
				listener.onError(cause);
				
			}
			
		});
	}

}
