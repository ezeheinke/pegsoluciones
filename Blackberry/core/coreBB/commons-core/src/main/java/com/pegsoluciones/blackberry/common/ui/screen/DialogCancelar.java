package com.pegsoluciones.blackberry.common.ui.screen;

import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.component.Dialog;

public class DialogCancelar extends Dialog {
	
	static Object[] boton= {"Cancelar"};

	public DialogCancelar(){
		super(" Descargando...        ", boton,null, -1, null, Screen.DEFAULT_CLOSE);
	} 

}
