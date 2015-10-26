package com.pegsoluciones.blackberry.common.web;


import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

import net.rim.device.api.math.Fixed32;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;

import com.pegsoluciones.blackberry.common.util.CryptTools;

public class ImagesDownloader extends Thread {
    
   

    private String getImageName( String url ) {
                
        String imgName = "img" + CryptTools.getMD5( url ) + ".tmp";
        
        return imgName;
    
    }




    public static EncodedImage imageFromFile( String fileName ) 
        throws IOException {
        
        FileConnection file = (FileConnection) Connector.open( "file:///" + fileName );
        if (!file.exists()) {
            return null;
        }
        
        InputStream in = file.openInputStream();
        int len = (int) file.fileSize();
        byte[] buffer = new byte[ len ];
        in.read( buffer, 0, len );
      
        EncodedImage image = EncodedImage.createEncodedImage( buffer, 0, len );
        return image;
    
    }
    
    public static Bitmap bitmapFromFile( String fileName ) {
    
    	
    	
    FileConnection file = null;
	try {
		file = (FileConnection) Connector.open( "file:///" + fileName );
	} catch (IOException e) {
		e.printStackTrace();
	}
    if (!file.exists()) {
        return null;
    }
    
    InputStream in = null;
	try {
		in = file.openInputStream();
	} catch (IOException e) {
		// 
		e.printStackTrace();
	}
    int len = 0;
	try {
		len = (int) file.fileSize();
	} catch (IOException e) {
		// 
		e.printStackTrace();
	}
    byte[] buffer = new byte[ len ];
    try {
		in.read( buffer, 0, len );
	} catch (IOException e) {
		// 
		e.printStackTrace();
	}
  
    EncodedImage image = EncodedImage.createEncodedImage( buffer, 0, len );
    return image.getBitmap();

}
    
    
    public static Bitmap bitmapFromFileWithWidth( String fileName, int width ) {
        
    	
    	
        FileConnection file = null;
    	try {
    		file = (FileConnection) Connector.open( "file:///" + fileName );
    	} catch (IOException e) {
    		// 
    		e.printStackTrace();
    	}
        if (!file.exists()) {
            return null;
        }
        
        InputStream in = null;
    	try {
    		in = file.openInputStream();
    	} catch (IOException e) {
    		// 
    		e.printStackTrace();
    	}
        int len = 0;
    	try {
    		len = (int) file.fileSize();
    	} catch (IOException e) {
    		// 
    		e.printStackTrace();
    	}
        byte[] buffer = new byte[ len ];
        try {
    		in.read( buffer, 0, len );
    	} catch (IOException e) {
    		// 
    		e.printStackTrace();
    	}
      
        EncodedImage image = EncodedImage.createEncodedImage( buffer, 0, len );
        return ImagesDownloader.scale(image,width,image.getHeight()).getBitmap();

    }    

    
    public static Bitmap bitmapFromFileWithSize( String fileName, int width, int height ) {
        
    	
    	
        FileConnection file = null;
    	try {
    		file = (FileConnection) Connector.open( "file:///" + fileName );
    	} catch (IOException e) {
    		// 
    		e.printStackTrace();
    	}
        if (!file.exists()) {
            return null;
        }
        
        InputStream in = null;
    	try {
    		in = file.openInputStream();
    	} catch (IOException e) {
    		// 
    		e.printStackTrace();
    	}
        int len = 0;
    	try {
    		len = (int) file.fileSize();
    	} catch (IOException e) {
    		// 
    		e.printStackTrace();
    	}
        byte[] buffer = new byte[ len ];
        try {
    		in.read( buffer, 0, len );
    	} catch (IOException e) {
    		// 
    		e.printStackTrace();
    	}
      
        EncodedImage image = EncodedImage.createEncodedImage( buffer, 0, len );
        return ImagesDownloader.scale(image,width,height).getBitmap();

    }        

    public static EncodedImage scale( EncodedImage image, int width, int height ) {
        EncodedImage result = null;
        
        int currentWidthFixed32 = Fixed32.toFP(image.getWidth());
        int currentHeightFixed32 = Fixed32.toFP(image.getHeight());
        
        int requiredWidthFixed32 = Fixed32.toFP(width);
        int requiredHeightFixed32 = Fixed32.toFP(height);
        
        int scaleXFixed32 = Fixed32.div(currentWidthFixed32,
            requiredWidthFixed32);
        int scaleYFixed32 = Fixed32.div(currentHeightFixed32,
            requiredHeightFixed32);
        
        result = image.scaleImage32(scaleXFixed32, scaleYFixed32);
        return result;
    }


    public static EncodedImage scaleH( EncodedImage image, int height ) {
        EncodedImage result = null;
        
        int currentWidthFixed32 = Fixed32.toFP(image.getWidth());
        int currentHeightFixed32 = Fixed32.toFP(image.getHeight());
        
        //int requiredWidthFixed32 = Fixed32.toFP(width);
        int requiredHeightFixed32 = Fixed32.toFP(height);
        
        int scaleXFixed32 = Fixed32.div(currentWidthFixed32, requiredHeightFixed32);
        int scaleYFixed32 = Fixed32.div(currentHeightFixed32, requiredHeightFixed32);
        
        result = image.scaleImage32(scaleXFixed32, scaleYFixed32);
        return result;
    }
    
} 
