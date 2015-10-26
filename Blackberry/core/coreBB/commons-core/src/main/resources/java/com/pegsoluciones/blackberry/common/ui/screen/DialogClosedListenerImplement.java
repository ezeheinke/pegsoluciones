package com.pegsoluciones.blackberry.common.ui.screen;

import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.DialogClosedListener;

public class DialogClosedListenerImplement implements DialogClosedListener {

	public void dialogClosed(Dialog dialog, int choice) {		
		onClose(dialog,choice);
	}

	public void onClose(Dialog dialog, int choice) {
		
	}

}
