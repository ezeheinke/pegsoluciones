package com.peg.blackberry.planetaboca.ui.manager;

import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.EditField;

import com.peg.blackberry.planetaboca.application.ApplicationFactory;
import com.peg.blackberry.planetaboca.domain.Noticia;
import com.peg.blackberry.planetaboca.service.ComentariosService;
import com.peg.blackberry.planetaboca.util.Colores;
import com.peg.blackberry.planetaboca.util.Strings;
import com.pegsoluciones.blackberry.common.device.ScreenUtil;
import com.pegsoluciones.blackberry.common.screenEnManager.SimulaScreens;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.ui.field.Linea;

public class ComentarManager extends PlanetaBocaBaseManager implements ServiceExecutionListener {

	private EditField comentario;
	private EditField nombre;
	private EditField mail;
	private ComentariosService service;
	private Dialog dialog;
	
	public ComentarManager(final Noticia noticia) {
		add(comentario 	= new EditField("Comentario: ", ""));
		add(new Linea(Colores.AZUL_COMENTARIOS, Colores.AZUL_COMENTARIOS,
				Display.getWidth(),3,0,NON_FOCUSABLE));
		add(nombre 		= new EditField("Nombre: ", ""));
		add(new Linea(Colores.AZUL_COMENTARIOS, Colores.AZUL_COMENTARIOS,
				Display.getWidth(),3,0,NON_FOCUSABLE));
		add(mail		= new EditField("Mail: ", ""));
		add(new Linea(Colores.AZUL_COMENTARIOS, Colores.AZUL_COMENTARIOS,
				Display.getWidth(),3,0,NON_FOCUSABLE));
		
		comentario.setMargin(new XYEdges(5, 10, 5, 10));
		nombre.setMargin(new XYEdges(5, 10, 5, 10));
		mail.setMargin(new XYEdges(5, 10, 5, 10));
		
		ButtonField f;
		add(f = new ButtonField("COMENTAR",FIELD_RIGHT){
			protected boolean trackwheelClick(int status, int time) {
				
				if(comentario.getText().equals("") || nombre.getText().equals("") 
						|| mail.getText().equals("")){
					Dialog.alert(Strings.COMPLETAR_DATOS);
					return true;
				}
				
				service.comentar(comentario.getText() + ". Enviado desde PlanetaBoca " +
						"para Blackberry.",
						nombre.getText(),mail.getText(),noticia.getId(),
						DeviceInfo.getDeviceName() + DeviceInfo.getPlatformVersion(),
						ComentarManager.this);
				dialog.show();
				
				return true;
			}
			
		});
		f.setMargin(new XYEdges(5, 10, 5, 10));
		
		service = ApplicationFactory.getInstance().getComentariosService();
		
		Object[] o = {"Cancelar"};
		dialog = new Dialog("Descargando...",o ,null, -1, null,Screen.DEFAULT_CLOSE);
	}

	protected void paint(Graphics g) {
		g.setBackgroundColor(Colores.AMARILLO_COMENTARIOS);
		g.clear();
		super.paint(g);
	}

	public void onCallComplete(Object parameters) {
		if(dialog.isDisplayed()){
			dialog.close();
			if(((Boolean)parameters).booleanValue()){
				Dialog.inform(Strings.COMENTARIO_ENVIADO);
				SimulaScreens.onClose();
			}else{
				Dialog.inform(Strings.ERROR_COMENTAR);
			}
		}
	}

	public void onError(ServiceError error) {
		if(dialog.isDisplayed()){
			dialog.close();
			Dialog.inform(Strings.ERROR_COMENTAR);
		}
	}
}
