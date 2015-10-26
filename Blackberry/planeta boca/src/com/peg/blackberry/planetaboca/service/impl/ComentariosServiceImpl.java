package com.peg.blackberry.planetaboca.service.impl;

import com.peg.blackberry.planetaboca.dao.ComentariosDao;
import com.peg.blackberry.planetaboca.service.ComentariosService;
import com.pegsoluciones.blackberry.common.service.AbstractService;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;

public class ComentariosServiceImpl extends AbstractService implements
		ComentariosService {


	ComentariosDao comentariosDao;
	
	public void setComentariosDao(ComentariosDao comentariosDao) {
		this.comentariosDao = comentariosDao;
	}

	public void getComentarios(final String idNoticia,
			final ServiceExecutionListener listener) {
		run(new Runnable() {
			
			public void run() {
				try {
					complete(listener, comentariosDao.getComentarios(idNoticia));
				} catch (Throwable t) {
					fail(listener, new ServiceError(t.getMessage(),t));
				}
			}
		});
	}

	public void comentar(final String comentario,final String nombre,final String mail,final String idNoticia,
			final String infoDevice,final ServiceExecutionListener listener) {
		run(new Runnable() {			
			public void run() {
				try {
					complete(listener, comentariosDao.comentar(comentario, mail, nombre, idNoticia,infoDevice));
				} catch (Throwable t) {
					fail(listener, new ServiceError(t.getMessage(),t));
				}
			}
		});
	}

}
