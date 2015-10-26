package com.peg.blackberry.planetaboca.ui.manager;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.peg.blackberry.planetaboca.domain.Noticia;
import com.pegsoluciones.blackberry.common.screenEnManager.SimulaScreens;

public class BotonComentarManager extends VerticalFieldManager {

	public BotonComentarManager(final Noticia noticia) {

		Field f;
		EditField e;
		add(e = new EditField("", noticia.getTitulo()));
		e.setMargin(new XYEdges(5, 10, 5, 10));
		e.setFont(Font.getDefault().derive(Font.BOLD,22));
		e.setEditable(false);
		
		add(f = new ButtonField("COMENTAR",FIELD_RIGHT){
			protected boolean trackwheelClick(int status, int time) {
				SimulaScreens.pushManager(new ComentarManager(noticia));
				return true;
			}
			
		});
		f.setMargin(new XYEdges(5, 10, 5, 10));
		
		LabelField l;
		add(l = new LabelField("Comentarios:",NON_FOCUSABLE));
		l.setMargin(new XYEdges(5, 10, 5, 10));
		
	}

}
