package com.pegsoluciones.blackberry.common.ui.screen;

import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.DialogClosedListener;

import com.pegsoluciones.blackberry.common.util.ExBoolean;

public class DialogDescargando extends Dialog{

	 static Object[] boton= {"Cancelar"};
	 private ExBoolean cancelaron;
	 
	 public DialogDescargando(ExBoolean cancelaronPopup){
		 this(cancelaronPopup,"Descargando...");
	 }
	 
	public DialogDescargando(ExBoolean cancelaronPopup, String msj) {
		 super(msj, boton,null, -1, null,Screen.DEFAULT_CLOSE);
		 
		 this.cancelaron = cancelaronPopup;
		 
		 this.setDialogClosedListener(new DialogClosedListener() {
			
			public void dialogClosed(Dialog dialog, int choice) {
				if( choice != -1)
					cancelaron.setValue(true);				
			}
		});
	}


}