package com.pegsoluciones.blackberry.common.util;

import org.w3c.dom.Node;

public abstract class XMLUtil {

	public static Node getNodeLlamado(Node node, String name) {

		  int size = node.getChildNodes().getLength();
		  for(int i = 0; i < size ; i++){
		   String nodeName = node.getChildNodes().item(i).getNodeName();
		   if(nodeName.equals(name))
		    return node.getChildNodes().item(i);
		  }
		  
		  return null;
	}	
	
}