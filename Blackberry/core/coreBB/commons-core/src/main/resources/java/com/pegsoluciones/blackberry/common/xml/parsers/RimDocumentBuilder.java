package com.pegsoluciones.blackberry.common.xml.parsers;

import java.io.InputStream;

import net.rim.device.api.xml.parsers.DocumentBuilder;
import net.rim.device.api.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;


/**
 * 
 * RIM <code>DocumentBuilder</code> implementation
 * 
 * @author PEG Soluciones S.A.
 *
 */
public class RimDocumentBuilder implements DocumentBuilderAdapter {
	
	

	public Document parse(InputStream inputstream) throws RuntimeException {

		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 

			DocumentBuilder builder = factory.newDocumentBuilder();
			
			return builder.parse(inputstream);
			
		} catch(Exception ex) {
			
			throw new RuntimeException(ex.getMessage());
		}
	}

	public Document parse(String string) {
		
		throw new RuntimeException("Method not implemented!");
	}

}
