package com.peg.blackberry.planetaboca.ui.field;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.Dialog;

import com.peg.blackberry.planetaboca.application.ApplicationEvent;
import com.peg.blackberry.planetaboca.application.ApplicationFactory;
import com.peg.blackberry.planetaboca.service.NoticiasService;
import com.peg.blackberry.planetaboca.service.SubcategoriaService;
import com.peg.blackberry.planetaboca.ui.manager.ListadoDeportesManager;
import com.peg.blackberry.planetaboca.ui.manager.NoticiasManager;
import com.peg.blackberry.planetaboca.ui.manager.SubcategoriasManager;
import com.peg.blackberry.planetaboca.util.Colores;
import com.peg.blackberry.planetaboca.util.Strings;
import com.pegsoluciones.blackberry.common.device.ScreenUtil;
import com.pegsoluciones.blackberry.common.event.EventManager;
import com.pegsoluciones.blackberry.common.screenEnManager.SimulaScreens;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.ui.screen.DialogDescargando;
import com.pegsoluciones.blackberry.common.util.ExBoolean;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;

public class DeporteField extends Field implements ServiceExecutionListener{

	public static final int ALTO = PosicionesUtil.UbicarEn(45, 45, 60, 40);
	private static final int MARGEN_X = 10;
	private int colorFondo = Colores.CASI_BLANCO;
	private String nombreDeporte;
	private String categoria;
	private static final Font FONT = Font.getDefault().derive(Font.BOLD,PosicionesUtil.UbicarEn(17, 19, 20, 17));
	ExBoolean cancelaron = new ExBoolean(false);
	DialogDescargando popup = new DialogDescargando(cancelaron);

	public DeporteField(String nombreDeporte,String categoria) {
		super(FOCUSABLE);
		this.nombreDeporte = nombreDeporte;
		this.categoria = categoria;
	}

	protected void layout(int width, int height) {
		setExtent(ListadoDeportesManager.ANCHO, ALTO);
	}

	protected void paint(Graphics g) {
		
		g.setBackgroundColor(colorFondo);
		g.clear();
		
		g.setFont(FONT);
		g.setColor(Colores.AZUL_BARRA);
		g.drawText(nombreDeporte,MARGEN_X,PosicionesUtil.centrada(g.getFont().getHeight(), ALTO));
	}
	
	protected void onFocus(int direction) {		
		colorFondo = Colores.GRISECITO;
		super.onFocus(direction);
	}

	protected void onUnfocus() {
		colorFondo = Colores.CASI_BLANCO;
		super.onUnfocus();
		invalidate();
	}
	
	public boolean trackwheelClick(int time, int status){
		ScreenUtil.closeActiveScreen();
		SimulaScreens.popHasta(1);
		Vector subcategorias = SubcategoriaService.getSubcategorias(this.categoria);
		
		// Por ahora si es mayor a uno se muestran las subcategorias, si es
		// cero entonces hay una unica categoria y se muestran directamente las noticias
		if(subcategorias.size() > 1){
			SimulaScreens.pushManager(new SubcategoriasManager(categoria,subcategorias));	
			EventManager.getInstance().notify(ApplicationEvent.CAMBIO_SECCION,
					nombreDeporte);
		}else{
			popup.show();
			NoticiasService noticiasService = ApplicationFactory.getInstance().getNoticiasService();
			noticiasService.getNoticias("1",Strings.TAMANIO_PAGINA,categoria, this);
		}
			
		return true;
	}

	public void onCallComplete(Object parameters) {
		if(cancelaron.isFalse()){			
			popup.close();
			Vector noticias = (Vector) parameters;
			if(noticias.size() == 0){
				Dialog.alert(Strings.CERO_NOTICIAS);				
			}
			else{
				SimulaScreens.pushManager(new NoticiasManager(noticias,categoria));
				EventManager.getInstance().notify(ApplicationEvent.CAMBIO_SECCION,
						nombreDeporte); 	
			}
		}else
			cancelaron.setValue(false);
		
	}

	public void onError(ServiceError error) {
		if(cancelaron.isFalse()){
			popup.close();
			Dialog.alert(Strings.ERROR_SERVICIO);
		}else
			cancelaron.setValue(false);
	}

}
