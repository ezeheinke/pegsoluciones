package com.pegsoluciones.blackberry.common.util;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;

public abstract class FontUtil {



	/**
	 * OJO!!
	 * Tira exception si la familia no es correcta, la agarra adentro.
	 * 
	 * */
	public static Font getFont(String family,int style, int size){
		FontFamily ff = null;
      
		try {
             ff = FontFamily.forName( family );
        }
        catch( Exception ex ) {
        	// Ver q conviene hacer con esta excepcion
        }
        
        Font fnt = null;
        if ( ff != null ) 
            fnt = ff.getFont( style, size );
          
        return fnt;
        
	}

	public static void println(String s) {
		System.out.println(s);		
	}



	public static void setDefaultFont(String family, int style, int size) {
	 	   FontFamily f = null;
	       try {
	            f = FontFamily.forName( family );	    	
	       }
	       catch( Exception ex ) {
	       }
	       if ( f != null ) {
	           Font fnt = f.getFont( style, size);
	           Font.setDefaultFont( fnt );
	       }		
	}	
	
}
