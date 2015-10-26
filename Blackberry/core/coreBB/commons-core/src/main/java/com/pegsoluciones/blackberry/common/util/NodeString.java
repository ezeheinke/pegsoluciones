package com.pegsoluciones.blackberry.common.util;

import java.util.Enumeration;
import java.util.Hashtable;

import org.w3c.dom.Node;


public class NodeString {
	
	private Hashtable hs = new Hashtable();

	public NodeString(Node unNodo){
 
    	String value="";

		
		int length = unNodo.getChildNodes().getLength();
		
		for(int i=0;i<length;i++){
             
            Node node = unNodo.getChildNodes().item(i);
			String key=node.getNodeName();
			
            if(!key.equals("#text")) {
            	Node child = node.getChildNodes().item(0);
				
				if(child != null)
					value=child.getNodeValue();
				else
					value = "";
				
				hs.put(key,value);
			}
			
        }
	
		String s = hs.toString();
        		
	}

	
	public NodeString(Hashtable hs) {
		this.hs = hs;
	}


	public String get(String key){
		String s=(String)hs.get(key);
		return s;
		
	}


	public void put(String key, String value) {
		hs.put(key, value);
		
	}


	public Enumeration keys() {
		return hs.keys();
	}

}

