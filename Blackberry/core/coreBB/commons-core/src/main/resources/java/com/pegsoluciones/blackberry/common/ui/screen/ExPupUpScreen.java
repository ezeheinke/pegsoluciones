package com.pegsoluciones.blackberry.common.ui.screen;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.container.FullScreen;

import com.pegsoluciones.blackberry.common.util.GraphicUtil;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;

public class ExPupUpScreen extends FullScreen {

	private Field manager;
	private int w;
	private int h;
	private int colorMarco;
	private boolean conMarco = false;

	public ExPupUpScreen(Field m,int w,int h){
		super(DEFAULT_CLOSE | DEFAULT_MENU);
		manager = m;
		this.w = w;
		this.h= h;
		add(manager);
	}

	public ExPupUpScreen(Field m,int w,int h,int colorMarco){
		super(DEFAULT_CLOSE | DEFAULT_MENU);
		manager = m;
		this.w = w;
		this.h= h;
		add(manager);
		this.colorMarco = colorMarco;
		this.conMarco  =true;
	}	
	
	protected void sublayout(int width, int height) {
		super.sublayout(width, height);
		setPosition(PosicionesUtil.xCentrada(w),PosicionesUtil.yCentrada(h));
		setExtent(w,h);
	}
	

	public void paint(Graphics g){
		super.paint(g);
		GraphicUtil.drawMarco(g, w, h, colorMarco);
	}
}
