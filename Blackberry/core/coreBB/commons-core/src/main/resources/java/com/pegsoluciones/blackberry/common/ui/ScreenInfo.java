package com.pegsoluciones.blackberry.common.ui;

import java.util.Vector;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.XYPoint;

public class ScreenInfo {

    private static String   _resDir;
    
    public static void setResDir(String resDir) {
		ScreenInfo._resDir = resDir;
	}


	private static XYPoint  _screenSize;

    public static Vector getSupportedSizes() {

        Vector sizes = new Vector();
        sizes.addElement( new XYPoint( 240, 260 ) );
        sizes.addElement( new XYPoint( 320, 240 ) );
        sizes.addElement( new XYPoint( 360, 480 ) );
        sizes.addElement( new XYPoint( 480, 320 ) );
        sizes.addElement( new XYPoint( 480, 360 ) );
        return sizes;
    
    }

    public static XYPoint getScreenSize() {

        if ( _screenSize != null ) {
            return _screenSize;
        }

        // Calcula el dir de recursos a usar.
        int h = Display.getHeight();//Graphics.getScreenHeight(); 
        int w = Display.getWidth();//Graphics.getScreenWidth();
                
        // Busca el mayor tama�o que matchee.
        XYPoint max = null;
        int maxProdPix = 0;
        Vector sizes = getSupportedSizes();
        for (int i = 0; i < sizes.size(); i++) {
            XYPoint size = (XYPoint) sizes.elementAt( i );
            int prodPix = size.x * size.y;
            if ( size.x <= w && size.y <= h ) {
                // Este tama�o calza.
                if ( max == null || prodPix > maxProdPix ) {
                    max = size;
                    maxProdPix = prodPix;
                }
            }
        }
        _screenSize = max;
        return _screenSize;
        
    }
    
    public static int getScreenWidth() {
        return getScreenSize().x;
    }

    public static int getScreenHeight() {
        return getScreenSize().y;
    }
    
    public static boolean isScreenSize( int width, int height ) {
        return ( getScreenWidth() == width && getScreenHeight() == height );
    }
    
    public static String getResDir() {
    
        if ( _resDir != null ) {            
            return _resDir;
        }

        throw new RuntimeException("El directorio de imagenes no fue especificad" 
        		+ " en el Screeninfo");    
    }

   
    public static int HalfWidth(){
        return getScreenSize().x / 2;    
    }

} 
