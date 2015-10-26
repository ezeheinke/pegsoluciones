package com.pegsoluciones.blackberry.common.ui.screen;

import net.rim.device.api.ui.container.FullScreen;

import com.pegsoluciones.blackberry.common.device.ScreenUtil;
import com.pegsoluciones.blackberry.common.screenEnManager.SimulaScreens;

/**
 * Clase de la que debe heredar la pantalla que use el simulaScreen, esta clase
 * se encarga de la logica de cuando se apreta el boton BACK.
 * 
 * Muestra un cartel de "Desea salir de la App?" antes de cerrar la app. 
 * 
 * @author Pablo Poza
 *
 */
public abstract class HomeScreenBase extends FullScreen {
	
	public HomeScreenBase(){
		super(DEFAULT_CLOSE);
	}
	
	public HomeScreenBase(long flags){
		super(flags);
	}
	
	public boolean onClose(){
		if(SimulaScreens.siCierroPantallaSeCierraApp())
			ScreenUtil.deseaSalir(getMensajeSalida());
		else
			SimulaScreens.onClose();
		return true;		
	}		

	public abstract String getMensajeSalida();
}
