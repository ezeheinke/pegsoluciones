package com.pegsoluciones.blackberry.common.ui.screen;

import com.pegsoluciones.blackberry.common.device.ScreenUtil;
import com.pegsoluciones.blackberry.common.screenEnManager.SimulaScreens;

import net.rim.device.api.ui.container.FullScreen;

/**
 * Clase de la que debe heredar la pantalla que use el simulaScreen, esta clase
 * se encarga de la logica de cuando se apreta el boton BACK.
 * 
 * Muestra un cartel de "Desea salir de la App?" antes de cerrar la app. 
 * 
 * @author Pablo Poza
 *
 */
public class HomeScreenBase extends FullScreen {
	
	public HomeScreenBase(){
		super(DEFAULT_CLOSE);
	}
	
	public HomeScreenBase(long flags){
		super(flags);
	}
	
	public boolean onClose(){
		if(SimulaScreens.siCierroPantallaSeCierraApp())
			ScreenUtil.deseaSalir();
		else
			SimulaScreens.onClose();
		return true;		
	}		

}
