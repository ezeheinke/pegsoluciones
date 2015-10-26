package com.pegsoluciones.blackberry.common.util;

import java.io.InputStream;

import org.w3c.dom.Node;

import com.pegsoluciones.blackberry.common.web.provider.RimWebserviceProvider;
import com.pegsoluciones.blackberry.common.xml.parsers.DocumentBuilderAdapter;
import com.pegsoluciones.blackberry.common.xml.parsers.RimDocumentBuilder;

/**
 * @author poza pablo
 * 
 * acceso a funciones de google maps api
 *
 */
public abstract class GoogleMapsUtil {
	
	/**
	 * @param direccion
	 * @return location
	 * 
	 * pasada un direccion en String te devuleve si puede localizarla la latitud
	 * longitud
	 */
	public static Location getLocationDireccionGoogleMaps(String direccion) {
		
		Node node = null;
		DocumentBuilderAdapter documentBuilder = new RimDocumentBuilder();
		
		try {
			
			// get final url
			String url = getUrlLocationDireccion(StringUtils.replaceAll(direccion," ","%20"),"xml");
			
			// call webservice
			RimWebserviceProvider webserviceProvider = new RimWebserviceProvider();
			InputStream in =webserviceProvider.loadResource(url);
			
			node= documentBuilder.parse(in).getChildNodes().item(3);
			for(int i = 0 ; i < node.getChildNodes().getLength() ; i++){
				Node n1 = node.getChildNodes().item(i);
				String name = n1.getNodeName();
				System.out.println(name);
				if(name.equals("geometry")){
					node = n1.getChildNodes().item(1);
					break;
				}
			}
			
			String sLat = node.getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
			String sLon = node.getChildNodes().item(3).getChildNodes().item(0).getNodeValue();
			return new Location(Double.parseDouble(sLat),Double.parseDouble(sLon));
		} catch (Exception e) {
			return new Location(0,0);
		}
		

		
		
	}
	

	/**
	 * @param direccion
	 * @param format
	 * @return url del xml con las coordenas de la direccion
	 */
	private static String getUrlLocationDireccion(String direccion,String format){
		return "http://maps.googleapis.com/maps/api/geocode/".concat(format).
				concat("?addElementress=").concat(direccion).concat("&sensor=false");
	}
	
	public static String getStringGooglePinMaps(String color,char label,double lat, double lon){
		return "&markers=color:" + color + "|label:" + label + "|"
				+ String.valueOf(lat )+","+ String.valueOf(lon);
	}
	
	/**
	 * @param direccion
	 * @param zoom1
	 * @param w1
	 * @param h1
	 * @param label
	 * @return url de un mapa estatico con un pin en la direccion pasada como
	 * parametro
	 */
	public static String getUrlMapaConPin(String direccion, int zoom1, int w1, int h1,char label){
		String zoom = String.valueOf(zoom1);
		String w = String.valueOf(w1);
		String h = String.valueOf(h1);
		String url = "http://maps.google.com/maps/api/staticmap?center=".
				concat(direccion).concat("&zoom=").concat(zoom);
		return url.concat("&size=").concat(w).concat("x").concat(h)
			.concat("&sensor=false&markers=color:blue|label:")
			.concat(String.valueOf(label)).concat("|").concat(direccion);
	}
	
    /**
     * @param lat
     * @param lon
     * @param z
     * @param w
     * @param h
     * @return url de mapa statica centrado en (lat,lon) con zoom z y de w x h
     */
    public static String getGoogleMapsUrl(double lat,double lon,int z,int w, int h) {
    	return "http://maps.google.com/maps/api/staticmap?center=" + lat +","
    			+ lon + "&zoom=" + z + "&size=" + w + "x" + h 
    			+ "&sensor=false&mobile=true";
    }

	/**
	 * @param lat
	 * @param lon
	 * @param zoom1
	 * @param w1
	 * @param h1
	 * @param label
     * @return url de mapa statica centrado en (lat,lon) con zoom z ,de w x h, con
     * pin en el centro con el char label dibujad dentro del pin
	 */
	public static String getUrlMapaConPin(String lat,String lon, int zoom1, int w1, int h1, char label) {
		String zoom = String.valueOf(zoom1);
		String w = String.valueOf(w1);
		String h = String.valueOf(h1);
		String url = "http://maps.google.com/maps/api/staticmap?center="
				.concat(lat.concat(",").concat(lon))
				.concat("&zoom=").concat(zoom);
		return url.concat("&size=").concat(w).concat("x").concat(h)
				.concat("&sensor=false&markers=color:blue|label:").
				concat(String.valueOf(label)).concat("|").concat(lat.concat(lon));
	}
}
