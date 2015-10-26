package com.pegsoluciones.blackberry.common.ui.field;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

import com.pegsoluciones.blackberry.common.ui.ScreenInfo;


/**
 * @author poza pablo
 * Linea que herada de field, sirve como separador por ejemplo
 *
 */
public class Linea extends Field{

	private int color;
	private int fondo;
	private int w;
	private int h;
	private int margen;

	public Linea(int color,int fondo) {
		this(color,fondo,ScreenInfo.getScreenWidth(),1,0,0);
	}
	
	
	/**
	 * @param color
	 * @param fondo
	 * @param w
	 * @param h
	 * @param margen
	 * @param flag de field
	 */
	public Linea(int color,int fondo,int w,int h,int margen,long flag) {
		super(flag);
		this.color = color;
		this.fondo = fondo;
		this.w = w;
		this.h = h;
		this.margen = margen;
	}

	public Linea(int color,int fondo,int w,int margen,long flag) {
		this(color,fondo,w,1,margen,flag);
	}	
	
	protected void layout(int width, int height){
		setExtent(w,h);
	}

	protected void paint(Graphics g){
		g.setBackgroundColor(fondo);
		g.clear();
		g.setColor(color);
		for(int i = 0 ; i < h ; i++)
			g.drawLine(margen,i , w - margen , i);
	}

}