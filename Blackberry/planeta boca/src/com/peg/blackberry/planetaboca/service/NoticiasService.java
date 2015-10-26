package com.peg.blackberry.planetaboca.service;

import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;

public interface NoticiasService {

	
	public void getNoticias(String id, ServiceExecutionListener listener);
	public void getNoticias(String pagina,String tamanio,String categoria, ServiceExecutionListener listener);
	public void getDetalleNoticias(String id, ServiceExecutionListener listener);
	
}
