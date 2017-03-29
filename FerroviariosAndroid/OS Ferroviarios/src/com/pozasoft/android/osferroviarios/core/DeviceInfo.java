package com.pozasoft.android.osferroviarios.core;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class DeviceInfo {
	
	private static Display display;
	private static int width;
	private static int height;

	public static int getScreenWidth(Context context) {
		if(display == null){
			WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			display = wm.getDefaultDisplay();;
			width = display.getWidth();
			height =display.getHeight();			
		}
		return width;
	}
	
	public static int getScreenHeight(Context context) {
		if(display == null){
			WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			display = wm.getDefaultDisplay();;
			width = display.getWidth();
			height =display.getHeight();			
		}
		return height;
	}

}
