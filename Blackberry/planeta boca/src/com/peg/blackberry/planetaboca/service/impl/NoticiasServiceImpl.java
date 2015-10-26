package com.peg.blackberry.planetaboca.service.impl;

import com.peg.blackberry.planetaboca.dao.NoticiasDao;
import com.peg.blackberry.planetaboca.service.NoticiasService;
import com.pegsoluciones.blackberry.common.service.AbstractService;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;

public class NoticiasServiceImpl extends AbstractService implements
		NoticiasService {

	private NoticiasDao dao;

	public void getNoticias(final String id,final ServiceExecutionListener listener) {
		run(new Runnable() {
			
			public void run() {
				try{
					complete(listener, dao.getNoticias(id));
				}catch(Throwable t){
					
				}
			}
		});

	
	}

	public void setDao(NoticiasDao dao) {
		this.dao = dao;
	}

	public void getNoticias(final String pagina, final String tamanio,
			final String categoria,final ServiceExecutionListener listener) {
			run(new Runnable() {
			
			public void run() {
				try{
					complete(listener, dao.getNoticias(pagina,tamanio,categoria));
				}catch(Throwable t){
					
				}
			}
		});
		
	}

	public void getDetalleNoticias(final String id, final ServiceExecutionListener listener) {
		
		run(new Runnable() {			
			public void run() {			
				try{
					complete(listener, dao.getDetalleNoticia(id));
				}catch (Throwable e) {
					complete(listener, "Error al obtener noticia");
				}	
			}
		});
	
	}

}
