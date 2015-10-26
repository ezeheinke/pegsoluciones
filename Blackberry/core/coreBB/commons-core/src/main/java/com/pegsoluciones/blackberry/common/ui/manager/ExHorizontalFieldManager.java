package com.pegsoluciones.blackberry.common.ui.manager;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.HorizontalFieldManager;

import com.pegsoluciones.blackberry.common.ui.ScreenInfo;

public class ExHorizontalFieldManager extends HorizontalFieldManager {

	int w,h;
	public String nombreManager = null;
	
	/**
	 * devuelve horizontal de w x h
	 * */
	public ExHorizontalFieldManager(int w,int h){
		super(Manager.FOCUSABLE | Manager.HORIZONTAL_SCROLL);
		this.w = w;
		this.h = h;
		
	}

	/**devuelve un Horizontal del ancho de la pantalla y del alto de la pantalla menos h
	 * @param h
	 */
	public ExHorizontalFieldManager(int h) {
		this(ScreenInfo.getScreenWidth(), ScreenInfo.getScreenHeight() - h);
	}

	protected void sublayout(int width, int height) {
		super.sublayout(w,h);
		setExtent(w,h);
	}
	
	public void addElement(Vector v){
		for(int i = 0 ; i < v.size(); i++)
			add((Field) v.elementAt(i));		
	}

}
