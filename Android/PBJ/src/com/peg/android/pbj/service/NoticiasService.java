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

import com.peg.android.pbj.domain.Noticia;
import com.peg.android.pbj.domain.Strings;


/**
 * 
 * @author 
 *
 */
public class NoticiasService extends AbstractService {
	
	
	private final static String URL_NOTICIAS_RECIENTES 
			= "http://www.planetabocajuniors.com.ar/serviciosBB/services/ws_noticias.php?PAGINA={pagina}&TAMANIO={tamanio}";
	private final static String URL_NOTICIAS_CATEGORIAS 
			= "http://www.planetabocajuniors.com.ar/serviciosBB/services/ws_noticias_categoria.php?PAGINA={pagina}&TAMANIO={tamanio}&CATEGORIA={categoria}";
	private final static String URL_DETALLE_NOTICIA
			= "http://www.planetabocajuniors.com.ar/serviciosBB/services/ws_detalle_noticia.php?ID={id}";
	
	private final static String URL_DETALLE_NOTICIA_FROM_EXTERNAL_LINK 
			= "http://www.planetabocajuniors.com.ar/serviciosBB/services/ws_datos_noticia_by_name.php?NAME={name}";
	//---------------------------------------------------------------------//
	private static final String NOTICIA = "NOTICIA";
	private static final String ID = "ID";
	private static final String AUTOR = "AUTOR";
	private static final String FECHA = "FECHA";
	private static final String TITULO = "TITULO";
	private static final String RESUMEN = "RESUMEN";
	private static final String CONTENIDO = "CONTENIDO";
	private static final String CANTCOMENTARIOS = "CANTCOMENTARIOS";
	private static final String IMAGEN = "IMAGEN";
	private static final String URL_NOTICIA = "URL";
	private static final String TAMANIO = "10";
	
	

	
	/**
	 * pagina donde empiezo a pedir (minimo es 1)
	 * tamaÃ±o, osea cantidad te noticias por pagina..
	 */
	public void getNoticias(final ServiceListener listener,final String categoria,final String pagina) {
		execute(new Runnable() {
			
			public void run() {
				String url = "";
				if (categoria.equals(Strings.CATEGORIA_RECIENTES))
					url = URL_NOTICIAS_RECIENTES;
				else {
					url = URL_NOTICIAS_CATEGORIAS;
					url = url.replace("{categoria}", categoria);
					
				}
				
				// reemplazar pagina -> {pagina} -> pagina
				url = url.replace("{pagina}", pagina);
				// reemplazar tamaño -> {tamanio} -> pagina
				url = url.replace("{tamanio}", TAMANIO);
				try{
					complete(listener, doGetNoticias(url));					
				} catch(Exception e){
					fail(listener, new ServiceError());
				}
								
			}
		});
		
	}
	
	/*donde se hace el procesamiento del servicio*/
	private List<Noticia> doGetNoticias(String url) {
		System.out.println("*****Url servicio: " + url );
		List<Noticia> noticias = new ArrayList<Noticia>();
		
		try {

			URL urlM = new URL(url);
			URLConnection conn = urlM.openConnection();
			conn.setConnectTimeout(5000);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(conn.getInputStream());
			Node root = doc.getFirstChild();
			NodeList items = root.getChildNodes();

			for (int i=0;i<items.getLength();i++){
				Noticia noticia = new Noticia();
				Node item = items.item(i);				
				String name  = item.getNodeName();
				
				if(name.equals(NOTICIA)){
					NodeList properties = item.getChildNodes();
					for (int j=0;j<properties.getLength();j++){
						Node property = properties.item(j);
						name = property.getNodeName();
						
						if (name.equalsIgnoreCase("#text")){
							continue;
						}
												
						Node firstChild = property.getFirstChild();
						
						if(firstChild == null)
							continue;
						
						String value = firstChild.getNodeValue();// + StringUtils.decoder();
						
						if (name.equalsIgnoreCase(ID)){
							if (value != null)
								noticia.setId(value);
						} else if (name.equalsIgnoreCase(AUTOR)){
							if (value != null)
								noticia.setAutor(value);
						} else if (name.equalsIgnoreCase(FECHA)){
							if (value != null)
								noticia.setFecha(value);
						} else if (name.equalsIgnoreCase(TITULO)){
							if (value != null)
								noticia.setTitulo(value);
						} else if (name.equalsIgnoreCase(CANTCOMENTARIOS)){							
							if (value != null)
								noticia.setCantComentarios(value);
						} else if (name.equalsIgnoreCase(RESUMEN)){
							if (value != null)
								noticia.setResumen(value);
						}else if (name.equalsIgnoreCase(IMAGEN)) {
							if (value != null){
								noticia.setImagen(value);
							}
						}else if (name.equalsIgnoreCase(URL_NOTICIA)) {
							if (value != null) {
								noticia.setUrlNoticia(value);
							}
						}

						
						
					}
					noticias.add(noticia);
				}
			}
			
		} catch (Exception ex) {
			throw new RuntimeException("Error noticias service: " + ex);
		} 
		return noticias;
	}

	
	public void getDetalle(final ServiceListener listener, final int idNoticia){
		execute(new Runnable() {			
			public void run() {		
				try {
					complete(listener, getDetalleNoticia(idNoticia));													
				} catch (Exception e) {
					fail(listener, new ServiceError());
				}
			}
		});
		
	}

