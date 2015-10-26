package com.peg.blackberry.planetaboca.ui.manager;

import java.util.Vector;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

import com.peg.blackberry.planetaboca.domain.SubCategoria;
import com.peg.blackberry.planetaboca.util.Colores;
import com.pegsoluciones.blackberry.common.ui.field.Linea;

public class SubcategoriasManager extends PlanetaBocaBaseManager {

	public SubcategoriasManager(final String categoria, Vector subcategorias) {
	
		for(int i = 0 ; i < subcategorias.size() ; i++){
			add(new CategoriaField(categoria,(SubCategoria)subcategorias.elementAt(i)));
		
			if(i <subcategorias.size() -1)
				this.add(new Linea(Colores.AMARILLO_BARRA,Colores.CASI_BLANCO,
						Display.getWidth() - 20,2,10,Field.FIELD_HCENTER));
		}
		
	}

	public void paint(Graphics g){
		g.setBackgroundColor(Colores.CASI_BLANCO);
		g.clear();
		super.paint(g);
	}
	
}
