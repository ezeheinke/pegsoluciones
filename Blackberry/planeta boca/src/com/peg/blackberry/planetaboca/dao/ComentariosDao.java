package com.peg.blackberry.planetaboca.dao;

import java.util.Vector;

public interface ComentariosDao {
	
	public Vector getComentarios(String idNoticia);
	public Boolean comentar(String comentario, String mail, String nombre, String idNoticia, String deviceInfo);

}
