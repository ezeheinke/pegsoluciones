package com.pegsoluciones.blackberry.common.ui.field;




import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

import com.pegsoluciones.blackberry.common.util.GraphicUtil;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;



/**
 * 
 *  @author poza pablo
 **/
public class ExButtonField extends Field {

	private String text;
	
	private int colorFondo;
	private int colorText;
	private int colorMarco;
	
	private int colorMarcoFocus;
	private int colorMarcoUnfocus;
	private int colorTextFocus;
	private int colorTextUnfocus;
	private int colorFondoFocus;
	private int colorFondoUnfocus;
	
	private int sizeFont;
	
	private int width;
	private int height;
	
	int y,x;
	
	private static final int MARGEN_VERTICAL = 2;
	private static final int MARGEN_HORIZONTAL = 4;
	
	
	public ExButtonField(String text, int colorMarcoFocus,
			 int colorMarcoUnfocus, int colorTextFocus, int colorTextUnfocus,
			 int colorFondoFocus, int colorFondoUnfocus,int sizeFont,long flags) {
		
		super(flags);
		this.text = text;
		this.colorMarcoFocus = colorMarcoFocus;
		this.colorMarcoUnfocus = colorMarcoUnfocus;
		this.colorTextFocus = colorTextFocus;
		this.colorTextUnfocus = colorTextUnfocus;
		this.colorFondoFocus = colorFondoFocus;
		this.colorFondoUnfocus = colorFondoUnfocus;
		this.sizeFont = sizeFont;
		
		colorFondo = colorFondoUnfocus;
		colorText = colorTextUnfocus;
		colorMarco = colorMarcoUnfocus;
		
		calcularExtent();
	}
	
	
	
	private void calcularExtent() {
		Graphics g = new Graphics(new Bitmap(0, 0));
		GraphicUtil.setSizeFont(g, sizeFont);
		height = sizeFont + 2 * MARGEN_VERTICAL;
		width = GraphicUtil.getAdvance(g, text) + 2 * MARGEN_HORIZONTAL;
		
		x = MARGEN_HORIZONTAL ;
		y = MARGEN_VERTICAL;
		
	}



	public ExButtonField(String text, int colorMarcoFocus,
				 int colorMarcoUnfocus, int colorTextFocus, int colorTextUnfocus,
				 int colorFondoFocus, int colorFondoUnfocus, int width, int height,
				 long flags,int sizeFont) {		
		
		super(flags);
		this.text = text;
		this.colorMarcoFocus = colorMarcoFocus;
		this.colorMarcoUnfocus = colorMarcoUnfocus;
		this.colorTextFocus = colorTextFocus;
		this.colorTextUnfocus = colorTextUnfocus;
		this.colorFondoFocus = colorFondoFocus;
		this.colorFondoUnfocus = colorFondoUnfocus;

		
		colorFondo = colorFondoUnfocus;
		colorText = colorTextUnfocus;
		colorMarco = colorMarcoUnfocus;		
		
		this.width = width;
		this.height = height;
		this.sizeFont = sizeFont;
		
		Graphics g = new Graphics(new Bitmap(0, 0));
		GraphicUtil.setSizeFont(g, sizeFont);		
		x = PosicionesUtil.centrada(GraphicUtil.getAdvance(g, text), width);
		y = PosicionesUtil.centrada(sizeFont, height);
		
	}
	
	
	public ExButtonField(String text, int colorMarcoFocus,
			int colorMarcoUnfocus, int colorTextFocus, int colorTextUnfocus,
			int colorFondoFocus, int colorFondoUnfocus, int width, int height,
			long flags,int sizeFont, int margin) {		
		
		super(flags);
		this.text = text;
		this.colorMarcoFocus = colorMarcoFocus;
		this.colorMarcoUnfocus = colorMarcoUnfocus;
		this.colorTextFocus = colorTextFocus;
		this.colorTextUnfocus = colorTextUnfocus;
		this.colorFondoFocus = colorFondoFocus;
		this.colorFondoUnfocus = colorFondoUnfocus;
		
		
		colorFondo = colorFondoUnfocus;
		colorText = colorTextUnfocus;
		colorMarco = colorMarcoUnfocus;		
		
		this.width = width;
		this.height = height;
		this.sizeFont = sizeFont;
		
		Graphics g = new Graphics(new Bitmap(0, 0));
		GraphicUtil.setSizeFont(g, sizeFont);		
		x = margin;
		y = PosicionesUtil.centrada(sizeFont, height);
		
	}
	
	
	
	public ExButtonField(String text, int colorMarcoFocus,
			 int colorMarcoUnfocus, int colorTextFocus, int colorTextUnfocus,
			 int colorFondoFocus, int colorFondoUnfocus, int width, int height){
		this(text, colorMarcoFocus, colorMarcoUnfocus, colorTextFocus, colorTextUnfocus, colorFondoFocus, colorFondoUnfocus, width, height,FOCUSABLE, height);
				
	}
	
	public ExButtonField(String text, int colorText, int colorFondoFocus,
			int colorFondoUnfocus, int colorMarco, int width, int height) {
		
		this(text,colorMarco,colorMarco,colorText,colorText,colorFondoFocus,
				colorFondoUnfocus,width,height);
	}

	public void onFocus(int direction){		
		colorFondo = colorFondoFocus;
		colorText = colorTextFocus;
		colorMarco = colorMarcoFocus;
		super.onFocus(direction);
	}
	
	public void onUnfocus(){
		colorFondo = colorFondoUnfocus;
		colorText = colorTextUnfocus;
		colorMarco = colorMarcoUnfocus;
		invalidate();
		super.onUnfocus();
	}

	protected void layout(int w, int h) {	
		setExtent(this.width,this.height);
	}

	protected void paint(Graphics g) {
		g.setBackgroundColor(colorFondo);
		g.clear();
		
		g.setColor(colorMarco);
		g.drawRect(0, 0, width, height);
		GraphicUtil.setSizeFont(g,sizeFont);

		
		
		g.setColor(colorText);
		g.drawText(text, x, y);		
	}	
	

	public int getAncho() {
		return width;
	}



	public int getAlto() {
		return height;
	}



	public int getColorTextFocus() {
		return colorTextFocus;
	}



	public void setColorTextFocus(int colorTextFocus) {
		this.colorTextFocus = colorTextFocus;
	}



	public int getColorTextUnfocus() {
		return colorTextUnfocus;
	}

	public void setColorTextUnfocus(int colorTextUnfocus) {
		this.colorTextUnfocus = colorTextUnfocus;
	}	
	
	public void invalidate() {
		super.invalidate();
	}
	
}
