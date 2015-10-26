package com.pegsoluciones.blackberry.common.util;

public abstract class DateUtil {
	
	public static String diasDesde(long f){
		java.util.Date utilDate = new java.util.Date();
		long miliSeg = utilDate.getTime() - f;
		return String.valueOf(miliSeg / (1000 * 60 * 60 * 24) );
	}
	
	public static int diasEntre(String desde, String hasta){
		long l1 = Long.parseLong(desde);		
		long l2 = Long.parseLong(hasta);		
		return (int) ((l2 - l1) / (24*60*60*1000)) ;
	}	

}
