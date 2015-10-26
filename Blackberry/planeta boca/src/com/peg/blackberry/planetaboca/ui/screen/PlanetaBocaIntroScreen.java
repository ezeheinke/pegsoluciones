package com.peg.blackberry.planetaboca.ui.screen;

import java.util.Vector;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.FullScreen;

import com.peg.blackberry.planetaboca.application.ApplicationFactory;
import com.peg.blackberry.planetaboca.service.NoticiasService;
import com.peg.blackberry.planetaboca.util.Colores;
import com.peg.blackberry.planetaboca.util.Strings;
import com.pegsoluciones.blackberry.common.device.ScreenUtil;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.ui.manager.IntroGifManager;
import com.pegsoluciones.blackberry.common.util.BitmapUtil;
import com.pegsoluciones.blackberry.common.util.GraphicUtil;
import com.pegsoluciones.blackberry.common.util.PermissionsUtil;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;

public class PlanetaBocaIntroScreen extends FullScreen implements ServiceExecutionListener {

	
	private Bitmap intro = BitmapUtil.getImagenParaEsteEquipo("intro.png");
	
	
	 public PlanetaBocaIntroScreen() {
		 super(DEFAULT_CLOSE);
		 
		 add(new IntroGifManager("loading.gif"));	
		 
		boolean tienePermisos = PermissionsUtil.tienePermisos();
		if(tienePermisos){
			 pedirNoticiasHome(); 
		 }else{
			 UiApplication.getUiApplication().invokeLater(new Runnable() {
				
				public void run() {					
					while(!PermissionsUtil.tienePermisos()){
						int ask = Dialog.ask(Strings.DARLE_PERMISOS, Strings.OPCIONES_SI_O_NO, 0);
						if(ask == 1 ){
							ScreenUtil.salir();
							return;
						}
					}			
					pedirNoticiasHome();
				}
			});
		 }
		 
		 
		
	}

	private void pedirNoticiasHome() {
		NoticiasService noticiasService = ApplicationFactory.getInstance().getNoticiasService();
		 noticiasService.getNoticias("1",Strings.TAMANIO_PAGINA,Strings.CATEGORIA_RECIENTES,this);
	}
	 
	protected void paint(Graphics g) {
		g.setBackgroundColor(Colores.AZUL_INTRO);
		g.clear();
		GraphicUtil.drawBitmap(g, intro ,PosicionesUtil.xCentrada(intro),PosicionesUtil.yCentrada(intro));
		super.paint(g);
	}

	public void onCallComplete(Object parameters) {
		ScreenUtil.pushScreen(new HomeScreen((Vector) parameters));
		ScreenUtil.closeActiveScreen();
	}

	public void onError(ServiceError error) {
		Dialog.inform("Error al conectarse al servidor, intentelo mas tarde.");		
	} 
	

}
