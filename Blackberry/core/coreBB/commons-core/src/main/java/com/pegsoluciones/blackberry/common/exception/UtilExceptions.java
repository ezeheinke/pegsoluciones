package com.pegsoluciones.blackberry.common.exception;

import com.pegsoluciones.blackberry.common.device.ScreenUtil;

public abstract class UtilExceptions {
	
	public static void catchException(Exception e ){

		ScreenUtil.showDialogYSalir("Arreglar");
		
	}

}
