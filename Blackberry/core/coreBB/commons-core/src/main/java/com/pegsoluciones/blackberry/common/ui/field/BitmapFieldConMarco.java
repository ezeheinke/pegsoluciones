package com.pegsoluciones.blackberry.common.ui.field;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.BitmapField;

import com.pegsoluciones.blackberry.common.util.GraphicUtil;



/**
 * @author poza pablo
 * 
 * Bitmap field que dibuja marco al hacerle foco
 *
 */
public class BitmapFieldConMarco extends BitmapField {

	private int colorMarco;
	private String pathFoto;

	/**
	 * @param b Bitmap
	 * @param colorMarco
	 * @param pathFoto
	 */
	public BitmapFieldConMarco(Bitmap b,int colorMarco, String pathFoto) {
		super(b,Field.FOCUSABLE);
		this.colorMarco = colorMarco;
		this.pathFoto = pathFoto;
	}
	
	/**
	 * @param colorMarco
	 */
	public BitmapFieldConMarco(int colorMarco) {
		super(null,Field.FOCUSABLE);
		this.colorMarco = colorMarco;
	}

	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}

	public void paint(Graphics g){		
		super.paint(g);
		if(isFocus()){
			int w = this.getBitmapWidth();
			int h = this.getBitmapHeight();
			GraphicUtil.drawMarco(g,w,h,colorMarco);
		}
	}

	
	public void onUnfocus(){
		invalidate();
		super.onUnfocus();
	}

}
