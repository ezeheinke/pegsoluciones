package com.peg.blackberry.planetaboca.ui.manager;

import java.util.Vector;

import com.peg.blackberry.planetaboca.domain.Comentario;
import com.peg.blackberry.planetaboca.domain.Noticia;
import com.peg.blackberry.planetaboca.ui.field.ComentarioField;
import com.peg.blackberry.planetaboca.util.Colores;

public class ComentariosManager extends PlanetaBocaBaseManager {

	public ComentariosManager(Noticia noticia, Vector v) {
		
		add(new BotonComentarManager(noticia));
		
		for(int i = 0; i < v.size() ; i++){
			Comentario comentario = (Comentario) v.elementAt(i);
			if(i % 2 == 0)
				this.add(new ComentarioField(comentario,Colores.AMARILLO_COMENTARIOS));				
			else
				this.add(new ComentarioField(comentario,Colores.AZUL_COMENTARIOS));
			
		}
	}

}
