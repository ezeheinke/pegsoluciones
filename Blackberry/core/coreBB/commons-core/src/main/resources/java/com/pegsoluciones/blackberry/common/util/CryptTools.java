package com.pegsoluciones.blackberry.common.util;

import net.rim.device.api.crypto.MD5Digest;


/**
 * @author poza pablo 
 * 
 * herramientas para encriptar y seguridad
 */
public class CryptTools {
    
    /**
     * @param input
     * @return devuelve el MD5 de input
     */
    public static String getMD5( String input ) {

        MD5Digest md5 = new MD5Digest();
        byte[] buff = new byte[ input.length() ];
        for( int i = 0; i < input.length(); i++ ) {
            buff[ i ] = (byte) input.charAt( i );
        }
        md5.update( buff, 0, input.length() );
        byte[] res =  new byte[ 16 ];
        int count = md5.getDigest( res, 0, false );

        String strRes = "";
        for (int i = 0; i < count; i++) {
            String resAux = Integer.toHexString( (int) res[ i ] );
            if (resAux.length() == 1 ) {
                resAux = "0" + resAux;
            }
            resAux = resAux.substring( resAux.length() - 2 );
            strRes += resAux;
        }

        return strRes;

    }    
    
} 


