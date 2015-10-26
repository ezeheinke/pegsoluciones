package com.peg.blackberry.planetaboca.ui.screen;

import java.util.Vector;

import net.rim.blackberry.api.invoke.Invoke;
import net.rim.blackberry.api.invoke.MessageArguments;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;

import com.peg.blackberry.planetaboca.application.ApplicationEvent;
import com.peg.blackberry.planetaboca.application.ApplicationFactory;
import com.peg.blackberry.planetaboca.service.MensajesService;
import com.peg.blackberry.planetaboca.ui.field.BannerField;
import com.peg.blackberry.planetaboca.ui.field.HeaderField;
import com.peg.blackberry.planetaboca.ui.manager.ListadoDeportesManager;
import com.peg.blackberry.planetaboca.ui.manager.NoticiasManager;
import com.peg.blackberry.planetaboca.ui.manager.TweetsManager;
import com.peg.blackberry.planetaboca.util.MensajesListener;
import com.peg.blackberry.planetaboca.util.Strings;
import com.pegsoluciones.blackberry.common.device.ScreenUtil;
import com.pegsoluciones.blackberry.common.event.EventManager;
import com.pegsoluciones.blackberry.common.io.PersistentStoreFacade;
import com.pegsoluciones.blackberry.common.screenEnManager.SimulaScreens;
import com.pegsoluciones.blackberry.common.ui.screen.ExPupUpScreen;
import com.pegsoluciones.blackberry.common.ui.screen.HomeScreenBase;

public class HomeScreen extends HomeScreenBase {
	
	private static final String AYUDA_FUE_MOSTRADA = "ayudaFueMostrada";

	public HomeScreen(Vector noticias){
		super(DEFAULT_CLOSE | DEFAULT_MENU);
		this.add(new HeaderField());
		Field principalManager;
		this.add(principalManager = new NoticiasManager(noticias,Strings.CATEGORIA_RECIENTES){
			
			public void onDisplay(){
				EventManager.getInstance().notify(ApplicationEvent.CAMBIO_SECCION,
						Strings.HOME);
				super.onDisplay();
			}
			
		});
		this.add(BannerField.getInstance());
		
		SimulaScreens.setScreen(this);
		SimulaScreens.setInitialField(principalManager);

		MensajesService mensajesService = ApplicationFactory.getInstance().getMensajesService();
		mensajesService.getMensajes(new MensajesListener());
		
	}
	
	
	public void makeMenu(Menu menu,int instance){
		
		int i = 0;
		
		menu.deleteAll();
		
		menu.add(new MenuItem("Home", i++, 10){
	        public void run() {
	    		SimulaScreens.popHasta(1);
	        }
		});
		
		
		menu.add(new MenuItem("Elegir seccion", i++, 10){
	        public void run() {
	    		ScreenUtil.pushScreen(new ExPupUpScreen(new ListadoDeportesManager(), 
	    				ListadoDeportesManager.ANCHO, ListadoDeportesManager.ALTO, Color.BLACK));
	        }
		});
		
		
		menu.add(new MenuItem("Twitter - Minuto a Minuto", i++, 10){
	        public void run() {
	        	SimulaScreens.pushManager(TweetsManager.getInstance());
	        }
		});
		
		menu.addSeparator();
		
		menu.add(new MenuItem("Publicidad", i++, 10){
	        public void run() {
	           int ask = Dialog.ask(Strings.PUBLICIDAD, Strings.OPCIONES_SI_O_NO, 0);
	           if(ask == 0){
	       		Invoke.invokeApplication(Invoke.APP_TYPE_MESSAGES,new MessageArguments(						
	    				MessageArguments.ARG_NEW,Strings.MAIL_PUBLICIDAD,Strings.ASUNTO_PUBLICIDAD,
	    				Strings.CUERPO_MAIL_PUBLICIDAD));
	           }
	        }
		});
		
		menu.add(new MenuItem("Acerca de PBJ", i++, 10){
	        public void run() {
	        	Dialog.inform(Strings.ACERCA_DE);
	        }
		});
		menu.addSeparator();
		
		menu.add(new MenuItem("Ayuda", i++, 10){
	        public void run() {
	           Dialog.inform(Strings.AYUDA);
	        }
		});	
		
		menu.addSeparator();
		
		menu.add(new MenuItem("Construido por PEG", i++, 10){
	        public void run() {
	           Dialog.inform(Strings.CONSTRUIDO_POR_PEG);
	        }
		});			
		menu.addSeparator();
				
		menu.add(new MenuItem("PBJ Copyright©" , i++, 10){
	        public void run() {
	           Dialog.inform(Strings.COPYRIGHT);
	        }
		});	
	}


	public String getMensajeSalida() {
		return Strings.DESEA_SALIR;
	}
	
	public void onDisplay(){
		if(PersistentStoreFacade.get(AYUDA_FUE_MOSTRADA) == null){
			Dialog.inform(Strings.AYUDA + Strings.BUSCA_AYUDA_EN_MENU);
			PersistentStoreFacade.recordar(AYUDA_FUE_MOSTRADA, "si");
		}
		super.onExposed();
	}

}
