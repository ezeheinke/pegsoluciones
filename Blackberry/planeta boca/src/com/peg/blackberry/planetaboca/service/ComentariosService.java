package com.peg.blackberry.planetaboca.service;

import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;

public interface ComentariosService {
	
	public void getComentarios(String idNoticia,ServiceExecutionListener listener);	
	public void comentar(String comentarios, String nombre, String mail,String idComentario,
			String infoDevice,ServiceExecutionListener listener);

}