	public void getDetalleFromTitle(final ServiceListener listener, final String title){
		execute(new Runnable() {			
			public void run() {		
				try {
					complete(listener, getDetalleNoticia(title));													
				} catch (Exception e) {
					fail(listener, new ServiceError());
				}
			}


		});
		
	}

	private Noticia getDetalleNoticia(String title) {
		String url = URL_DETALLE_NOTICIA_FROM_EXTERNAL_LINK;
		url = url.replace("{name}", title + "");
		System.out.println("*****Url servicio: " + url);
		Noticia noticia = new Noticia();
		try {

			URL urlM = new URL(url);
			URLConnection conn = urlM.openConnection();
			conn.setConnectTimeout(5000);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(conn.getInputStream());
		
			Node root = doc.getFirstChild();
			
			NodeList items = root.getChildNodes();
			for (int i=0;i<items .getLength();i++){
				Node item = items.item(i);
				String name = item.getNodeName();

				
				Node firstChild = item.getFirstChild();
				if(firstChild == null)
					continue;
				
				String value = firstChild.getNodeValue();
				
				if (name.equals(ID)) {
					noticia.setId(value);
				}else if (name.equals(TITULO)) {					
					noticia.setTitulo(value);
				}else if (name.equals(IMAGEN)) {					
					noticia.setImagen(value);
				}else if (name.equals(FECHA)) {					
					noticia.setFecha(value);
				}else if (name.equals(RESUMEN)) {					
					noticia.setResumen(value);
				}else if (name.equals(CONTENIDO)) {
					noticia.setContenido(value);
				}else if (name.equalsIgnoreCase(AUTOR)){
					if (value != null)
						noticia.setAutor(value);
				}else if (name.equalsIgnoreCase(URL_NOTICIA)) {
					if (value != null) {
						noticia.setUrlNoticia(value);
					}
				}
			
			}
			
			
		} catch (Exception ex) {
			throw new RuntimeException("Error detalle x titel service: " + ex);
		} 
		return noticia;

	}
	
	public String getDetalleNoticia(int idNoticia) {
		
		String url = URL_DETALLE_NOTICIA;
		url = url.replace("{id}", idNoticia + "");
		System.out.println("*****Url servicio: " + url);
		String contenido = "";
		try {

			URL urlM = new URL(url);			
			URLConnection conn = urlM.openConnection();
			conn.setConnectTimeout(5000);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(conn.getInputStream());
		
			Node root = doc.getFirstChild();
			String name = root.getNodeName();
			
			if (name.equals(CONTENIDO)) {
				Node texto = root.getFirstChild();
				
				String value = texto.getNodeValue();//+ StringUtils.decoder();
				if (value != null)
					contenido = value;
			}
			
			
		} catch (Exception ex) {
			throw new RuntimeException("Error detalle x id service: " + ex);
		} 
		return contenido;
	}	
}
