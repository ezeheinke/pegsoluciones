package com.pegsoluciones.blackberry.common.ui.field;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.pegsoluciones.blackberry.common.util.GraphicUtil;

/**
 * @author poza pablo
 * text box con scroll vertical
 *
 */
public class ExVerticalTextBoxField extends Manager{

	
	public Graphics gActual;
	private VerticalFieldManager v;
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

	public ExVerticalTextBoxField(int wField, int hField, int margenX , int margenY, int max,Font font, String textInicial,int colorMarco,int colorFondo,final int colorFont){
		super(FOCUSABLE);
		v = new VerticalFieldManager(VERTICAL_SCROLL);
		f = new EditField("",textInicial){
			
			public void paint(Graphics g){
				g.setColor(colorFont);
				super.paint(g);
			}
			
		};
		f.setFont(font);
		f.setMaxSize(max);
		v.add(f);
		add(v);
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
	
	/**
	 * @param wField
	 * @param hField
	 * @param margenX
	 * @param margenY
	 * @param max
	 * @param font
	 * @param colorMarco
	 * @param colorFondo
	 * @param colorFont
	 */
	public ExVerticalTextBoxField(int wField, int hField, int margenX , int margenY, int max,Font font,int colorMarco,int colorFondo,int colorFont) {
		this(wField,hField,margenX,margenY,max,font,"",colorMarco,colorFondo,colorFont);
	}
	
	/**
	 * @param margenX
	 * @param margenY
	 * @param max
	 * @param fondo
	 * @param colorMarco
	 * @param colorFondo
	 * @param colorFont
	 */
	public ExVerticalTextBoxField(int margenX, int margenY, int max, Bitmap fondo,int colorMarco,int colorFondo, int colorFont) {
		this(margenX,margenY,max,fondo,"",colorMarco,colorFondo,colorFont);
	}
	
	/**
	 * @param margenX
	 * @param margenY
	 * @param max
	 * @param fondo
	 * @param textInicial
	 * @param colorMarco
	 * @param colorFondo
	 * @param colorFont
	 */
	public ExVerticalTextBoxField(int margenX, int margenY, int max, Bitmap fondo,String textInicial,int colorMarco,int colorFondo, int colorFont) {
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
			g.fillRoundRect(0, 0, widthField, heightField, margenX*3, margenY*3);
			g.setColor(colorMarco);
			g.drawRoundRect(0, 0, widthField, heightField, margenX*3, margenY*3);
		}
		super.paint(g);
	}
	
	protected void sublayout(int width, int height) {
		setPositionChild(v,margenX,margenY);
		layoutChild(v,wTextBox,hTextBox);
		setExtent(widthField,heightField);
	}
	
	
	public void setText(String s){
		f.setText(s);
	}
	
	public String getText(){
		return f.getText();
	}

}
