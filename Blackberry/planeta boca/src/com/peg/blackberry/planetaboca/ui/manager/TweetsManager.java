package com.peg.blackberry.planetaboca.ui.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import net.rim.device.api.system.Alert;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.DialogClosedListener;
import net.rim.device.api.ui.container.HorizontalFieldManager;

import com.peg.blackberry.planetaboca.application.ApplicationFactory;
import com.peg.blackberry.planetaboca.domain.Twett;
import com.peg.blackberry.planetaboca.domain.TwitterUser;
import com.peg.blackberry.planetaboca.service.TwitterService;
import com.peg.blackberry.planetaboca.ui.field.TwitteoField;
import com.peg.blackberry.planetaboca.util.Colores;
import com.peg.blackberry.planetaboca.util.Strings;
import com.pegsoluciones.blackberry.common.device.ScreenUtil;
import com.pegsoluciones.blackberry.common.screenEnManager.SimulaScreens;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.ui.field.Linea;
import com.pegsoluciones.blackberry.common.ui.screen.DialogDescargando;
import com.pegsoluciones.blackberry.common.util.ExBoolean;

public class TweetsManager extends PlanetaBocaBaseManager implements ServiceExecutionListener {

	protected static final long TIME_AUTO_REFRESH = 30000;
	private static TweetsManager instance;
	ExBoolean cancelaronPopup = new ExBoolean(false);
	final DialogDescargando dialogDescargando = new DialogDescargando(cancelaronPopup );
	private Vector tweetFields;
	private boolean actualizando = false;
	private CheckboxField check;
	HorizontalFieldManager h = new HorizontalFieldManager();
	private TwitterService twitterService;	
	Vector twetts;
	InputStream is;
	byte[] tuneBytes;
	int i = 0;
	
	/**
	 * ojo pide twitts y levanta pop si es necesario
	 */
	public static TweetsManager getInstance(){
		if(instance == null)
			instance = new TweetsManager();		
		instance.pedirTwitsSiTodaviaNoPidio();		
		return instance;
		
	}	
		
	private void pedirTwitsSiTodaviaNoPidio() {
		if(TweetsManager.this.getFieldCount() == 0 && !dialogDescargando.isDisplayed()){
			cancelaronPopup.setValue(false);
			dialogDescargando.show();		
			pedirTwits();
		}
	}

	private TweetsManager() {		
		twitterService = ApplicationFactory.getInstance().getTwitterService();
		
		createMidi();
		
		tweetFields = new Vector();
		
		draw();				
			
		
		createThread();				
		dialogDescargando.setDialogClosedListener(new DialogClosedListener() {
			
			public void dialogClosed(Dialog dialog, int choice) {
				if(TweetsManager.this.getFieldCount() == 0)
					SimulaScreens.onClose();
			}
		});
			
	}

	private void createMidi() {
		is = TweetsManager.class.getResourceAsStream("/images/SILBATO.MID");		
		try {
			tuneBytes = new byte[is.available()];
			is.read(tuneBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void draw() {
		check = new CheckboxField(Strings.AUTO_REFRESH, false,FIELD_VCENTER );
		check.setMargin(0,5,0,5);
		h.add(check);
		
		h.add(new ButtonField(Strings.ACTUALIZA_AHORA){
			
			protected boolean trackwheelClick(int status, int time) {
				dialogDescargando.show();				
				pedirTwits();			
				return true;
			}
			
		});		
	}

	private void createThread() {
		new Thread(){		
			public void run() {
				while(true){
					try {
						sleep(TIME_AUTO_REFRESH);
						if(check.getChecked() || TweetsManager.this.getFieldCount() > 0){				
							pedirTwits();							
						}
					} catch (InterruptedException e) {				
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	protected void pedirTwits() {
		if(!actualizando){
			actualizando = true;
			twitterService.getTwetts(TweetsManager.this, Strings.USER_TWITTER);
		}
	}

	public void onError(ServiceError error) {
		actualizando = false;
		if(cancelaronPopup.isFalse()){
			dialogDescargando.close();
			Dialog.alert(Strings.ERROR_TWITTER);			
		}else{
			cancelaronPopup.setValue(false);
		}
	}
	
	public void onCallComplete(Object parameters) {
		actualizando = false;
		if(cancelaronPopup.isFalse()){			
			Vector userYtwetts = (Vector) parameters;		
			twetts = (Vector) userYtwetts.elementAt(1);
			
			if(dialogDescargando.isDisplayed())
				dialogDescargando.close();
			
			if(tweetFields.size() >0){
				Twett newFirst = (Twett) twetts.elementAt(0);
				Twett lastFirst = (Twett) tweetFields.elementAt(0);
				if(newFirst.getId().equals(lastFirst.getId())){
					return;
				}
			}
			
			this.deleteAll();
			tweetFields.removeAllElements();			
			TwitterUser tU = (TwitterUser) userYtwetts.elementAt(0);
			Field f;
			add(f = new InfoUserTwitterField(tU));
			add(new Linea(Colores.GRISECITO,Colores.GRISECITO,Display.getWidth(),0,NON_FOCUSABLE));
			add(h);
			twetts = (Vector) userYtwetts.elementAt(1);
			for (int i = 0; i < twetts.size(); i++) {
				add(new Linea(Colores.GRISECITO,Colores.GRISECITO,Display.getWidth(),0,NON_FOCUSABLE));
				Twett twett = (Twett) twetts.elementAt(i);
				tweetFields.addElement(twett);
				add(new TwitteoField(twett));
			}
			if(this.getScreen() != null && this.getScreen().equals(ScreenUtil.getActiveScreen())){
				f.setFocus();
				invalidate();
				this.getScreen().invalidate();
		    }			
			Alert.startVibrate(2);
			Alert.startMIDI(tuneBytes, true);
		}else{
			cancelaronPopup.setValue(false);
		}				
	}	

	

}
