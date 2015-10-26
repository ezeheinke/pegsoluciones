package com.pegsoluciones.blackberry.common.device;

import net.rim.blackberry.api.invoke.Invoke;
import net.rim.blackberry.api.invoke.PhoneArguments;
import net.rim.blackberry.api.mail.ServiceConfiguration;
import net.rim.blackberry.api.mail.Session;
import net.rim.blackberry.api.mail.Store;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.EventInjector;
import net.rim.device.api.system.KeypadListener;
import net.rim.device.api.ui.Keypad;

public abstract class DeviceUtil {
	
    public static boolean runningOnSimulator() {
        int modHandle = CodeModuleManager.getModuleHandle("net_rim_sdk_simulationSB");
        if (modHandle > 0)
            return true;
        else
            return false;
    } 
    
    /**
     * El metodo utiliza la aplicacion del dispositivo para hacer llamadas
     * Invoca la aplicaci�n y automaticamente llama al numero especificado. 
     * 
     * 
     * @param number
     */
    public static void runCallNumber(String number) {
    	
    	Invoke.invokeApplication( 
    			Invoke.APP_TYPE_PHONE, 
    			new PhoneArguments(PhoneArguments.ARG_CALL, number));
    }
    
    
    /**
     * Este es el primer metodo que comento
     * @return el id del telefono
     */
    public static String getPhoneID(){
    	return (Integer.toString(DeviceInfo.getDeviceId(),16)).toUpperCase();
    }
    
	static public void runBackground(){
        EventInjector.KeyCodeEvent ev = new EventInjector.KeyCodeEvent(
        		EventInjector.KeyCodeEvent.KEY_DOWN, ((char)Keypad.KEY_END),
        		KeypadListener.STATUS_NOT_FROM_KEYPAD,100);
        
        EventInjector.invokeEvent(ev);
	}    
	
	public static String getDefaultMail(){
		Session session = Session.getDefaultInstance();
		if (session != null) {

			//now the store

			Store store = session.getStore();

			//then the service configuration

			ServiceConfiguration serviceConfig = store.getServiceConfiguration();


			//now get the email address

			return  serviceConfig.getEmailAddress();
		}
		return "-";
	}

}
