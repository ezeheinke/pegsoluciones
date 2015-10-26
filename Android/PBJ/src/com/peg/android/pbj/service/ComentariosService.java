package com.peg.android.pbj.service;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.peg.android.pbj.domain.Comentario;

public class ComentariosService extends AbstractService {
        
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

        
        public void getComentarios(final String idNoticia, final ServiceListener listener) {
        	execute(new Runnable() {				
				@Override
				public void run() {
					try {
						complete(listener, doGetComentarios(idNoticia));
					} catch (Exception e) {
						fail(listener, new ServiceError());
					}
				}
			});
        }
        
        
        private List<Comentario> doGetComentarios(String idNoticia) {    
        	
                List<Comentario> v = new ArrayList<Comentario>();
        		URLConnection conn = null;
        		URL urlM = null;
                
                try {
                	urlM = new URL(URL_COMENTARIOS + idNoticia);
        			conn = urlM.openConnection();
        	
        			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        			DocumentBuilder builder = factory.newDocumentBuilder();
        			Document doc = builder.parse(conn.getInputStream());
        			Node root = doc.getFirstChild();
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
                                    v.add(c);
                            }
                                    
                            
                    }
                    return v;
				} catch (Exception e) {
					throw new RuntimeException("Error en comentarios service: " + e);
				}
    			
        }
        
        
        public void comentar(final ServiceListener listener,final String idNoticia, final String comentario,final String mail,final String nombre,
        		final String deviceInfo) {
        	execute(new Runnable() {				
				@Override
				public void run() {
					try {
						complete(listener, doComentar(comentario,mail,nombre,idNoticia,deviceInfo));
					} catch (Exception e) {
						fail(listener, new ServiceError());
					}
				}
			});
        }        

        public Boolean doComentar(String comentario, String mail, String nombre,
                       String idNoticia, String deviceInfo) {
        	
            	URLConnection conn = null;
            	URL urlM = null;
        		
            	try{
	        		String url = URL_COMENTAR.concat(idNoticia);
	                //seguridad si ponene & en un comentario se rompe todo
	                comentario = comentario.replaceAll("&","");
	                url = url.concat("&COMENTARIO=").concat(comentario);
	                url = url.concat("&MAIL=").concat(mail);
	                url = url.concat("&AUTOR=").concat(nombre);
	                url = url.concat("&MODELO=").concat(deviceInfo);
	                url = url.concat("&PLATAFORMA=android");
	                url = url.replaceAll(" ","%20");
	
	            	urlM = new URL(url);
	    			conn = urlM.openConnection();
	    			conn.setConnectTimeout(5000);
	                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    			DocumentBuilder builder = factory.newDocumentBuilder();
	    			Document doc = builder.parse(conn.getInputStream());
	    			Node root = doc.getFirstChild();
	               // String rta = root.getFirstChild().getNodeValue();
	                return new Boolean(true);
            	}catch(Exception e){
            		throw new RuntimeException("Error en comentar service: " + e);
            	}
                
                
        		
                
        }

}