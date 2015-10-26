package com.pegsoluciones.blackberry.common.web.provider;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 * Standard implementation for {@link WebserviceProvider}
 * 
 * @author PEG Soluciones S.A.
 * @since 1.0
 *
 */
public class DefaultWebserviceProvider implements WebserviceProvider {
	
	

	public InputStream loadResource(String url) {

		return this.loadResource(url, 0);
	}
	

	public InputStream loadResource(String url, int timeout) {
		
		HttpConnection 	cx = null;
		InputStream	 	is = null;
		
		try {
			// prepare connection
			cx = (HttpConnection) Connector.open(url);
			cx.setRequestMethod(HttpConnection.GET);
			cx.setRequestProperty("Content-Type", "text/html,application/xhtml+xml,application/xml;");

			// check for HTTP error
			if(cx.getResponseCode() != HttpConnection.HTTP_OK) {
				
				throw new RuntimeException("GET: " + url +
						" - HTTP Code: " + cx.getResponseCode() +
						" - Message: " + cx.getResponseMessage());
			}

			// establish connection
			is = cx.openInputStream();
			
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
		return is;
	}
	

	public InputStream loadResource(String url, String postData) {
		
		return this.loadResource(url, postData, 0);
	}

	public InputStream loadResource(String url, String postData, int timeout) {
		
		return loadResource(url, postData, null);
	}

	public InputStream loadResource(String url, String postData, Hashtable headers) {
		
		return this.loadResource(url, postData, headers, 0);
	}


	public InputStream loadResource(String url, String postData, Hashtable headers, int timeout) {
		
		return loadResource(url, postData, headers, new ContentType("text/xml; charset=utf-8"), timeout);
	}
	
	

	public InputStream loadResource(String url, String postData, Hashtable headers, ContentType contentType, int timeout) {

		HttpConnection 	cx = null;
		InputStream	 	is = null;
		
		try {
			// prepare connection
			cx = (HttpConnection) Connector.open(url);
			cx.setRequestMethod(HttpConnection.POST);
			
			// set custom content type
			cx.setRequestProperty("Content-Type", contentType.getValue());
			
			// add extra headers
			if(headers != null){
				this.addAdditionalHeaders(cx, headers);
			}
			
			// write post data
			DataOutputStream outputStream = cx.openDataOutputStream();
			outputStream.write(postData.getBytes());
			outputStream.close();
			
			// check for HTTP error
			if(cx.getResponseCode() != HttpConnection.HTTP_OK &&
			   cx.getResponseCode() != HttpConnection.HTTP_CREATED) {
				
				throw new RuntimeException("POST: " + url +
						" - HTTP Code: " + cx.getResponseCode() +
						" - Message: " + cx.getResponseMessage());
			}

			// establish connection
			is = cx.openInputStream();

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
		return is;
	}
	
	
	
	private void addAdditionalHeaders(HttpConnection cx, Hashtable headers) throws Exception {
		
		for(Enumeration headerKeys = headers.keys(); headerKeys.hasMoreElements();){
				
			String headerKey = (String) headerKeys.nextElement();
			String headerValue = (String) headers.get(headerKey);
			cx.setRequestProperty(headerKey, headerValue);
		}
		
	}
	
}
