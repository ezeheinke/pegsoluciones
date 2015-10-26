package com.peg.blackberry.planetaboca.dao.impl;

import java.util.Vector;

import net.rim.device.api.system.ApplicationDescriptor;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.peg.blackberry.planetaboca.dao.MensajesDao;
import com.peg.blackberry.planetaboca.domain.Mensaje;
import com.pegsoluciones.blackberry.common.dao.AbstractDao;
import com.pegsoluciones.blackberry.common.device.DeviceUtil;

public class MensajesDaoImpl extends AbstractDao implements MensajesDao {

	private static final String MENSAJE = "mensaje";
	private static final Object TEXTO = "texto";
	private static final Object LINK = "link";
	private static final Object TIEMPO = "tiempo";
	String url = "http://www.pegsoluciones.com/proyectos/PBJ/mensajes.php?idTelefono=";
	String versionApp = "&verdionApp=";
	String mail = "&mail=";
	
	public Vector getMensajes() {
		Vector v = new Vector();
		url += DeviceUtil.getPhoneID();
		url += versionApp + ApplicationDescriptor.currentApplicationDescriptor().getVersion();
		url += mail + DeviceUtil.getDefaultMail();
		Document document = this.loadResource(url);			
		
		Node root = document.getFirstChild();
		NodeList items = root.getChildNodes();
		Mensaje m;
		
		for (int i=0;i<items.getLength();i++){
			Node item = items.item(i);				
			String value,name = item.getNodeName();
			
			if(name.equals(MENSAJE)){
				m = new Mensaje();
				NodeList valoresMensaje = item.getChildNodes();
				for(int j = 0 ; j < valoresMensaje.getLength() ; j++){
					Node n = valoresMensaje.item(j);					
					name = n.getNodeName();
					
					if(name.equals("#text"))
						continue;
					value = n.getFirstChild().getNodeValue();
					if(name.equals(TEXTO)){
						m.setTexto(value);
					}else if(name.equals(LINK)){
						m.setLink(value);
					}else if(name.equals(TIEMPO)){
						m.setTime(Integer.parseInt(value));
					}
				}
				v.addElement(m);
			}
		}
		return v;
	}

}
