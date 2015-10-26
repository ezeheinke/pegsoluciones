package com.peg.blackberry.planetaboca.ui.manager;

import java.util.Vector;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.Dialog;

import com.peg.blackberry.planetaboca.application.ApplicationFactory;
import com.peg.blackberry.planetaboca.domain.SubCategoria;
import com.peg.blackberry.planetaboca.service.NoticiasService;
import com.peg.blackberry.planetaboca.util.Colores;
import com.peg.blackberry.planetaboca.util.Strings;
import com.pegsoluciones.blackberry.common.screenEnManager.SimulaScreens;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.ui.screen.DialogDescargando;
import com.pegsoluciones.blackberry.common.util.ExBoolean;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;

public class CategoriaField extends Field implements ServiceExecutionListener {
	
	private String nombrePadre;
	private static final int ALTO = 60;
	private static final int MARGEN_X = 10;
	private static final Font FONT = Font.getDefault().derive(Font.BOLD,PosicionesUtil.UbicarEn(18, 20, 21, 18));
	private int colorFondo = Colores.CASI_BLANCO;
	private String nombre;
	private String idServicio;
	ExBoolean cancelaron = new ExBoolean(false);
	DialogDescargando popup = new DialogDescargando(cancelaron);

	public CategoriaField(String nombreSeccion,SubCategoria subCategoria) {
		super(FOCUSABLE);
		this.nombrePadre = nombreSeccion;
		this.nombre = subCategoria.getNombre();
		this.idServicio = subCategoria.getIdServicio();
	}

	protected void layout(int width, int height) {
		setExtent(Display.getWidth(), ALTO);
	}

	protected void paint(Graphics g) {		
		g.setBackgroundColor(colorFondo);
		g.clear();
		
		g.setFont(FONT);
		g.setColor(Colores.AZULCITO);
		g.drawText(nombre,MARGEN_X,PosicionesUtil.centrada(g.getFont().getHeight(), ALTO));
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

	public boolean trackwheelClick(int status, int time){
		popup.show();
		NoticiasService noticiasService = ApplicationFactory.getInstance().getNoticiasService();
		noticiasService.getNoticias("1",Strings.TAMANIO_PAGINA,nombrePadre + "-" + idServicio, this);
		return true;
	}

	public void onCallComplete(Object parameters) {
		if(cancelaron.isFalse()){
			popup.close();
			Vector noticias = (Vector) parameters;
			if(noticias.size() == 0)
				Dialog.alert(Strings.CERO_NOTICIAS);
			else
				SimulaScreens.pushManager(new NoticiasManager(noticias,nombrePadre + "-" + idServicio));
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
