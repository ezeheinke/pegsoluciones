package com.peg.blackberry.planetaboca.util;

import java.util.Vector;

import net.rim.blackberry.api.browser.Browser;
import net.rim.blackberry.api.browser.BrowserSession;
import net.rim.device.api.system.Application;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;

import com.peg.blackberry.planetaboca.domain.Mensaje;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;

public class MensajesListener implements ServiceExecutionListener {

	int ask;
	
	public void onCallComplete(final Object parameters) {
		new Thread(){			
			public void run() {
				Vector v = (Vector) parameters;
				for(int i = 0 ; i < v.size() ; i++){
					final Mensaje m = (Mensaje) v.elementAt(i);
					try {
						sleep(m.getTime());								
						
						synchronized (Application.getEventLock()) {							
							UiApplication.getUiApplication().invokeLater(new Runnable() {								
								public void run() {								
									ask = Dialog.ask(m.getTexto(),Strings.OPCIONES_SI_O_NO, 0);	
									if(ask == 0){
										BrowserSession bS=Browser.getDefaultSession();
										bS.displayPage(m.getLink());
									}
								}								
							});							
						}											
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		

	}

	public void onError(ServiceError error) {
		// si falla ya fue
	}

}
