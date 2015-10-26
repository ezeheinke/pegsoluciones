package com.pegsoluciones.blackberry.common.ui.field;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.container.HorizontalFieldManager;

import com.pegsoluciones.blackberry.common.util.GraphicUtil;

/**
 * @author poza pablo
 * es una textbox con scroll horizontal
 *
 */
public class ExHorizontalTextBoxField extends Manager{

	
	public Graphics gActual;
	private HorizontalFieldManager h;
	private int wTextBox;
	private int hTextBox;
	private Bitmap fondo;
	private int margenX = 3;
	private int margenY = 3;
	int widthField;
	private int heightField;
	EditField f;
	private boolean conMarco;
	private int colorMarco;
	private int colorFondo;
	

	///////////////////////// Constructores //////////////////////////////////////////////
	
	
	/**
	 * @param margenX  tanto a izquierda como derecha
	 * @param margenY  tanto arriba como abajo
	 * @param max    de caracteres
	 * @param fondo el field tomara en
	 * 
	 */

	public ExHorizontalTextBoxField(int wField, int hField, int margenX , int margenY, int max,Font font, String textInicial,int colorMarco,int colorFondo,final int colorFont){
		super(FOCUSABLE);
		h = new HorizontalFieldManager(HORIZONTAL_SCROLL | FOCUSABLE);
		f = new EditField("",textInicial){
			
			public void paint(Graphics g){
				g.setColor(colorFont);
				super.paint(g);
	
			} 
			
			public boolean keyChar(char key,int status, int time){
				if(Keypad.KEY_ENTER == key)
					return true; 
				else
					return super.keyChar(key, status, time);
			}
			
			
		};
		f.setFont(font);
		f.setMaxSize(max);
		
		h.add(f);
		add(h);
		this.widthField = wField;
		this.heightField = hField;
		this.margenX = margenX;
		this.margenY = margenY;
		this.wTextBox = this.widthField - this.margenX*2;
		this.hTextBox = this.heightField - this.margenY*2;
		this.conMarco = true;
		this.colorMarco = colorMarco;
		this.colorFondo = colorFondo;
	}
	
	public ExHorizontalTextBoxField(int wField, int hField, int margenX ,
			int margenY, int max,Font font,int colorMarco,int colorFondo,int colorFont) {
		
		this(wField,hField,margenX,margenY,max,font,"",colorMarco,colorFondo,colorFont);
	}
	
	public ExHorizontalTextBoxField(int margenX, int margenY, int max, Bitmap fondo,
			int colorMarco,int colorFondo, int colorFont) {
		
		this(margenX,margenY,max,fondo,"",colorMarco,colorFondo,colorFont);
	}
	
	public ExHorizontalTextBoxField(int margenX, int margenY, int max, Bitmap fondo,
			String textInicial,int colorMarco,int colorFondo, int colorFont) {
		
		this(fondo.getWidth(),fondo.getWidth(),margenX,margenY,max,Font.getDefault(),textInicial,colorMarco,colorFondo,colorFont);	
		this.fondo = fondo;
	}	

	
	///////////////////////////////////////////////////////////////////////////////
	

	public void paint(Graphics g){	
		//g.setBackgroundColor(colorFondo);
	//	g.clear();
		if(fondo != null)
			GraphicUtil.drawBitmap(g, fondo, 0,0);
		
		if(conMarco){
			g.setColor(colorFondo);		
			//Borde Redondo
			//g.fillRoundRect(0, 0, widthField, heightField, margenX*3, margenY*3);
			
			//Borde Cuadrado
			g.fillRect(0, 0, widthField, heightField);
			
			
			g.setColor(colorMarco);
			//Borde Redondo
			//g.drawRoundRect(0, 0, widthField, heightField, margenX*3, margenY*3);	
			
			//Borde Cuadrado
			g.drawRect(0, 0, widthField, heightField);			
		}
			
		super.paint(g);
	}
	
	protected void sublayout(int width, int height) {
		setPositionChild(h,margenX,margenY);
		layoutChild(h,wTextBox,hTextBox);
		setExtent(widthField,heightField);
	}
	
	
	public void setText(String s){
		f.setText(s);
	}
	
	public String getText(){
		return f.getText();
	}
	
	public int getAlto(){
		return heightField;
	}

}
