package com.peg.blackberry.planetaboca.ui.field;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

import com.peg.blackberry.planetaboca.util.Colores;
import com.pegsoluciones.blackberry.common.util.BitmapUtil;
import com.pegsoluciones.blackberry.common.util.GraphicUtil;

public abstract  class BotoncitoImagenField extends Field {
	
	Bitmap imagen;
	int colorFondo = Color.WHITE;

	public BotoncitoImagenField(String pathImagen) {
		super(FOCUSABLE);
		imagen = BitmapUtil.getImagen(pathImagen);
	}

	protected void layout(int width, int height) {
		setExtent(imagen.getWidth() + 4, imagen.getHeight() + 4);
	}

	protected void paint(Graphics g) {
		g.setBackgroundColor(colorFondo);
		g.clear();
		
		GraphicUtil.drawBitmap(g, imagen, 2, 2);
	}
	
	public abstract void onClick();
	
	public boolean trackwheelClick(int time, int status){
		onClick();
		return true;
	}

	protected void onFocus(int direction) {
		colorFondo = Colores.AMARILLO_BARRA;
		super.onFocus(direction);
	}

	protected void onUnfocus() {
		colorFondo = Color.WHITE;
		super.onUnfocus();
		invalidate();
	}

	
}
