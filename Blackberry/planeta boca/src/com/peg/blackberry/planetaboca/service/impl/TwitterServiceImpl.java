package com.peg.blackberry.planetaboca.service.impl;

import java.util.Vector;

import com.peg.blackberry.planetaboca.dao.TwettsDao;
import com.peg.blackberry.planetaboca.service.TwitterService;
import com.pegsoluciones.blackberry.common.service.AbstractService;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;

public class TwitterServiceImpl extends AbstractService implements TwitterService {

	TwettsDao twettsDao;

	public void setTwettsDao(TwettsDao twettsDao) {
		this.twettsDao = twettsDao;
	}

	public void getTwetts(final ServiceExecutionListener listener,final String user) {
		run(new Runnable() {
			
			public void run() {
				try {
					Vector v = new Vector();
					v.addElement(twettsDao.getUser(user));
					v.addElement(twettsDao.getTwetts(user));
					complete(listener, v);
				} catch (Exception e) {	
					fail(listener,new ServiceError(e.getMessage(),e));
				}
				
			}
		});

	}

	public void getTwetts(ServiceExecutionListener listener, String user,
			String since, String count) {
		// TODO Auto-generated method stub
		
	}

}
