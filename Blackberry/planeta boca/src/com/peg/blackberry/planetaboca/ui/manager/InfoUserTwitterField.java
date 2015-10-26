package com.peg.blackberry.planetaboca.ui.manager;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;

import com.peg.blackberry.planetaboca.domain.TwitterUser;
import com.peg.blackberry.planetaboca.util.PBStringUtils;
import com.pegsoluciones.blackberry.common.util.GraphicUtil;
import com.pegsoluciones.blackberry.common.util.Point;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;
import com.pegsoluciones.blackberry.common.web.DescargaImagenesManager;
import com.pegsoluciones.blackberry.common.web.EsperaImagenEncodedInterface;
import com.pegsoluciones.blackberry.common.web.ImagesDownloader;

public class InfoUserTwitterField extends Field implements EsperaImagenEncodedInterface {
	
	private static final int MARGEN_SUPERIOR = 10;
	private static final int MARGEN_LATERAL = 10;
	private static final Font FONT_DETALLE = Font.getDefault().derive(Font.BOLD, 
			PosicionesUtil.UbicarEn(16, 18, 18, 0));
	private static final Font FONT_USER = Font.getDefault().derive(Font.BOLD, 
			PosicionesUtil.UbicarEn(18, 20, 20, 0));
	private static final int ESPACIO_ENTRE__USER_Y_DETALLE = 12;
	private int alto;
	private int LADO_IMAGEN = 75;
	private Bitmap imagen;
	private TwitterUser tU;
	private int xText;

	public InfoUserTwitterField(TwitterUser tU) {
		super(FOCUSABLE);
		DescargaImagenesManager.descargarImagenThreadEncoded(tU.getUrlAvatar(), this);
		this.tU = tU;
		
		Graphics g = new Graphics(new Bitmap(100,100));
		g.setFont(FONT_DETALLE);
		int cantRengolones = PBStringUtils.drawTituloPixel(g, xText,MARGEN_SUPERIOR,
				tU.getDescripcion(),Display.getWidth() - xText - 20 - LADO_IMAGEN,10);		
		alto = MARGEN_SUPERIOR * 2 + 
			   cantRengolones * (FONT_DETALLE.getHeight() + FONT_DETALLE.getLeading())
			   + ESPACIO_ENTRE__USER_Y_DETALLE + FONT_USER.getHeight();
	}

	protected void layout(int width, int height) {
		setExtent(Display.getWidth(),alto);
	}

	public void paint(Graphics g) {
		g.setBackgroundColor(Color.WHITE);
		g.clear();		
		
		
		if(imagen != null){
			GraphicUtil.drawBitmap(g, imagen, MARGEN_LATERAL, MARGEN_SUPERIOR);
			xText = 2 * MARGEN_LATERAL + LADO_IMAGEN;
		}else
			xText = MARGEN_LATERAL;
		
		g.setColor(Color.BLACK);
		
		g.setFont(FONT_USER);
		g.drawText(tU.getNombre(),xText,MARGEN_SUPERIOR);
		
		g.setFont(FONT_DETALLE);
		int maxPixelAncho = Display.getWidth() - xText - 10;
		PBStringUtils.drawTituloPixel(g, xText,MARGEN_SUPERIOR + FONT_USER.getHeight() 
				+ ESPACIO_ENTRE__USER_Y_DETALLE,tU.getDescripcion(),
				maxPixelAncho,10);
	}

	public void catchImagen(EncodedImage img, String ruta) {		
		if(img != null){
			Point sizePara = GraphicUtil.getSizePara(img.getWidth(),
					img.getHeight(), LADO_IMAGEN, LADO_IMAGEN);
			imagen = ImagesDownloader.scale(img, (int) sizePara.getX(),
					(int) sizePara.getY()).getBitmap();
			invalidate();
		}
	}
	
	protected boolean trackwheelClick(int status, int time) {	
		return true;
	}

}
