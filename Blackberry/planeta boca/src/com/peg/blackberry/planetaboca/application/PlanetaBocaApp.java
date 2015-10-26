package com.peg.blackberry.planetaboca.application;

import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;

import com.peg.blackberry.planetaboca.ui.screen.PlanetaBocaIntroScreen;
import com.pegsoluciones.blackberry.common.io.IOUtils;
import com.pegsoluciones.blackberry.common.ui.ScreenInfo;
import com.pegsoluciones.blackberry.common.util.FontUtil;


public class PlanetaBocaApp extends UiApplication {

	public PlanetaBocaApp() { 

    	//Ui.getUiEngineInstance().setAcceptableDirections(Display.DIRECTION_PORTRAIT);
    	if(Display.getOrientation() == Display.ORIENTATION_LANDSCAPE)
			Ui.getUiEngineInstance().setAcceptableDirections(Display.DIRECTION_LANDSCAPE);
		else
			Ui.getUiEngineInstance().setAcceptableDirections(Display.ORIENTATION_PORTRAIT);
			
		
    	
    	IOUtils.setNombreApp("PlanetaBoca-" + ApplicationDescriptor.currentApplicationDescriptor().getVersion());
       	ScreenInfo.setResDir("images/");
		IOUtils.createDir("store/home/user/yell");		
		FontUtil.setDefaultFont("Franklin Gothic Medium", Font.BOLD, 16);	

		Screen scr = new PlanetaBocaIntroScreen();
		pushScreen(scr);
		
   }

	public static String getIdUser() {
		return null;
	}
	
}