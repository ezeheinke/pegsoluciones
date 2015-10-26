package com.peg.blackberry.planetaboca.service;

import java.util.Hashtable;
import java.util.Vector;

import com.peg.blackberry.planetaboca.domain.SubCategoria;
import com.peg.blackberry.planetaboca.util.Strings;

public class SubcategoriaService {

	private static Hashtable categorias;
	
	static{
		categorias = new Hashtable();
		
		Vector categoriasFutbol = new Vector();
		categoriasFutbol.addElement(new SubCategoria("Local",Strings.LOCAL));
		categoriasFutbol.addElement(new SubCategoria("Mercado de pases",Strings.MERCADO_DE_PASES));
		categoriasFutbol.addElement(new SubCategoria("Femenino",Strings.FEMENINO));
		categoriasFutbol.addElement(new SubCategoria("Inferiores",Strings.INFERIORES));
		categorias.put(Strings.FUTBOL, categoriasFutbol);
		
		Vector v = new Vector();
		categorias.put(Strings.VOLEY, v);
		categorias.put(Strings.BASKET,v);			
		categorias.put(Strings.INSTITUCIONAL,v);
	}

	public static Vector getSubcategorias(String categoria) {
		return (Vector) categorias.get(categoria);
	}
	
}
