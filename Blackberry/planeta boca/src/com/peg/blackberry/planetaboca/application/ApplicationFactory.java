package com.peg.blackberry.planetaboca.application;

import com.peg.blackberry.planetaboca.dao.BannerDao;
import com.peg.blackberry.planetaboca.dao.ComentariosDao;
import com.peg.blackberry.planetaboca.dao.MensajesDao;
import com.peg.blackberry.planetaboca.dao.NoticiasDao;
import com.peg.blackberry.planetaboca.dao.TwettsDao;
import com.peg.blackberry.planetaboca.dao.impl.BannerDaoImpl;
import com.peg.blackberry.planetaboca.dao.impl.ComentariosDaoImp;
import com.peg.blackberry.planetaboca.dao.impl.MensajesDaoImpl;
import com.peg.blackberry.planetaboca.dao.impl.NoticiasDaoImpl;
import com.peg.blackberry.planetaboca.dao.impl.TwettsDaoImpl;
import com.peg.blackberry.planetaboca.service.BannerService;
import com.peg.blackberry.planetaboca.service.ComentariosService;
import com.peg.blackberry.planetaboca.service.MensajesService;
import com.peg.blackberry.planetaboca.service.NoticiasService;
import com.peg.blackberry.planetaboca.service.TwitterService;
import com.peg.blackberry.planetaboca.service.impl.BannerServiceImpl;
import com.peg.blackberry.planetaboca.service.impl.ComentariosServiceImpl;
import com.peg.blackberry.planetaboca.service.impl.MensajesServiceImpl;
import com.peg.blackberry.planetaboca.service.impl.NoticiasServiceImpl;
import com.peg.blackberry.planetaboca.service.impl.TwitterServiceImpl;
import com.pegsoluciones.blackberry.common.cache.InMemoryCache;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionNotifier;
import com.pegsoluciones.blackberry.common.web.provider.WebserviceProvider;
import com.pegsoluciones.blackberry.common.xml.parsers.DocumentBuilderAdapter;



public class ApplicationFactory {

	private static ApplicationFactory INSTANCE;
		
	public static ApplicationFactory getInstance() {
		
		if(INSTANCE == null) {
			INSTANCE = new ApplicationFactory();
		}
		return INSTANCE;
	}
	
	/** Web service provider used by DAO layer */
	private WebserviceProvider serviceProvider;
	
	/** Interface wich notifies from service to UI */
	private ServiceExecutionNotifier serviceNotifier;
	
	/** Dom document builder */
	private DocumentBuilderAdapter documentBuilder;
	
	/** Cache for services*/
	private InMemoryCache cache;
	
	/**
	 * Sets a web service provider implementation
	 * @param serviceProvider
	 */
	public void setWebserviceProvider(WebserviceProvider serviceProvider) {
		
		this.serviceProvider = serviceProvider;
	}
	
	/**
	 * Set the service notifier
	 * @param notifier 
	 */
	public void setServiceNotifier(ServiceExecutionNotifier notifier) {
		
		this.serviceNotifier = notifier;
	}
	
	

	/**
	 * @param documentBuilder the documentBuilder to set
	 */
	public void setDocumentBuilder(DocumentBuilderAdapter documentBuilder) {
		this.documentBuilder = documentBuilder;
	}


	/**
	 * @param cache the cache to set
	 */
	public void setCache(InMemoryCache cache) {
		this.cache = cache;
	}

	public NoticiasService getNoticiasService() {
		NoticiasServiceImpl service = new NoticiasServiceImpl();
		service.setDao(getNoticiasDao());
		service.setServiceNotifier(serviceNotifier);
		return service;
	}

	private NoticiasDao getNoticiasDao() {
		NoticiasDaoImpl dao = new NoticiasDaoImpl();
		dao.setCache(cache);
		dao.setDocumentBuilder(documentBuilder);
		dao.setServiceProvider(serviceProvider);
		dao.setUseCache(true);
		return dao;
	}
		
	private BannerDao getBannerDao() {
		BannerDaoImpl dao = new BannerDaoImpl();
		dao.setCache(cache);
		dao.setDocumentBuilder(documentBuilder);
		dao.setServiceProvider(serviceProvider);
		dao.setUseCache(true);
		return dao;
	}
	
	public BannerService getBannerService(){
		BannerServiceImpl service = new BannerServiceImpl();
		service.setBannerDao(getBannerDao());
		service.setServiceNotifier(serviceNotifier);
		return service;
	}
		
	private MensajesDao getMensajesDao(){
		MensajesDaoImpl dao = new MensajesDaoImpl();
		dao.setCache(cache);
		dao.setDocumentBuilder(documentBuilder);
		dao.setServiceProvider(serviceProvider);
		dao.setUseCache(true);
		return dao;
	}
	
	public MensajesService getMensajesService() {
		MensajesServiceImpl service = new MensajesServiceImpl();
		service.setMensajesDao(getMensajesDao());
		service.setServiceNotifier(serviceNotifier);
		return service;
	}
	
	public TwettsDao getTwettsDao(){
		TwettsDaoImpl dao = new TwettsDaoImpl();
		dao.setServiceProvider(serviceProvider);
		dao.setDocumentBuilder(documentBuilder);
		dao.setCache(cache);
		dao.setUseCache(true);
		return dao;
	}
	
	public TwitterService getTwitterService(){
		TwitterServiceImpl service = new TwitterServiceImpl();
		service.setServiceNotifier(serviceNotifier);
		service.setTwettsDao(getTwettsDao());
		return service;
	}
	///////////////////////////////////////////////////////////////////////////
	
	
	public ComentariosDao getComentariosDao(){
		ComentariosDaoImp dao = new ComentariosDaoImp();
		dao.setServiceProvider(serviceProvider);
		dao.setDocumentBuilder(documentBuilder);
		dao.setCache(cache);
		dao.setUseCache(true);
		return dao;
	}

	public ComentariosService getComentariosService() {
		ComentariosServiceImpl service = new ComentariosServiceImpl();
		service.setServiceNotifier(serviceNotifier);
		service.setComentariosDao(getComentariosDao());
		return service;
	}
}
