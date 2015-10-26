package com.peg.blackberry.planetaboca.dao.impl;

import java.util.Vector;

import net.rim.device.api.system.Display;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.peg.blackberry.planetaboca.dao.BannerDao;
import com.peg.blackberry.planetaboca.domain.Banner;
import com.peg.blackberry.planetaboca.domain.bannerRunner.AgendarBannerRunner;
import com.peg.blackberry.planetaboca.domain.bannerRunner.BrowserBannerRunner;
import com.peg.blackberry.planetaboca.domain.bannerRunner.ContactoBannerRunner;
import com.peg.blackberry.planetaboca.domain.bannerRunner.MailBannerRunner;
import com.pegsoluciones.blackberry.common.dao.AbstractDao;
import com.pegsoluciones.blackberry.common.util.StringUtils;

public class BannerDaoImpl extends AbstractDao implements BannerDao {

	private static final AgendarBannerRunner 	AGENDAR_BANNER_RUNNER 	= new AgendarBannerRunner();
	private static final ContactoBannerRunner 	CONTACTO_BANNER_RUNNER 	= new ContactoBannerRunner();
	private static final MailBannerRunner 		MAIL_BANNER_RUNNER 		= new MailBannerRunner();
	private static final BrowserBannerRunner 	BROWSER_BANNER_RUNNER 	= new BrowserBannerRunner();
	
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
	
	// app en que corren los banners
	private static final String BROWSER 	= "browser";
	private static final String MAIL		= "mail";
	private static final String CONTACTO	= "contacto";
	private static final String AGENDAR		= "agendar";
	
	String URL = "http://pegsoluciones.com/proyectos/PBJ/bannersPBJ.xml";
	
	public Vector getBanners() {
		Vector v = new Vector();
		Document document = this.loadResource(URL);			
		
		Node root = document.getFirstChild();
		NodeList items = root.getChildNodes();

		for (int i=0;i<items.getLength();i++){
			Node item = items.item(i);				
			String name  = item.getNodeName();
			
			if(name.equals(BANNER)){
				NodeList bannersNode = item.getChildNodes();
				Vector todosLosModelos = new Vector();
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
								elegirBannerRunner(b);
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
						todosLosModelos.addElement(b);
					}				
				}
				v.addElement(getBestSize(todosLosModelos));
			}
		}
		
		return v;
	}

	private void elegirBannerRunner(Banner b) {
		String app = b.getApp();
		if(app.equals(BROWSER)){
			b.setBannerRunner(BROWSER_BANNER_RUNNER);
		}else if(app.equals(MAIL)){
			b.setBannerRunner(MAIL_BANNER_RUNNER);
		}else if(app.equals(CONTACTO)){
			b.setBannerRunner(CONTACTO_BANNER_RUNNER);
		}else if(app.equals(AGENDAR)){
			b.setBannerRunner(AGENDAR_BANNER_RUNNER);
		}
		
	}

	/**
	 * @param banners
	 * @return da el banner q mejor se ajusta a este modelo
	 */
	private Banner getBestSize(Vector banners) {		
        int iMax = 0,x,y,maxProdPix = 0;
        Vector tamanos;
        int w = Display.getWidth();
        int h = Display.getHeight();
        for (int i = 0; i < banners.size(); i++) {
        	Banner b = (Banner) banners.elementAt( i );
        	tamanos = StringUtils.parse(b.getTamano(), 'x');
            x = Integer.parseInt((String) tamanos.elementAt(0));
            y = Integer.parseInt((String) tamanos.elementAt(1));
            int prodPix = x * y;
            if ( x <= w && y <= h ) {            	
                if (prodPix > maxProdPix ) {
                    iMax = i;
                    maxProdPix = prodPix;
                }
            }
        }
		return (Banner) banners.elementAt(iMax);
	}

}
