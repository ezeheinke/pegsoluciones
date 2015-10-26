package com.pegsoluciones.blackberry.common.util;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.DeviceInfo;

import com.pegsoluciones.blackberry.common.ui.ScreenInfo;


/**
 * @author poza pablo
 * 
 * provee funciones basicas para determinar las posiciones de los 
 * objetos en la pantallas
 *
 */
/**
 * @author ITPC002
 *
 */
public abstract class PosicionesUtil {

    /**
     * @param b
     * @return la posicion en x para que b este centrado horizontalmente
     */
    public static int xCentrada(Bitmap b){
    	int x = (ScreenInfo.getScreenWidth() - b.getWidth()) / 2;
    	return x;
    }
    /**
     * @param b
     * @return la posicion en y para que b este centrado verticalemente
     */    
    public static int yCentrada(Bitmap b){
    	return (ScreenInfo.getScreenHeight() - b.getHeight()) / 2;
    }
    
    
    /**
     * @param b
     * @param f
     * @return la posicion en x para que b este centrado horizontalemente en f
     */    
    public static int xCentrada(Bitmap b, Bitmap f){
    	return (f.getWidth() - b.getWidth()) / 2;
    }    
    
    
    
    /**
     * @param b
     * @return la posicion en y para que b este al final de la pantalla verticalmente
     */
    public static int yAlFinal(Bitmap b){
    	return ScreenInfo.getScreenHeight() - b.getHeight();
    }
    
	/**
	 * @param b
	 * @param fondo
	 * @return la posicion en y para que b este al final de la pantalla
	 * verticalmente en fondo
	 */
	public static int yAlFinal(Bitmap b, Bitmap fondo) {
		return fondo.getHeight() - b.getHeight() ;
	}
	
    /**
     * @param curve
     * @param storm
     * @param bold
     * @param perl
     * @return devuelve el valor correspodiente al modelo segun su tamano de 
     * pantala
     */
    public static int UbicarEn(int curve,int storm,int bold,int perl){
    	if(ScreenInfo.getScreenWidth()==320)
    		return curve;
    	if(ScreenInfo.getScreenWidth()==480)
    			return bold;
    	if(ScreenInfo.getScreenWidth()==240)
    		return perl;
    	return storm;
    }
    
	/**
	 * @param w
	 * @return retorna la posicion en x para que quede centrado horizontalmente 
	 * si este mide w de ancho
	 * 
	 */
	public static int xCentrada(int w) {
		return (ScreenInfo.getScreenWidth() -w ) / 2;
	}
	
	/**
	 * @param h
	 * @return retorna la posicion en y para que quede centrado verticalmente
	 * si este mide h de alto
	 * 
	 */	
	public static int yCentrada(int h) {
		return (ScreenInfo.getScreenHeight() - h ) / 2;
	}
	
	/**
	 * @param so45
	 * @param so47
	 * @return devuelve el int segun version de SO, so45 si es menor igual que 4.5
	 * si no so47 
	 */
	public static int ubicarSegunSO(int so45, int so47) {
		
		double so = Double.parseDouble(DeviceInfo.getSoftwareVersion().substring(0,3));

		if(so <= 4.5)
			return so45;
		
		return so47;
	}
	
	
	/**
	 * @param altoACentrar
	 * @param altoContenedor
	 * @return
	 */
	public static int centrada(int altoACentrar, int altoContenedor) {
		return (altoContenedor - altoACentrar) / 2;
	}

	/**
	 * @param b
	 * @param alto
	 * @return
	 */
	public static int yCentrada(Bitmap b, int alto) {
		return ( b.getHeight() - alto   ) / 2;
	}

	public static String UbicarEn(String curve, String storm,String bold, String pearl) {
    	if(ScreenInfo.getScreenWidth()==320)
    		return curve;
    	if(ScreenInfo.getScreenWidth()==480)
    			return bold;
    	if(ScreenInfo.getScreenWidth()==240)
    		return pearl;
    	return storm;
	}
	
	public static String UbicarEnCel(String curve, String torch,String bold, String bold9700) {
    	if(ScreenInfo.getScreenWidth()==320)
    		return curve;
    	if(ScreenInfo.getScreenWidth()==480){
    		if(ScreenInfo.getScreenHeight()==320)
    			return bold;
    		else 
    			return bold9700;
    	}
    	
    	return torch;
    	
    	
	}
	
	/**
     * @param curve
     * @param torch
     * @param bold
     * @param bold9700
     * @return devuelve el valor correspodiente al modelo segun su tama�o de 
     * pantalla
     */
    public static int UbicarEnCel(int curve,int torch,int bold,int bold9700){
    	if(ScreenInfo.getScreenWidth()==320)
    		return curve;
    	if(ScreenInfo.getScreenWidth()==480){
    		if(ScreenInfo.getScreenHeight()==320)
    			return bold;
    		else 
    			return bold9700;
    	}
    	
    	return torch;
    }
	
	
}
