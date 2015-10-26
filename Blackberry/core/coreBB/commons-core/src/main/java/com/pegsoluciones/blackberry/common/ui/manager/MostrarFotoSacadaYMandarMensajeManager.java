 package com.pegsoluciones.blackberry.common.ui.manager;


import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;

import com.pegsoluciones.blackberry.common.device.ScreenUtil;
import com.pegsoluciones.blackberry.common.ui.ScreenInfo;
import com.pegsoluciones.blackberry.common.ui.field.ImageButtonField;
import com.pegsoluciones.blackberry.common.util.GraphicUtil;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;
import com.pegsoluciones.blackberry.common.web.ImagesDownloader;

/**
 * @author poza pablo
 *
 *	Manager que muestra la foto sacada, con un boton aceptar y cancelar, 
 *	sobrescribiendo el metodo actionAceptar se cambia el comportamiento al apretar
 *	aceptar
 */
public class MostrarFotoSacadaYMandarMensajeManager extends Manager {

	public static int margen = 20;
	public static int w = ScreenInfo.getScreenWidth() - 2*margen ;
	public static int h = ScreenInfo.getScreenHeight() - 40 ;
	public static int wFoto = w - 40 ;
	public static int hFoto = ScreenInfo.getScreenHeight() - 130 ;
	public static int sizeFont = PosicionesUtil.UbicarEn(14, 15, 16, 12);
	Bitmap b;
	private ImageButtonField aceptar;
	private ImageButtonField cancelar;
	private int hBoton = PosicionesUtil.ubicarSegunSO(30, PosicionesUtil.UbicarEn(30, 25, 50, 12));
	private int wBoton = PosicionesUtil.ubicarSegunSO(90,PosicionesUtil.UbicarEn(100, 15, 150, 12));
	public String path;
	
	
	public MostrarFotoSacadaYMandarMensajeManager(final String path) {
		super(FOCUSABLE);
		this.path = path;
		b = ImagesDownloader.bitmapFromFileWithSize(path,wFoto,hFoto);
		drawManager();		
	}

	private void drawManager() {		
		
		add(cancelar =  new ImageButtonField("album_detalle/pop_cancelar"){
			
			public boolean trackwheelClick(int status,int time){
				ScreenUtil.closeActiveScreen();
				return true;
			}
			
		});		
		
		add(aceptar = new ImageButtonField("album_detalle/pop_aceptar"){			
			public void onClick(){
				actionAceptar();
			}
		});
	}

	//Sobre escribir
	public void actionAceptar() {
		
	}
	
	protected void sublayout(int width, int height) {
		
		setPositionChild(cancelar,w/2 - cancelar.getBitmapWidth() - 5,MostrarFotoSacadaYMandarMensajeManager.h - cancelar.getBitmapHeight() - 5);
		layoutChild(cancelar,wBoton,hBoton);
		
		setPositionChild(aceptar,w/2 + 5,MostrarFotoSacadaYMandarMensajeManager.h - cancelar.getBitmapHeight() - 5);
		layoutChild(aceptar,wBoton,hBoton);
		
		setExtent(w,h);
	}
	
	
	public void paint(Graphics g){		
		g.setBackgroundColor(Color.BLACK);
		g.clear();
		super.paint(g);
		GraphicUtil.setSizeFont(g, sizeFont);
		g.setColor(Color.WHITE);
		g.drawText("Acepte si quieres enviar esta foto.", margen, 10);
		GraphicUtil.drawBitmap(g, b, margen + 20, 30);		
		GraphicUtil.drawMarco(g, w, h, Color.WHITE);
	}
	
	
}

