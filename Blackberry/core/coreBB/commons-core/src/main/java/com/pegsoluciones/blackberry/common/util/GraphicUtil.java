package com.pegsoluciones.blackberry.common.util;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Graphics;

public abstract class GraphicUtil {

	/**
	 * Dibuja de la base para abajo
	 * 
	 * */
	public static void drawTriangulo(Graphics g,int color,int x,int y,int w){
		int colorAnterior = g.getColor();
		
		g.setColor(color);
	
		while( w > 2)
			g.drawLine(x, y, x++ + (w-=2), y++);
		
		g.setColor(colorAnterior);
	}
	
	public static int getAdvance(Graphics g,String s){
		return g.getFont().getAdvance(s);
	}

	public static void drawMarco(Graphics g, int w, int h, int colorMarco) {
		int lastColor = g.getColor();
		g.setColor(colorMarco);
		g.drawLine(0, 0, 0, h);
		g.drawLine(0, 0, w, 0);
		g.drawLine(w-1, 0, w-1, h);
		g.drawLine(0, h-1, w, h-1);
		g.setColor(lastColor);
	}
	
    public static void drawBitmap(Graphics g, Bitmap b , int x, int y){
    	g.drawBitmap(x,y,b.getWidth(),b.getHeight(), b, 0, 0);
    }	
    
	public static void setSizeFont(Graphics g, int size) {
		g.setFont(g.getFont().derive(g.getFont().getStyle(),size));		
	}
  
	public static void setStyleFont(Graphics g, int style) {
		g.setFont(g.getFont().derive(style));
	}

	public static Point getSizePara(int w, int h, int wContenedor,int hContenedor) {
		
		double proporcionW = (double)wContenedor / (double)w;		
		double proporcionH = (double)hContenedor / (double)h;
		
		if(proporcionW < proporcionH)
			return new Point(proporcionW*(double)w,proporcionW*(double)h);
		return new Point(proporcionH*(double)w,proporcionH*(double)h);
	}
}
