package com.peg.blackberry.planetaboca.ui.field;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;

import com.peg.blackberry.planetaboca.domain.Noticia;
import com.peg.blackberry.planetaboca.ui.manager.DetalleNoticiaManager;
import com.peg.blackberry.planetaboca.util.Colores;
import com.peg.blackberry.planetaboca.util.PBStringUtils;
import com.pegsoluciones.blackberry.common.screenEnManager.SimulaScreens;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;

public class NoticiaField extends Field {

	private static final Font FONT_TITULO = Font.getDefault().derive(Font.BOLD,
			PosicionesUtil.UbicarEn(16, 18, 20, 0));
	private static final Font FONT_RESUMEN = Font.getDefault().derive(Font.BOLD,
			PosicionesUtil.UbicarEn(14, 16, 18, 0));
	
	private static final Font FONT_AUTOR = Font.getDefault().derive(Font.PLAIN,
			PosicionesUtil.UbicarEn(12, 14, 14, 0));
	private static final Font FONT_COMPLEMENTO = Font.getDefault().derive(Font.PLAIN,
			PosicionesUtil.UbicarEn(12, 14, 14, 0));
	
	private static final int MARGEN_X = 10;
	private static final int MARGEN_Y = 10;
	private static final int ESPACIO_TITU_RESU = 20;
	
	private static final int ESPACIO_RESU_AUTOR = 15;
	private static final int ESPACIO_AUTOR_COMPLEMENTO = 5;
	
	private Noticia noticia;
	private int colorFuente = Color.BLACK;
	private int colorComplementos = Colores.AZULCITO;
	private int alto; 

	public NoticiaField(Noticia noticia) {
		super(FOCUSABLE);
		this.noticia = noticia;
		
		int r1 = PBStringUtils.drawTituloPixel(new Graphics(new Bitmap(0, 0)), MARGEN_X,
				MARGEN_Y, noticia.getTitulo(),Display.getWidth() - 2 * MARGEN_X, 2);
		
		int r2 = PBStringUtils.drawTituloPixel(new Graphics(new Bitmap(0, 0)), MARGEN_X,
				MARGEN_Y, noticia.getResumen(),Display.getWidth() - 2 * MARGEN_X, 2);
		
			
		alto = MARGEN_Y * 2 + r1 * FONT_TITULO.getHeight() + ESPACIO_TITU_RESU 
			+ r2 * FONT_RESUMEN.getHeight();
		
		alto += ESPACIO_RESU_AUTOR + FONT_AUTOR.getHeight() + ESPACIO_AUTOR_COMPLEMENTO 
		+ FONT_COMPLEMENTO.getHeight();
	}

	protected void layout(int width, int height) {
		setExtent(Display.getWidth(),alto);
	}

	protected void paint(Graphics g) {
		
		g.setBackgroundColor(Color.WHITE);
		g.clear();
				
		g.setColor(colorFuente);
		
		g.setFont(FONT_TITULO);
		
		int r = PBStringUtils.drawTituloPixel(g, MARGEN_X, MARGEN_Y, noticia.getTitulo(),
				Display.getWidth() - 2 * MARGEN_X, 2);
		
		int y = r * FONT_TITULO.getHeight() + ESPACIO_TITU_RESU;
		g.setFont(FONT_RESUMEN);
		
		int r2 = PBStringUtils.drawTituloPixel(g, MARGEN_X, y, noticia.getResumen(),
				Display.getWidth() - 2 * MARGEN_X, 2);
		
		g.setFont(FONT_AUTOR);
		y += ESPACIO_RESU_AUTOR + r2 * FONT_RESUMEN.getHeight();
		
		g.drawText("Por: " + noticia.getAutor(),MARGEN_X, y);
		
		g.setFont(FONT_COMPLEMENTO);
		g.setColor(colorComplementos);
		//aumentamos la y
		
		y = y +  ESPACIO_AUTOR_COMPLEMENTO  + FONT_AUTOR.getHeight(); 
		
		g.drawText("Fecha: "+ noticia.getFecha(), MARGEN_X, y);
		
		g.drawText("Comentarios: "+ noticia.getCantComentarios(), Display.getWidth()
				- MARGEN_X - FONT_COMPLEMENTO.getAdvance("Comentarios: "
				+ noticia.getCantComentarios()) , y);
		
			
	}

	protected void onFocus(int direction) {		
		colorFuente = Colores.AZUL_BARRA;
		colorComplementos = Colores.AZUL_BARRA;
		super.onFocus(direction);
	}

	protected void onUnfocus() {
		colorFuente = Color.BLACK;
		colorComplementos = Colores.AZULCITO;
		super.onUnfocus();
		invalidate();
	}

	public boolean trackwheelClick(int time, int status){
		SimulaScreens.pushManager(new DetalleNoticiaManager(noticia));
		return true;
	}
	
}
