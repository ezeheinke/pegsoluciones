package com.peg.blackberry.planetaboca.dao.impl;

import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.peg.blackberry.planetaboca.dao.NoticiasDao;
import com.peg.blackberry.planetaboca.domain.Noticia;
import com.peg.blackberry.planetaboca.util.Strings;
import com.pegsoluciones.blackberry.common.dao.AbstractDao;
import com.pegsoluciones.blackberry.common.util.StringUtils;

/**
 * 
 * @author jose, (el que te la puso y se fue)
 *
 */
public class NoticiasDaoImpl extends AbstractDao implements NoticiasDao {

	
	
	private final static String URL_NOTICIAS_RECIENTES 
			= "http://www.planetabocajuniors.com.ar/serviciosBB/services/ws_noticias.php?PAGINA={pagina}&TAMANIO={tamanio}";
	private final static String URL_NOTICIAS_CATEGORIAS 
			= "http://www.planetabocajuniors.com.ar/serviciosBB/services/ws_noticias_categoria.php?PAGINA={pagina}&TAMANIO={tamanio}&CATEGORIA={categoria}";
	private final static String URL_DETALLE_NOTICIA
			= "http://www.planetabocajuniors.com.ar/serviciosBB/services/ws_detalle_noticia.php?ID={id}";
	//---------------------------------------------------------------------//
	private static final String NOTICIA = "NOTICIA";
	private static final  String ID = "ID";
	private static final  String AUTOR = "AUTOR";
	private static final  String FECHA = "FECHA";
	private static final  String TITULO = "TITULO";
	private static final  String RESUMEN = "RESUMEN";
	private static final  String CONTENIDO = "CONTENIDO";
	private static final  String CANTCOMENTARIOS = "CANTCOMENTARIOS";
	private static final  String IMAGEN = "IMAGEN";
	private static final String URL_NOTICIA = "URL";
	
	
	/**@deprecated**/
	public Vector getNoticias(String idUltimas) {
		Noticia noticia = new Noticia();
		//noticia.setDeporte("Futbol");
		noticia.setId("122");
		noticia.setResumen("El delantero renovara contrato por 4 años más, que groso");
		noticia.setTitulo("Riquelme renovó");
		noticia.setImagen("http://t1.gstatic.com/images?q=tbn:ANd9GcR7oyHxE" +
				"euvcfrksqcnyyKRDkEXzo0Kq0WTqdtJ0DRyZqv-h_c99iEJd7Q");
		
		Vector v = new Vector();
		for(int i = 0 ; i < 10 ; i++){
			v.addElement(noticia);
		}
		
		return v;
	}
	
	
	/**
	 * pagina donde empiezo a pedir (minimo es 1)
	 * tamaño, osea cantidad te noticias por pagina..
	 */
	public Vector getNoticias(String pagina,String tam, String categoria) {
		
		String url = "";
		if (categoria.equals(Strings.CATEGORIA_RECIENTES))
			url = URL_NOTICIAS_RECIENTES;
		else {
			url = URL_NOTICIAS_CATEGORIAS;
			url = StringUtils.replaceAll(url, "{categoria}", categoria);
		}
		
		// reemplazar pagina -> {pagina} -> pagina
		url = StringUtils.replaceAll(url, "{pagina}", pagina);
		// reemplazar tama�o -> {tamanio} -> pagina
		url = StringUtils.replaceAll(url, "{tamanio}", tam);
		
		return doGetNoticias(url);
		
	}
	
	/*donde se hace el procesamiento del servicio*/
	private Vector doGetNoticias(String url) {
		System.out.println("*****Url servicio: " + url );
		Vector noticias = new Vector();
		
		try {

			//cargamos el documento ya con el pagina y tamanio dado..
			/*

			 * 
			 */
			Document document = this.loadResource(url);			
			
			Node root = document.getFirstChild();
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
						
						String value = StringUtils.decoder(firstChild.getNodeValue());
						
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
					noticias.addElement(noticia);
				}
			}
			
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		} 
		return noticias;
	}


	public String getDetalleNoticia(String idNoticia) {
		
		String url = URL_DETALLE_NOTICIA;
		url = StringUtils.replaceAll(url, "{id}", idNoticia);
		System.out.println("*****Url servicio: " + 
				url);
		String contenido = "";
		try {


			Document document = this.loadResource(url);			
			Node root = document.getFirstChild();
			String name = root.getNodeName();
			
			if (name.equals(CONTENIDO)) {
				Node texto = root.getFirstChild();
				
				String value = StringUtils.decoder(texto.getNodeValue());
				if (value != null)
					contenido = value;
			}
			
			
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		} 
		return contenido;


	}

}
