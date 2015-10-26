package com.pegsoluciones.blackberry.common.util;

public abstract class NumberUtil {
    
	public static double truncate(double x) {       
        long y=(long)x*100;       
        double z= (double)y/100;        
        return z;    
      }    
              
  	public static int PositivoUnoNegativoMenosUno(int i) {
  		if(i > 0)
  			return 1;	
  		if(i < 0)
  			return -1;
  		return 0;
  	}
}
