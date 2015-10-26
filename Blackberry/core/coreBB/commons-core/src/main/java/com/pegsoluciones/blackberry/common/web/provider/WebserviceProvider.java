package com.pegsoluciones.blackberry.common.web.provider;

import java.io.InputStream;
import java.util.Hashtable;


/**
 * Esta interface provee servicios de acceso a webservices remotos
 * 
 * @author PEG Soluciones S.A.
 * @since 1.0
 */
public interface WebserviceProvider {

	
	/**
	 * Este metodo retorna el response que se obtuve de
	 * hacer un HTTP GET a la url
	 * @param url 
	 * @return byte stream perteneciente al response
	 */
	InputStream loadResource(String url);
	
	/**
	 * Get with timeout
	 * @param url
	 * @param timeout
	 * @return
	 * @see WebserviceProvider#loadResource(String)
	 */
	InputStream loadResource(String url, int timeout);
	
	
	/**
	 * Este metodo retorna el response que se obtuve de
	 * hacer un HTTP POST a la url.
	 * 
	 * @param url
	 * @param postData son los datos a enviar en el body del request.
	 * @return
	 */
	InputStream loadResource(String url, String postData);
	
	/**
	 * Post with timeout
	 * 
	 * @param url
	 * @param postData
	 * @param timeout
	 * @return
	 * @see WebserviceProvider#loadResource(String, String)
	 */
	InputStream loadResource(String url, String postData, int timeout);
	
	/**
	 * Este metodo retorna el response que se obtuve de
	 * hacer un HTTP POST a la url. Incluye la opcion de 
	 * incluir HTTP HEADERS
	 * 
	 * @param url
	 * @param postData son los datos a enviar en el body del request.
	 * @param headers es un hashTable con los strings del header (key,value)
	 * @return
	 */
	InputStream loadResource(String url, String postData, Hashtable headers);

	/**
	 * @param url
	 * @param postData
	 * @param headers
	 * @param timeout
	 * @return
	 * @see WebserviceProvider#loadResource(String, String, Hashtable)
	 */
	InputStream loadResource(String url, String postData, Hashtable headers, int timeout);
	
	/**
	 * Este metodo retorna el response que se obtuve de
	 * hacer un HTTP POST a la url. Incluye la opcion de 
	 * incluir HTTP HEADERS
	 * 
	 * @param url
	 * @param postData son los datos a enviar en el body del request.
	 * @param headers es un hashTable con los strings del header (key,value)
	 * @param Specify a custom content type of the body
	 * @param timeout.
	 * @return
	 */
	InputStream loadResource(String url, String postData, Hashtable headers, ContentType contentType, int timeout);
	
}
