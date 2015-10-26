package com.peg.android.pbj.service;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.graphics.BitmapFactory;

import com.peg.android.pbj.domain.Banner;

public class BannerService extends AbstractService {



	private static final String BANNER 		= "banner";
	private static final String MODELO 		= "modelo";
	private static final String IMAGEN 		= "imagen";
	private static final String LINK 		= "link";
	private static final String TAMANO 		= "tamano";
	private static final String APP 		= "app";
	private static final String MSJ 		= "msj";
	private static final String NOMBRE 		= "nombre";
	private static final String FECHA		= "fecha";
	private static final String DIRECCION	= "direccion";
	private static final String HORA		= "hora";
	private static final String TELEFONO	= "telefono";
	

	
	String URL = "http://www.pegsoluciones.com/proyectos/PBJ/bannersPBJ.xml";
	
	private List<Banner> doGetBanners() {
		
		URLConnection conn = null;
		URL urlM = null;
		
		try{
			List<Banner> v = new ArrayList<Banner>();
			
			urlM = new URL(URL);
			conn = urlM.openConnection();
			conn.setConnectTimeout(5000);
	
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(conn.getInputStream());
			Node root = doc.getFirstChild();
			NodeList items = root.getChildNodes();
	
			for (int i=0;i<items.getLength();i++){
				Node item = items.item(i);				
				String name  = item.getNodeName();
				
				if(name.equals(BANNER)){
					NodeList bannersNode = item.getChildNodes();
					List<Banner> todosLosModelos = new ArrayList<Banner>();
					for(int j = 0 ; j < bannersNode.getLength(); j++){
						Node n = bannersNode.item(j);					
						name = n.getNodeName();
						
						if(name.equals(MODELO)){
							NodeList modelosNode = n.getChildNodes();
							Banner b = new Banner();
							for(int k = 0 ; k < modelosNode.getLength(); k++){
								Node modeloKey = modelosNode.item(k);
								name = modeloKey.getNodeName();
								
								if(name.equals("#text"))
									continue;
								
								String value = modeloKey.getFirstChild().getNodeValue();
								
								if(name.equals(IMAGEN)){
									b.setUrlImagen(value);
								}else if(name.equals(LINK)){
									b.setLink(value);
								}else if(name.equals(TAMANO)){
									b.setTamano(value);
								}else if(name.equals(APP)){
									b.setApp(value);
								}else if(name.equals(MSJ)){
									b.setMsj(value);
								}else if(name.equals(NOMBRE)){
									b.setNombre(value);
								}else if(name.equals(DIRECCION)){
									b.setDireccion(value);
								}else if(name.equals(FECHA)){
									b.setFecha(value);
								}else if(name.equals(HORA)){
									b.setHora(value);
								}else if(name.equals(TELEFONO)){
									b.setTelefono(value);
								}							
							}
							todosLosModelos.add(b);
						}				
					}
					Banner bestSize = getBestSize(todosLosModelos);
					InputStream openStream = null;				     
				    try {
				    	 URL connection = new URL(bestSize.getUrlImagen());
				    	 openStream = connection.openStream();
				    	 bestSize.setBitamp(BitmapFactory.decodeStream(openStream));	    	 
				     } catch(Exception e){
				    	 
				     } finally {
				    	 try {
				    		 if(openStream != null)
				    			 openStream.close();
						} catch (Exception e) {
							
						}
				     }
				    
				     if(bestSize.getDrawable() != null)
				    	 v.add(bestSize);
				}
			}
			
			return v;
		}catch(Exception e){
			throw new RuntimeException("Error en banner service: " + e);
		} finally {
			
		}
	}


	public void getBanners(final ServiceListener listener){
		execute(new Runnable() {
			
			public void run() {
				try {
					complete(listener, doGetBanners());
				} catch (Exception e) {

				}
				
			}
		});
	}
	
	

	/**
	 * @param banners
	 * @return da el banner q mejor se ajusta a este modelo
	 */
	private Banner getBestSize(List<Banner> banners) {		
		return banners.get(banners.size() - 1);
	}

}