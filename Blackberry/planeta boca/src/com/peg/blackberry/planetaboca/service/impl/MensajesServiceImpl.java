package com.peg.blackberry.planetaboca.service.impl;

import com.peg.blackberry.planetaboca.dao.MensajesDao;
import com.peg.blackberry.planetaboca.service.MensajesService;
import com.pegsoluciones.blackberry.common.service.AbstractService;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;

public class MensajesServiceImpl extends AbstractService implements
		MensajesService {

	MensajesDao dao;
	
	public void setMensajesDao(MensajesDao dao) {
		this.dao = dao;
	}

	public void getMensajes(final ServiceExecutionListener listener) {
		run(new Runnable() {
			
			public void run() {
				try {
					complete(listener, dao.getMensajes());
				} catch (Throwable t) {
					// Si vaya ya fue, otro dia le llegan los msj y no le pienso
					// avisar que fallo
				}
			}
		});

	}

}
