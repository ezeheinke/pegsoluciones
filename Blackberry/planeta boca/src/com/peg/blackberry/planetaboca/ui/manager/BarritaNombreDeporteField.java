package com.peg.blackberry.planetaboca.ui.manager;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

import com.pegsoluciones.blackberry.common.util.PosicionesUtil;

public class BarritaNombreDeporteField extends Field {

	public static final int ALTO = 40;
	private static final int MARGEN_X = 10;
	private String nombreDeporte;

	public BarritaNombreDeporteField(String nombreDeporte) {
		this.nombreDeporte = nombreDeporte;
	}

	protected void layout(int width, int height) {
		setExtent(Display.getWidth(), ALTO);
	}

	protected void paint(Graphics g) {		
		g.setBackgroundColor(Color.BLUE);
		g.clear();
		
		g.setColor(Color.BLACK);
		g.drawText(nombreDeporte,MARGEN_X,PosicionesUtil.centrada(g.getFont().getHeight(), ALTO));
	}

}
