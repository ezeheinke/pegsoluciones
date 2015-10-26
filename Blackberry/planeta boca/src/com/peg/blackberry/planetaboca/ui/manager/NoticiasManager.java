package com.peg.blackberry.planetaboca.ui.manager;

import java.util.Vector;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.Dialog;

import com.peg.blackberry.planetaboca.application.ApplicationFactory;
import com.peg.blackberry.planetaboca.domain.Noticia;
import com.peg.blackberry.planetaboca.service.NoticiasService;
import com.peg.blackberry.planetaboca.ui.field.NoticiaField;
import com.peg.blackberry.planetaboca.util.Colores;
import com.peg.blackberry.planetaboca.util.Strings;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.ui.field.ExButtonField;
import com.pegsoluciones.blackberry.common.ui.field.Linea;
import com.pegsoluciones.blackberry.common.ui.screen.DialogDescargando;
import com.pegsoluciones.blackberry.common.util.ExBoolean;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;

public class NoticiasManager extends PlanetaBocaBaseManager implements ServiceExecutionListener {

	private String categoria;
	private String nroPagina;
	private ExButtonField verProximos;
	ExBoolean cancelaron = new ExBoolean(false);
	DialogDescargando popup = new DialogDescargando(cancelaron);
	private Vector noticiasField = new Vector();

	public NoticiasManager(Vector noticias, String categoria) {
		
		this.nroPagina = "1";
		this.categoria = categoria;
		
		for(int i = 0 ; i < noticias.size() ; i++){
			
			Noticia noticia = (Noticia) noticias.elementAt(i);
			NoticiaField noticiaField = new NoticiaField(noticia);
			this.add(noticiaField);
			noticiasField.addElement(noticiaField);
			
			
			this.add(new Linea(Colores.AMARILLO_BARRA,Color.BLUE,
					Display.getWidth() - 20,2,0,Field.FIELD_HCENTER));
			
			
		}
		
		add(verProximos = new ExButtonField(Strings.VER_SIGUIENTES_NOTICIAS, Color.WHITE, Color.WHITE,
				Colores.AZULCITO, Color.BLACK, Color.WHITE, Color.WHITE,
				Display.getWidth(), 30, FOCUSABLE, PosicionesUtil.UbicarEn(16, 18, 18, 14)){
			
			public boolean trackwheelClick(int time , int status) {				
				int pag = Integer.parseInt(NoticiasManager.this.nroPagina) + 1;
				NoticiasManager.this.nroPagina = pag + "";
				NoticiasService noticiasService = ApplicationFactory.getInstance().getNoticiasService();
				noticiasService.getNoticias(NoticiasManager.this.nroPagina, Strings.TAMANIO_PAGINA,
						NoticiasManager.this.categoria, NoticiasManager.this);
				popup.show();
				return true;
			}
			
		});	
		
	}

	public void onCallComplete(Object parameters) {
		if(cancelaron.isFalse()){			
			popup.close();
			Vector noticias = (Vector) parameters;
			if(noticias.size() == 0){
				Dialog.alert(Strings.NO_MAS_NOTICIAS);				
			}
			else{
				this.delete(verProximos);
				for(int i = 0 ; i < noticias.size() ; i++){
					
					Noticia noticia = (Noticia) noticias.elementAt(i);
					NoticiaField noticiaField = new NoticiaField(noticia);
					this.add(noticiaField);
					noticiasField.addElement(noticiaField);
					
					if(i < noticias.size() - 1){
						this.add(new Linea(Colores.AMARILLO_BARRA,Color.BLUE,
								Display.getWidth() - 20,2,0,Field.FIELD_HCENTER));
					}
					
				}
				
				if(!nroPagina.equals("3")){
					this.add(new Linea(Colores.AMARILLO_BARRA,Color.BLUE,
							Display.getWidth() - 20,2,0,Field.FIELD_HCENTER));
					add(verProximos);
				}
				
				int firstNewNews = Integer.parseInt(Strings.TAMANIO_PAGINA) *
						(Integer.parseInt(nroPagina) - 1);				
				((Field)noticiasField.elementAt(firstNewNews)).setFocus();
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
