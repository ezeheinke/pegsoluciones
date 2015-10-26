package com.pegsoluciones.blackberry.common.web.provider;

import java.io.InputStream;
import java.util.Hashtable;

import com.pegsoluciones.blackberry.common.device.StringConexionUtil;


/**
 * Rim iplementation for {@link WebserviceProvider}
 * 
 * @author PEG Soluciones S.A.
 * @since 1.0
 *
 */
public class RimWebserviceProvider implements WebserviceProvider {
	
	private static final String STR_TIMEOUT = ";ConnectionTimeout=";
	
	private static int DEFAULT_TIMEOUT = 60000;
	

	public InputStream loadResource(String url) {
		
		return this.loadResource(url, DEFAULT_TIMEOUT);
	}
	

	public InputStream loadResource(String url, int timeout) {
		
		// TODO Auto-generated method stub
		String managedUrl = this.willTimedOut(StringConexionUtil.urlConexion(url), timeout);
		
		DefaultWebserviceProvider webserviceProvider = new DefaultWebserviceProvider();
		
		try {
			
			return webserviceProvider.loadResource(managedUrl);
			
		} catch (Exception ex) {

			throw new RuntimeException(ex.getMessage());
		}
	}
	
	

	public InputStream loadResource(String url, String postData) {
		
		return this.loadResource(url, postData, DEFAULT_TIMEOUT);
	}
	

	public InputStream loadResource(String url, String postData, int timeout) {

		return this.loadResource(url, postData, null);
	}
	
	

	public InputStream loadResource(String url, String postData, Hashtable headers) {
		
		return this.loadResource(url, postData, headers, DEFAULT_TIMEOUT);
	}
	
	

	public InputStream loadResource(String url, String postData, Hashtable headers, int timeout) {
		
		return this.loadResource(url, postData, headers, new ContentType("text/xml; charset=utf-8"), timeout);
	}

	
	public InputStream loadResource(String url, String postData, Hashtable headers, ContentType contentType, int timeout) {

		String managedUrl = this.willTimedOut(StringConexionUtil.urlConexion(url), timeout);
		
		DefaultWebserviceProvider webserviceProvider = new DefaultWebserviceProvider();
		
		return webserviceProvider.loadResource(managedUrl, postData, headers);
	}
	
	private String willTimedOut(String url, int seconds) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(url);
		buffer.append(STR_TIMEOUT);
		buffer.append(Integer.toString(seconds * 1000));
		return buffer.toString();
	}
	
}
