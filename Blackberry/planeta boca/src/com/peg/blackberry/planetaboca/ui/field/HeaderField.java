package com.peg.blackberry.planetaboca.ui.field;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;

import com.peg.blackberry.planetaboca.application.ApplicationEvent;
import com.peg.blackberry.planetaboca.ui.manager.ListadoDeportesManager;
import com.peg.blackberry.planetaboca.util.Colores;
import com.pegsoluciones.blackberry.common.device.ScreenUtil;
import com.pegsoluciones.blackberry.common.event.EventManager;
import com.pegsoluciones.blackberry.common.event.EventObserver;
import com.pegsoluciones.blackberry.common.ui.screen.ExPupUpScreen;
import com.pegsoluciones.blackberry.common.util.BitmapUtil;
import com.pegsoluciones.blackberry.common.util.GraphicUtil;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;

public class HeaderField extends Field implements EventObserver {
	
	
	private static final int colorFocus 	= Colores.AMARILLO_BARRA;
	private static final int colorUnFocus	= Colores.GRISECITO;
	private int colorFont = colorUnFocus;
	private String nombreSecion = "HOME"; 
	private static final int SIZE_FONT = PosicionesUtil.UbicarEn(16, 18, 22, 14);
	
	static private Bitmap fondo =  BitmapUtil.getImagenParaEsteEquipo("header.png");
	public static final int ALTO 			= fondo.getHeight();
	
	public HeaderField() {
		super(FOCUSABLE);
		EventManager.getInstance().addObserver(this,ApplicationEvent.CAMBIO_SECCION);
	}
	
	protected void layout(int width, int height) {
		setExtent(Display.getWidth(), ALTO);
	}

	protected void paint(Graphics g) {
		GraphicUtil.drawBitmap(g, fondo, 0, 0);

		setFont(Font.getDefault().derive(Font.BOLD,SIZE_FONT));
		
		g.setColor(colorFont);
		g.drawText(nombreSecion,Display.getWidth() - g.getFont().getAdvance(nombreSecion) - 10,
				PosicionesUtil.centrada(g.getFont().getHeight(), ALTO));
	}

	protected void onFocus(int direction) {
		colorFont = colorFocus;
		super.onFocus(direction);
	}

	protected void onUnfocus() {
		colorFont = colorUnFocus;
		super.onUnfocus();
		invalidate();
	}
		
	protected boolean trackwheelClick(int status, int time) {
		ScreenUtil.pushScreen(new ExPupUpScreen(new ListadoDeportesManager(), 
				ListadoDeportesManager.ANCHO, ListadoDeportesManager.ALTO, Color.BLACK));
		return true;
	}

	public void onEvent(String topic, Object parameter) {
		nombreSecion = (String) parameter;
		invalidate();
	}
	

}
