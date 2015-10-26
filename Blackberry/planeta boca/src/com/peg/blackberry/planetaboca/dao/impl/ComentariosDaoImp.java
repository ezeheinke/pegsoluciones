package com.peg.blackberry.planetaboca.dao.impl;

import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.peg.blackberry.planetaboca.dao.ComentariosDao;
import com.peg.blackberry.planetaboca.domain.Comentario;
import com.pegsoluciones.blackberry.common.dao.AbstractDao;
import com.pegsoluciones.blackberry.common.util.StringUtils;

public class ComentariosDaoImp extends AbstractDao implements ComentariosDao {
	
	private static final String URL_COMENTARIOS
	= "http://www.planetabocajuniors.com.ar/serviciosBB/services/ws_listar_comentarios.php?ID=";
	private static final String URL_COMENTAR 
	= "http://www.planetabocajuniors.com.ar/serviciosBB/services/ws_hacer_comentario.php?ID_POST=";
	private static final String COMENTARIO = "COMENTARIO";
	private static final String CONTENIDO = "CONTENIDO";
	private static final String AUTOR = "AUTOR";
	private static final String FECHA = "FECHA";
	private static final String MAIL = "MAIL";
	private static final String ID = "ID";
	private static final String RESULTADO_OK = "1";

	
	
	public Vector getComentarios(String idNoticia) {
		Vector v = new Vector();
		Document document = this.loadResource(URL_COMENTARIOS.concat(idNoticia));			
		
		Node root = document.getFirstChild();
		NodeList items = root.getChildNodes();
		
		for (int i=0;i<items.getLength();i++){
			Node n, item = items.item(i);				
			String value,name = item.getNodeName();
			if(name.equals(COMENTARIO)){
				NodeList ns = item.getChildNodes();
				Comentario c = new Comentario();
				for(int j = 0;  j < ns.getLength() ; j++){
					n = ns.item(j);
					name = n.getNodeName();
					if(name.equals("#text"))
						continue;
					value = n.getFirstChild().getNodeValue();
					if(name.equals(CONTENIDO)){
						c.setComentario(value);
					}else if(name.equals(AUTOR)){
						c.setNombre(value);
					}else if(name.equals(FECHA)){
						c.setFecha(value);
					}else if (name.equals(MAIL)){
						c.setMail(value);
					}else if (name.equals(ID)){
						c.setId(value);
					}
				}
				v.addElement(c);
			}
				
			
		}
		return v;
	}

	public Boolean comentar(String comentario, String mail, String nombre,
			String idNoticia, String deviceInfo) {
		String url = URL_COMENTAR.concat(idNoticia);
		//seguridad si ponene & en un comentario se rompe todo
		comentario = StringUtils.replaceAll(comentario,"&","");
		url = url.concat("&COMENTARIO=").concat(comentario);
		url = url.concat("&MAIL=").concat(mail);
		url = url.concat("&AUTOR=").concat(nombre);
		url = url.concat("&MODELO=").concat(deviceInfo);
		url = StringUtils.replaceAll(url," ","%20");

		Document document = this.loadResource(url);			
		
		Node root = document.getFirstChild();
		String rta = root.getFirstChild().getNodeValue();
		
		/*NodeList items = root.getChildNodes();
		String rta = items.item(1).getFirstChild().getNodeValue();
		*/
		return new Boolean(rta.equals(RESULTADO_OK));
	}

}
