package com.peg.blackberry.planetaboca.ui.manager;

import java.util.Vector;

import net.rim.blackberry.api.browser.Browser;
import net.rim.blackberry.api.browser.BrowserSession;
import net.rim.blackberry.api.invoke.Invoke;
import net.rim.blackberry.api.invoke.MessageArguments;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.HorizontalFieldManager;

import com.blackberry.facebook.ApplicationSettings;
import com.blackberry.facebook.Facebook;
import com.blackberry.facebook.FacebookException;
import com.blackberry.facebook.inf.User;
import com.peg.blackberry.planetaboca.application.ApplicationFactory;
import com.peg.blackberry.planetaboca.domain.Noticia;
import com.peg.blackberry.planetaboca.service.ComentariosService;
import com.peg.blackberry.planetaboca.ui.field.BotoncitoImagenField;
import com.peg.blackberry.planetaboca.util.Strings;
import com.pegsoluciones.blackberry.common.screenEnManager.SimulaScreens;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;


public class BarritaCompartirManager extends HorizontalFieldManager implements ServiceExecutionListener {
	
	///twtter
	private static final String URL_TWITTER = "http://twitter.com/share?via=PlanetaBoca&text=";
	///////facebook
	private static final String NEXT_URL = "http://www.facebook.com/connect/login_success.html";
	private static final String APPLICATION_ID = "109805979149550"; //"136512159624";
	private static final String APPLICATION_SECRET = "e5cb978a606095b383528a5a0fd2160f"; //"64511ce33a2c95fc61963acf13f24bb7";
	private Noticia noticia;
	
	Dialog dialog;
	
	public BarritaCompartirManager(final Noticia noticia) {
		super(Field.FIELD_RIGHT);
		
		setMargin(new XYEdges(5,10,5,10));
		
		add(new BotoncitoImagenField(PosicionesUtil.UbicarEn("facebook.png", "facebook.png", "bold/facebook.png", "facebook.png")){
			public void onClick(){

				String[] PERMISSIONS = Facebook.Permissions.PUBLISHING_PERMISSIONS;// USER_DATA_PERMISSIONS;
				ApplicationSettings as = new ApplicationSettings(NEXT_URL, APPLICATION_ID,
						APPLICATION_SECRET, PERMISSIONS);
				Facebook fb = Facebook.getInstance(as);
				User user = null;
				try {
					user = fb.getCurrentUser();
					if (user != null) {
						////// @arg1 = texto antes del titulo @arg2 = click para ver la 
						///url del titulo @arg3 = click para ver la imagen grande 
						//@arg4 = titulo del post @arg5 = 1er parrafo    @arg6= 2 parrafo 
						//@arg7 = url_imagen a mostrar
						user.publishPost("", noticia.getUrlNoticia(),noticia.getImagen(),
								noticia.getTitulo(), noticia.getResumen(),
								"Mantenete actualizado en: http://www.facebook.com/planetabocajuniorsfans"
								, noticia.getImagen());
						
						
					}
				} catch (FacebookException e) {
					Dialog.alert("Error al publicar, intentelo más tarde.");
				}
				
			}
		});
		
		
		add(new BotoncitoImagenField(PosicionesUtil.UbicarEn("twitter.png", "twitter.png", "bold/twitter.png", "twitter.png")){
			public void onClick(){
				BrowserSession bS=Browser.getDefaultSession();
		        String url = URL_TWITTER + noticia.getTitulo()+ " " + noticia.getUrlNoticia();
				bS.displayPage(url);;
			}
		});
		
		add(new BotoncitoImagenField(PosicionesUtil.UbicarEn("mail.png", "mail.png", "bold/mail.png", "mail.png")){
			public void onClick(){
				Invoke.invokeApplication(Invoke.APP_TYPE_MESSAGES,new  MessageArguments(						
						MessageArguments.ARG_NEW,"",noticia.getTitulo()	, 
						noticia.getResumen() + "\n" + noticia.getUrlNoticia()));	
			}
		});
	
		add(new BotoncitoImagenField(PosicionesUtil.UbicarEn("comentarios.png", "comentarios.png", "bold/comentarios.png", "comentarios.png")){
			public void onClick(){
				ComentariosService service = ApplicationFactory.getInstance().getComentariosService();
				service.getComentarios(noticia.getId(), BarritaCompartirManager.this);
				dialog.show();
			}
		});
		this.noticia = noticia;
		
		Object[] o = {"Cancelar"};
		dialog = new Dialog("Descargando...",o ,null, -1, null,Screen.DEFAULT_CLOSE);
	}


	public void onCallComplete(Object parameters) {
		if(dialog.isDisplayed()){
			dialog.close();
			Vector v = (Vector) parameters;
			if(v.size() > 0)
				SimulaScreens.pushManager(new ComentariosManager(noticia, v));
			else{
				Object[] o = {"SI","NO"};
				Dialog d = new Dialog(Strings.CERO_COMENTARIOS,o ,null, -1, null,Screen.DEFAULT_CLOSE);
				int i = d.doModal();
				if(i == 0){
					SimulaScreens.pushManager(new ComentariosManager(noticia, v));
				}
			}				
			
		}
	}


	public void onError(ServiceError error) {
		if(dialog.isDisplayed()){
			dialog.close();
			Dialog.inform(Strings.ERROR_COMENTARIOS);
		}
	}

}
