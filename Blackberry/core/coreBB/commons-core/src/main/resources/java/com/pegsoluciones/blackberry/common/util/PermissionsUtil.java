package com.pegsoluciones.blackberry.common.util;

import net.rim.device.api.applicationcontrol.ApplicationPermissions;
import net.rim.device.api.applicationcontrol.ApplicationPermissionsManager;

/**
 * @author poza pablo
 * 
 * clase que permite controlar si la aplicacion tiene los distintos permisos
 *
 */
public abstract class PermissionsUtil {
	
	
	/**
	 * 
	 */
	public static void checkPermissions() {

		ApplicationPermissionsManager apm = ApplicationPermissionsManager.getInstance();
		ApplicationPermissions original = apm.getApplicationPermissions();


		ApplicationPermissions permRequest = new ApplicationPermissions();
		permRequest.addPermission(ApplicationPermissions.PERMISSION_EMAIL);

		boolean acceptance = original.containsPermissionKey(ApplicationPermissions.PERMISSION_EMAIL);

		if (acceptance) {
			return;
		} else {
		}
	}
	

	/**
	 * @return si tiene o no los permisos mas basicos
	 */
	public static boolean tienePermisos(){

		int keys[] ={
				ApplicationPermissions.PERMISSION_EVENT_INJECTOR,
				ApplicationPermissions.PERMISSION_FILE_API,
				ApplicationPermissions.PERMISSION_EMAIL,
				ApplicationPermissions.PERMISSION_HANDHELD_KEYSTORE,
				ApplicationPermissions.PERMISSION_INTERNAL_CONNECTIONS,
				ApplicationPermissions.PERMISSION_MEDIA,
				ApplicationPermissions.PERMISSION_PHONE,
				ApplicationPermissions.PERMISSION_WIFI,				
				ApplicationPermissions.PERMISSION_LOCATION_API
		};
		return tienePermisos(keys);
	}
	
	/**
	 * @param keys
	 * @return si tiene o no los permisos pasados en keys
	 */
	public static boolean tienePermisos(int keys[]){
		ApplicationPermissionsManager apm = ApplicationPermissionsManager.getInstance();
		ApplicationPermissions original = apm.getApplicationPermissions();
		
		for (int x = 0; x <= keys.length - 1; x++){
			if (original.getPermission(keys[x]) == ApplicationPermissions.VALUE_DENY)
					return false;
		}
				
		return true;

	}
}
