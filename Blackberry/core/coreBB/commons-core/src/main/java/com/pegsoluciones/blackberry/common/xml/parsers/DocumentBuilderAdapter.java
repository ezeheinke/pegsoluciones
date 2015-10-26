package com.pegsoluciones.blackberry.common.xml.parsers;

import java.io.InputStream;

import org.w3c.dom.Document;

/**
 * 
 * 
 * 
 * @author PEG Soluciones S.A.
 *
 */
public interface DocumentBuilderAdapter {

	/**
	 * Parse xml data to get a DOM representation.
	 * 
	 * @param inputstream 
	 * @return a document instance
	 */
	Document parse(InputStream inputstream);
	
	
	/**
	 * Parse xml data to get a DOM representation.
	 * 
	 * @param string XML to be parsed. 
	 * @return a document instance
	 */
	Document parse(String string);
	
	
}
