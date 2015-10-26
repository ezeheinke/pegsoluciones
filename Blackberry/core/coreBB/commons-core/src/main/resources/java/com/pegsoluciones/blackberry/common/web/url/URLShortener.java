package com.pegsoluciones.blackberry.common.web.url;

/**
 * This interface offers an access to a webservice to
 * make long url's a bit shorter. Many implementation 
 * can be found. 
 * 
 * @author PEG Soluciones S.A.
 * @since 1.0
 */
public interface URLShortener {
	
	
	/**
	 * Make the url short 
	 * 
	 * @param longUrl A long url.
	 * @return the short version of the given url. Returns 
	 * 		   null if the url couldn't be shorten
	 */
	String shorten(String longUrl); 
	
}
