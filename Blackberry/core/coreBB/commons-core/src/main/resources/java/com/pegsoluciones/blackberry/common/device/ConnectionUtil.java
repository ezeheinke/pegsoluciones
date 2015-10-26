package com.pegsoluciones.blackberry.common.device;

import net.rim.device.api.servicebook.ServiceBook;
import net.rim.device.api.servicebook.ServiceRecord;
import net.rim.device.api.system.CoverageInfo;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.RadioInfo;

/**
 * @author POZA PABLO	
 * Esta clase nos permite sabes si tenemos una conexion a internet disponible
 *
 */
public abstract class ConnectionUtil {
	
	//Valor de intensidad de se�al considerada buena para la recepci�n/Transmisi�n de Datos. (Medida en dbm)
	private static final int VALOR_CALIDAD_DE_SENIAL_CONSIDERADA_BUENA = -80;

	public static boolean hayWIFI() {		
		return RadioInfo.getSignalLevel( RadioInfo.WAF_WLAN ) != RadioInfo.LEVEL_NO_COVERAGE;
	}

	public static boolean hayBis() {
		return (CoverageInfo.getCoverageStatus() & 
			CoverageInfo.COVERAGE_DIRECT) == CoverageInfo.COVERAGE_DIRECT;
	}

	public static boolean hayAPN() {
        ServiceRecord serviceRecords[];
        ServiceBook serviceBook = ServiceBook.getSB();
        serviceRecords = serviceBook.findRecordsByCid("WPTCP");
        String servicio = new String();        
        for(int i = 0; i < serviceRecords.length; i++) {            
        	ServiceRecord serviceRecord = serviceRecords[i];
            servicio= serviceRecord.getUid();//serviceRecord.getName();
            servicio = servicio.toLowerCase();
            if (servicio.indexOf("wap") != -1)
            	return true;        
       }          
       return false;
	}


	public static boolean haySalidaAInternet() {
		boolean hayBis = hayBis();
		boolean hayWIFI = hayWIFI();		
		
		return DeviceInfo.isSimulator() || hayBis || hayWIFI;		
	}
	
	
	public static boolean hayBuenaCalidadDeSenial(){
		int qos = RadioInfo.getSignalLevel();
		return qos > VALOR_CALIDAD_DE_SENIAL_CONSIDERADA_BUENA;
	}
		
}
