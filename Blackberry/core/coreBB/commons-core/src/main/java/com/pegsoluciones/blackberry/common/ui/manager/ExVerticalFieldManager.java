package com.pegsoluciones.blackberry.common.ui.manager;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.pegsoluciones.blackberry.common.ui.ScreenInfo;



/**
 * @author poza pablo
 *  VerticalFieldManager con tama�o fijo
 *
 */
public class ExVerticalFieldManager extends VerticalFieldManager {

	int w,h;
	public String nombreManager = null;
	
	/**devuelve un Horizontal de w x h
	 */	
	public ExVerticalFieldManager(int w,int h){
		super(Manager.FOCUSABLE | Manager.VERTICAL_SCROLL);
		this.w = w;
		this.h = h;
				
	}
	

	/**devuelve un Horizontal de w x h, usando los flags 
	 * para constructor del manager
	 */		
	public ExVerticalFieldManager(int w,int h, long flags){
		super(flags);
		this.w = w;
		this.h = h;
		
	}
	
	/**decuelve un vertical del ancho de la pantalla y del alto de la pantalla menos y
	 * @param y
	 */
	public ExVerticalFieldManager(int y) {
		this(ScreenInfo.getScreenWidth(), ScreenInfo.getScreenHeight() - y);
	}

	protected void sublayout(int width, int height) {
		super.sublayout(w, h);
		setExtent(w,h);
	}

	public void add(Vector v){
		for(int i = 0 ; i < v.size(); i++)
			add((Field) v.elementAt(i));	
	}


	public int getW() {
		return w;
	}


	public void setW(int w) {
		this.w = w;
	}


	public int getH() {
		return h;
	}


	public void setH(int h) {
		this.h = h;
	}
		
	
}
