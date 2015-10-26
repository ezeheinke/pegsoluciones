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
	public static boolean solicitarPermisos() {

		ApplicationPermissionsManager apm = ApplicationPermissionsManager.getInstance();
		ApplicationPermissions original = apm.getApplicationPermissions();

		ApplicationPermissions permRequest = new ApplicationPermissions();
		permRequest.addPermission(ApplicationPermissions.PERMISSION_EMAIL);
		permRequest.addPermission(ApplicationPermissions.PERMISSION_FILE_API);
		permRequest.addPermission(ApplicationPermissions.PERMISSION_EMAIL);
		permRequest.addPermission(ApplicationPermissions.PERMISSION_MEDIA);
		permRequest.addPermission(ApplicationPermissions.PERMISSION_PHONE);
		permRequest.addPermission(ApplicationPermissions.PERMISSION_WIFI);			

		return original.containsPermissionKey(ApplicationPermissions.PERMISSION_EMAIL);				
	}
	

	/**
	 * @return si tiene o no los permisos mas basicos
	 */
	public static boolean tienePermisos(){

		int keys[] ={
				ApplicationPermissions.PERMISSION_FILE_API,
				ApplicationPermissions.PERMISSION_EMAIL,
				ApplicationPermissions.PERMISSION_MEDIA,
				ApplicationPermissions.PERMISSION_PHONE,
				ApplicationPermissions.PERMISSION_WIFI,			
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
