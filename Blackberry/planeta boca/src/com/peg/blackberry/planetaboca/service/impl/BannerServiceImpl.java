package com.peg.blackberry.planetaboca.service.impl;

import com.peg.blackberry.planetaboca.dao.BannerDao;
import com.peg.blackberry.planetaboca.service.BannerService;
import com.pegsoluciones.blackberry.common.service.AbstractService;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;

public class BannerServiceImpl extends AbstractService implements BannerService {

	BannerDao dao;
	
	public void getBanners(final ServiceExecutionListener listener) {
		
		run(new Runnable() {
			
			public void run() {
				try {
					complete(listener, dao.getBanners());
				} catch (Throwable e) {
				}
				
			}
		});

	}

	public void setBannerDao(BannerDao dao) {
		this.dao = dao;
	}

	
}
