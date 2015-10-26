package com.pegsoluciones.blackberry.common.ui.manager;


import net.rim.device.api.system.Display;
import net.rim.device.api.system.GIFEncodedImage;
import net.rim.device.api.ui.Manager;

import com.pegsoluciones.blackberry.common.ui.ScreenInfo;
import com.pegsoluciones.blackberry.common.ui.field.AnimatedGIFField;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;


public class IntroGifManager extends Manager{

	private AnimatedGIFField gif;
	
	public IntroGifManager(String imagen) {
		
		super(FOCUSABLE);
		
		GIFEncodedImage image = (GIFEncodedImage) 
			GIFEncodedImage.getEncodedImageResource(imagen);
		
		gif = new AnimatedGIFField(image,0);
		
		add(gif);
		
	}

	protected void sublayout(int width, int height) {
	
		setPositionChild(gif,PosicionesUtil.xCentrada(gif.getBitmapWidth()),
				Display.getHeight() - PosicionesUtil.UbicarEnCel(10,30,20,30) 
				- gif.getBitmapHeight());
		
		layoutChild(gif,gif.getBitmapWidth(),gif.getBitmapHeight());
			
		setExtent(ScreenInfo.getScreenWidth(),ScreenInfo.getScreenHeight());
		
		
	}

}
