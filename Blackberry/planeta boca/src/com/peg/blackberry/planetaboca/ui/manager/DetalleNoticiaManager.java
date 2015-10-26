package com.peg.blackberry.planetaboca.ui.manager;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BitmapField;

import com.peg.blackberry.planetaboca.application.ApplicationFactory;
import com.peg.blackberry.planetaboca.domain.Noticia;
import com.peg.blackberry.planetaboca.ui.field.BannerField;
import com.peg.blackberry.planetaboca.ui.field.ExNullField;
import com.peg.blackberry.planetaboca.ui.field.HeaderField;
import com.peg.blackberry.planetaboca.util.Colores;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.ui.field.ExEditField;
import com.pegsoluciones.blackberry.common.ui.field.Linea;
import com.pegsoluciones.blackberry.common.util.BitmapUtil;
import com.pegsoluciones.blackberry.common.util.Point;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;
import com.pegsoluciones.blackberry.common.web.DescargaImagenesManager;
import com.pegsoluciones.blackberry.common.web.EsperaImagenEncodedInterface;
import com.pegsoluciones.blackberry.common.web.ImagesDownloader;


public class DetalleNoticiaManager extends PlanetaBocaBaseManager implements ServiceExecutionListener, EsperaImagenEncodedInterface{

	private static final int SIZE_FONT_RESUMEN = PosicionesUtil.UbicarEn(15, 17, 17, 13);

	private static final int SIZE_FONT_TITULO = PosicionesUtil.UbicarEn(19, 22, 22, 14);

	private static final int SIZE_FONT_CUERPO_NOTICIA = PosicionesUtil.UbicarEn(14, 16, 16, 12);
	
	private static final XYEdges MARGENES = new XYEdges(10, 10, 10, 10);
	
	private ExEditField cuerpoNoticia;

	private BitmapField bitmapField;
	
	public DetalleNoticiaManager(Noticia noticia) {
		
		ExEditField auxField;
		
		this.add(auxField = new ExEditField("", noticia.getTitulo(),noticia.getTitulo().length()
				, Colores.AZULCITO,Font.BOLD,SIZE_FONT_TITULO));
		auxField.setMargin(MARGENES);
		
		this.add(auxField = new ExEditField("", noticia.getResumen(),noticia.getResumen().length()
				, Colores.AZULCITO,Font.BOLD,SIZE_FONT_RESUMEN));	
		auxField.setMargin(MARGENES);
		
		this.add(new BarritaCompartirManager(noticia));

		this.add(new Linea(Colores.AMARILLO_BARRA,Colores.AZULCITO,Display.getWidth() - 20,2,0,Field.FIELD_HCENTER));
		this.add(new ExNullField(10, 15));
		
		
		String imagen = noticia.getImagen();		
		if(imagen != null && !imagen.equals("") && imagen.length() > 10){	
			DescargaImagenesManager.descargarImagenThreadEncoded(imagen, this);
			this.add(bitmapField = new BitmapField(null,Field.FIELD_HCENTER | FOCUSABLE));
			this.add(new ExNullField(10, 15));
			this.add(new Linea(Colores.AMARILLO_BARRA,Colores.AZUL_BARRA,Display.getWidth() - 20,2,0,Field.FIELD_HCENTER));
			this.add(new ExNullField(10, 15));
		}
		
		this.add(cuerpoNoticia = new ExEditField(noticia.getContenido(),"Descargando...","Descargando...".length()
				, Colores.AZULCITO,Font.PLAIN,SIZE_FONT_CUERPO_NOTICIA));
		cuerpoNoticia.setMargin(MARGENES);
		
		
		ApplicationFactory.getInstance().getNoticiasService()
			.getDetalleNoticias(noticia.getId(),this);
		
	}

	public void onCallComplete(Object parameters) {
		String text = (String) parameters;
		cuerpoNoticia.setMaxSize(text.length());
		cuerpoNoticia.setText(text);
		invalidate();
	}

	public void onError(ServiceError error) {

	}

	public void catchImagen(EncodedImage img, String ruta) {
		if(img != null){
			Point sizePara = BitmapUtil.getSizePara(img.getWidth(), img.getHeight(),Display.getWidth() - 40,
					Display.getHeight() - (HeaderField.ALTO + BannerField.getInstance().getAltoBanner() + 40));
			img = ImagesDownloader.scale(img, (int)sizePara.getX(), (int)sizePara.getY());
			Bitmap bitmap = img.getBitmap();
			synchronized (UiApplication.getUiApplication().getAppEventLock()) {
				this.bitmapField.setBitmap(bitmap);
			}
			
		}		
	}

}
