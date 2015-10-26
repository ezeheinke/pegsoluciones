package com.pegsoluciones.blackberry.common.dao;

import java.io.InputStream;
import java.util.Hashtable;

import org.w3c.dom.Document;

import com.pegsoluciones.blackberry.common.cache.InMemoryCache;
import com.pegsoluciones.blackberry.common.cache.ProceedingJoinPoint;
import com.pegsoluciones.blackberry.common.web.provider.WebserviceProvider;
import com.pegsoluciones.blackberry.common.xml.parsers.DocumentBuilderAdapter;


/**
 * Common functionality for DAO's layer.<br> The user will be 
 * able to choose between two kind of webservice providers. One for
 * access to internet inside the phone and the other within the test env.
 * 
 * @author PEG Soluciones S.A.
 * @since 1.0
 */
public abstract class AbstractDao {

	
	private WebserviceProvider serviceProvider;
	
	private DocumentBuilderAdapter documentBuilder;
	
	private InMemoryCache cache;

	private boolean useCache;
	
	private int timeout; 
	
	/**
	 * @return the documentBuilder
	 */
	public DocumentBuilderAdapter getDocumentBuilder() {
		return documentBuilder;
	}

	/**
	 * @param documentBuilder the documentBuilder to set
	 */
	public void setDocumentBuilder(DocumentBuilderAdapter documentBuilder) {
		this.documentBuilder = documentBuilder;
	}

	/**
	 * @return the serviceProvider
	 */
	public WebserviceProvider getServiceProvider() {
		return serviceProvider;
	}

	/**
	 * @param serviceProvider the serviceProvider to set
	 */
	public void setServiceProvider(WebserviceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	
	/**
	 * @return the cache
	 */
	public InMemoryCache getCache() {
		return cache;
	}

	/**
	 * @param cache the cache to set
	 */
	public void setCache(InMemoryCache cache) {
		this.cache = cache;
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * @param useCache the useCache to set
	 */
	public void setUseCache(boolean useCache) {
		this.useCache = useCache;
	}
	
	/**
	 * Load internet resource using HTTP GET
	 * @param url
	 * @return
	 */
	public Document loadResource(final String url) {
		
		if(!useCache) {
			
			return doGetResourceAndParse(url);
		}
		
		Document result = (Document) cache.doCaching(this.getAProceedingJoinPoint(url));
		
		return result;
	}
	
	/**
	 * Load internet resource using HTTP POST
	 * @param url
	 * @param postData
	 * @return
	 */
	public Document loadResource(final String url, final String postData) {
		
		return this.loadResource(url, postData, null);
	}

	/**
	 * Load internet resource using HTTP POST including HTTP HEADERS
	 * @param url
	 * @param postData
	 * @return
	 */
	public Document loadResource(final String url, final String postData, final Hashtable headers) {
		
		return this.doGetResourceAndParse(url, postData, headers);
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	private Document doGetResourceAndParse(String url) {
		
		InputStream inputstream = this.timeout > 0 ? 
								  serviceProvider.loadResource(url, this.timeout) :
							      serviceProvider.loadResource(url);
		
		return this.doParse(inputstream);
	}
	
	/**
	 * 
	 * @param url
	 * @param postData
	 * @param headers
	 * @return
	 */
	private Document doGetResourceAndParse(String url, String postData, Hashtable headers) {
		
		InputStream inputstream = this.timeout > 0 ? 
		    serviceProvider.loadResource(url, postData, headers, this.timeout) :
			serviceProvider.loadResource(url, postData, headers);
		
	    return this.doParse(inputstream);
	}
	
	/**
	 * 
	 * @param inputStream
	 * @return
	 */
	private Document doParse(InputStream inputStream) {
		
		return documentBuilder.parse(inputStream);
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	private ProceedingJoinPoint getAProceedingJoinPoint(final String url) {
		
		return new ProceedingJoinPoint() {
			
			public Object proceed() {
				
				return doGetResourceAndParse(url);
			}
			
			public Object[] arguments() {
				
				return new Object[]{url};
			}
		};
	}
}
