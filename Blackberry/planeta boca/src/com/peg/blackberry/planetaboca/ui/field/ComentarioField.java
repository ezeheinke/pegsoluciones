package com.peg.blackberry.planetaboca.ui.field;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.peg.blackberry.planetaboca.domain.Comentario;

public class ComentarioField extends VerticalFieldManager {
	
	private static final Font FONT_COMENTARIO;
	private static final Font FONT_USUARIO;
	
	static{
		FONT_COMENTARIO = Font.getDefault().derive(Font.PLAIN,16);
		FONT_USUARIO = Font.getDefault().derive(Font.PLAIN,14);
	}
	
	int colorFondo;
	
	public ComentarioField(Comentario comentario, int amarilloBarra) {
		colorFondo = amarilloBarra;
		EditField e;
		add(e = new EditField("", comentario.getComentario()));
		e.setEditable(false);
		e.setFont(FONT_COMENTARIO);	
		e.setMargin(5, 10, 5, 10);
		
		add(e = new EditField("", comentario.getNombre() + "\n" + comentario.getFecha()));
		e.setEditable(false);
		e.setFont(FONT_USUARIO);
		e.setMargin(3, 10, 5, 10);
		
	}
	
	protected void paint(Graphics g) {
		g.setBackgroundColor(colorFondo);
		g.clear();
		super.paint(g);
	}
	
}
