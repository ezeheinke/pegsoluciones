package com.peg.blackberry.planetaboca.ui.field;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;

import com.peg.blackberry.planetaboca.domain.Twett;
import com.peg.blackberry.planetaboca.util.PBStringUtils;
import com.pegsoluciones.blackberry.common.ui.ScreenInfo;
import com.pegsoluciones.blackberry.common.util.GraphicUtil;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;
import com.pegsoluciones.blackberry.common.web.DescargaImagenesManager;
import com.pegsoluciones.blackberry.common.web.EsperaImagenInterface;

public class TwitteoField extends Field implements EsperaImagenInterface{

	
	private static final int ESPACIO_ENTRE_TWETT_Y_CREADO = 8;
	private static final int MARGEN_SUPERIOR = 10;	
	private static final int LADO_AVATAR = 50;
	private static final Font FONT_TWETT = Font.getDefault().derive(Font.BOLD, 
			PosicionesUtil.UbicarEn(14, 16, 16, 0));
	private static final Font FONT_CREADO = Font.getDefault().derive(Font.PLAIN, 
			PosicionesUtil.UbicarEn(12, 14, 14, 0));
	
	private int alto;
	private Bitmap imagen;
	private String twitteo;
	private int xText;
	private String creado;

	public TwitteoField(Twett twett){
		super(FOCUSABLE);
		DescargaImagenesManager.descargarImagenThread(twett.getUrlAvatar(), this);
		
		twitteo = twett.getText();
		creado = twett.getCreado();		
		
		Graphics g = new Graphics(new Bitmap(100,100));
		g.setFont(FONT_TWETT);
		int maxPixelAncho = Display.getWidth() - 30 - LADO_AVATAR;
		int cantRengolones = PBStringUtils.drawTituloPixel(g, xText,MARGEN_SUPERIOR,twitteo
				,maxPixelAncho,10);
		alto = MARGEN_SUPERIOR * 2 + 
			   cantRengolones * (FONT_TWETT.getHeight() + FONT_TWETT.getLeading()) +
			   ESPACIO_ENTRE_TWETT_Y_CREADO + FONT_CREADO.getHeight();
		
		alto = alto < LADO_AVATAR + 2 * MARGEN_SUPERIOR ? LADO_AVATAR + 2 * MARGEN_SUPERIOR : alto;
	}
	
	protected void layout(int arg0, int arg1) {
		setExtent(ScreenInfo.getScreenWidth(),alto); 
	}

	public void paint(Graphics g) {
		g.setBackgroundColor(Color.WHITE);
		g.clear();

		
		if(imagen != null){
			GraphicUtil.drawBitmap(g, imagen, 10, MARGEN_SUPERIOR);
			xText = 10 + LADO_AVATAR + 10;
		}else
			xText = 10; 
		
		g.setColor(Color.BLACK);
		g.setFont(FONT_TWETT);
		int maxPixelAncho = Display.getWidth() 
				- xText - 10;
		int renglones = PBStringUtils.drawTituloPixel(g, xText,MARGEN_SUPERIOR,
				twitteo,maxPixelAncho,10);
		
		g.setFont(FONT_CREADO);		
		int yCreado = MARGEN_SUPERIOR + renglones * (FONT_TWETT.getHeight() + FONT_TWETT.getLeading())
					  + ESPACIO_ENTRE_TWETT_Y_CREADO;
		
		// Si creado se dibuja al costdo de la imagen o debajo
		int xCreado = yCreado <= MARGEN_SUPERIOR + LADO_AVATAR 
			- ESPACIO_ENTRE_TWETT_Y_CREADO - FONT_CREADO.getHeight()
			? xText : 10;
		g.drawText(creado,xCreado, yCreado);
	}

	public void catchImagen(Bitmap b, String ruta) {
		if(b != null){
			imagen = b;
			invalidate();
		}		
	}
	
	protected boolean trackwheelClick(int status, int time) {	
		return true;
	}
}
