package com.peg.blackberry.planetaboca.dao;

import java.util.Vector;

public interface NoticiasDao {

	public Vector getNoticias(String idUltimas);
	public Vector getNoticias(String pagina,String tamanio,String categoria);
	public String getDetalleNoticia(String idNoticia);

}
