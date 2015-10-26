package com.peg.blackberry.planetaboca.util;

import net.rim.device.api.ui.component.Dialog;

import com.peg.blackberry.planetaboca.application.ApplicationFactory;
import com.peg.blackberry.planetaboca.service.TwitterService;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.ui.screen.DialogDescargando;
import com.pegsoluciones.blackberry.common.util.ExBoolean;

public class OnClickUtil {

	public static void tryGoTwitter() {
		
		final ExBoolean cancelaronPopup = new ExBoolean(false);
		final DialogDescargando dialogDescargando = new DialogDescargando(cancelaronPopup );
		
		TwitterService twitterService = 
			ApplicationFactory.getInstance().getTwitterService();
		
		twitterService.getTwetts(new ServiceExecutionListener() {
			
			public void onError(ServiceError error) {
				if(cancelaronPopup.isFalse()){
					dialogDescargando.close();
					Dialog.alert(Strings.ERROR_TWITTER);
				}else{
					cancelaronPopup.setValue(false);
				}
			}
			
			public void onCallComplete(Object parameters) {
				if(cancelaronPopup.isFalse()){
					dialogDescargando.close();
				//	Vector v = (Vector) parameters;		
				//	SimulaScreens.pushManager(new TweetsManager(v));
				}else{
					cancelaronPopup.setValue(false);
				}				
			}
		}, "PlanetaBoca");
		
		dialogDescargando.show();
	}

}
