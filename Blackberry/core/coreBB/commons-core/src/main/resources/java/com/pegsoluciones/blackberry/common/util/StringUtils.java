package com.pegsoluciones.blackberry.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import net.rim.device.api.ui.Graphics;

/**
 * @author poza pablo
 * metodos de manejo de string
 *
 */
public abstract class StringUtils {

	
	/**
	 * Constructs a String using the data read from the passed InputStream.
	 * Data is read using a 1024-chars buffer. Each char is created using the passed 
	 * encoding from one or more bytes.
	 * 
	 * <p>If passed encoding is null, then the default BlackBerry encoding (ISO-8859-1) is used.</p>
	 * 
	 * BlackBerry platform supports the following character encodings:
	 * <ul>
	 * <li>"ISO-8859-1"</li>
	 * <li>"UTF-8"</li>
	 * <li>"UTF-16BE"</li>
	 * <li>"UTF-16LE"</li>
	 * <li>"US-ASCII"</li>
	 * </ul>
	 * 
	 * @param in - InputStream to read data from.
	 * @param encoding - String representing the desired character encoding, can be null.
	 * @return String created using the char data read from the passed InputStream.
	 * @throws IOException if an I/O error occurs.
	 * @throws UnsupportedEncodingException if encoding is not supported.
	 */
	public static String getStringFromStream(InputStream in, String encoding) throws IOException {
		
		InputStreamReader reader;
	    if (encoding == null) {
	        reader = new InputStreamReader(in);
	    } else {
	        reader = new InputStreamReader(in, encoding);            
	    }

	    StringBuffer sb = new StringBuffer();

	    final char[] buf = new char[1024];
	    int len;
	    while ((len = reader.read(buf)) > 0) {
	        sb.append(buf, 0, len);
	    }
	    return sb.toString();
	}
	
	
	/**
	 * @param s
	 * @param max
	 * @return s si el su largo es menor max, si no s con un largo de max con 
	 *  tres puntos 
	 */
	public static String trucateStringPuntitos(String s, int max){
		if(s.length() <= max)
			return s; 
		if(max<s.length())
			return s.substring(0, max).trim() + "..." ;
		else
			return s.substring(0,s.length()-1).trim() + "..." ;	
	}

	/**
	 * Retorna unaCadena separada por c en un vector
	 * 
	 * @param String: unaCadena
	 * @param char: c
	 * @return Vector
	 */
	public static Vector parse(String string,char c) {

		Vector v = new Vector();		
		StringBuffer buf = new StringBuffer();;

		int largo = string.length(); 
		int i = 0;		
		while(largo != i){			
			char c1 = (char)string.getBytes()[i];		
			if(c1 == c){			
				v.addElement(new String(buf));
				buf = new StringBuffer();
			}
			else{
				buf.append(c1);
				if(largo == i + 1)
					v.addElement(new String(buf));
			}	
			i++;
		}		
		return v;
	}    	

	public static String formatPrice(String p){

		int chau = p.indexOf(',');

		if(chau != -1)
			return p;

		int coma = p.indexOf('.'); 
		String centavos;


		String pesos = null; 

		if(coma == -1 ){
			centavos = ".00";
			pesos =  p;
		}
		else{
			centavos = p.substring(coma,p.length());
			pesos = p.substring(0,coma);
		}

		System.out.println("Pesos: " + pesos);
		System.out.println("Centavos: " + centavos);

		String centenares,miles;

		if(p.length() >= 4)
			centenares= pesos.substring(pesos.length() - 3,pesos.length());

		else
			return p; // es menor que 1000

		miles = pesos.substring(0,pesos.length()-3);

		if(!miles.equals(""))
			miles = miles.concat(",");

		return miles + centenares  + centavos;
	}

	public static String replaceAll(String s, String before, String after) {
		if(s == null)	
			return null;
		int index = 0;
		while (true) {
			int next = s.indexOf(before, index);
			if (next < 0) {
				return s;
			}
			s = s.substring(0, next) + after + s.substring(next + before.length());
			index = next + after.length();
		}
	}

	public static String elementosSeparadosPor(Vector vectorString,String separador) {
		String s = new String();

		for(int i = 0; i < vectorString.size();i++)
			s += (String)vectorString.elementAt(i) + (i == vectorString.size() -1 ? "" : separador);

		return s;
	}



	/**
	 * Dibuja a cadena en g en la posicions (xt,yt) 
	 * 
	 * @param g
	 * @param xT
	 * @param yT
	 * @param cadena
	 * @param largoMax por renglon
	 * @param renglonesMax
	 * @return
	 */
	public static int drawTitulo(Graphics g,int xT, int yT
			,String cadena, int largoMax,int renglonesMax){
		String r1 = null;
		String aux = cadena;

		try {
			r1 = new String("".getBytes(),"iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Vector v = parse(cadena,' ') ;

		if(v.size()>0)
			r1= r1.concat( ((String)v.elementAt(0)).concat(" "));
		else
			return 1;

		int i = 1; 
		while( v.size() > i && (r1 + (String)v.elementAt(i)).length() <= largoMax)
			r1 =r1.concat( ((String)v.elementAt(i++)).concat(" "));

		if(renglonesMax == 1){
			g.drawText(trucateStringPuntitos(cadena, largoMax), xT,yT);
			return 1;
		}else
			g.drawText(cadena.substring(0,r1.length() - 1),xT,yT);

		if(v.size() <= i )
			return 1;

		int pos = posicionDelCaracter(aux,' ',i);

		return 1 + drawTitulo(g,xT,yT + g.getFont().getHeight()+1,aux.substring(pos,aux.length()),largoMax,renglonesMax - 1);
	}	

	public static String decoder(String s){
		s=replaceAll(s,"&reg;", "�");
		s= replaceAll(s,"#160;", " ");
		s= replaceAll(s,"&quot;","");
		s= replaceAll(s,"&apos;","'");
		s= replaceAll(s,"&lt;", "<");
		s= replaceAll(s,"&gt;", ">");
		s= replaceAll(s,"middot;", "�");
		s= replaceAll(s, "&deg", "�");
		s= replaceAll(s, "&aacute;", "�");
		s= replaceAll(s, "&amp", "&");
		s= replaceAll(s, "&ocirc", "�");
		s= replaceAll(s,"&Aacute;","�");
		s= replaceAll(s,"&Eacute;", "�");
		s= replaceAll(s,"&Iacute;", "�");
		s= replaceAll(s,"&Oacute;", "�");
		s= replaceAll(s,"&Uacute;", "�");
		s= replaceAll(s,"&aacute;","�");
		s= replaceAll(s,"&eacute;", "�");
		s= replaceAll(s,"&iacute;", "�");
		s= replaceAll(s,"&oacute;", "�");
		s= replaceAll(s,"&uacute;", "�");
		s= replaceAll(s,"&amp;", "&");
		s= replaceAll(s,"&ntilde;", "�");
		s= replaceAll(s,"&Ntilde;", "N");
		s= replaceAll(s,"&;", "");
		s= replaceAll(s,"Aacute;","�");
		s= replaceAll(s,"Eacute;", "�");
		s= replaceAll(s,"Iacute;", "�");
		s= replaceAll(s,"Oacute;", "�");
		s= replaceAll(s,"Uacute;", "�");
		s= replaceAll(s,"aacute;","�");
		s= replaceAll(s,"eacute;", "�");
		s= replaceAll(s,"iacute;", "�");
		s= replaceAll(s,"oacute;", "�");
		s= replaceAll(s,"uacute;", "�");
		s= replaceAll(s,"&amp;", "&");
		s= replaceAll(s,"ntilde;", "�");
		s= replaceAll(s,"Ntilde;", "N");

		return s;


	}	


	/**
	 * 
	 * devuelve la posicion del caracter c en su aparcion numero nro
	 * 
	 * 
	 * @param string
	 * @param c
	 * @param numero
	 * @return
	 */
	private static int posicionDelCaracter(String string, char c, int nro) {

		int largo = string.length(); 
		int i = 0;
		int nro1 = 0;

		while(largo != i &&  nro1 != nro){
			char c1 = (char)string.getBytes()[i++];
			if(c == c1)
				nro1++;
		}


		if(nro1 == nro)
			return i;
		return 0;
	}		
	
	/**
	 * 
	 * 
	 * decodifica un string en formato hexa a un string de texto usando 
	 * el encoding especificado
	 * 
	 * 
	 * @param hex - la cadena en hexa a ser transformada en string
	 * @param encoding - en encoding que se va a utilizar
	 * @return el string decodificado
	 * @throws UnsupportedEncodingException 
	 */
	public static final String hexStringToEncodedString(final String hex, 
			final String encoding) throws UnsupportedEncodingException {
        byte [] bytes = new byte[(hex.length() / 2)];
        int j = 0;
        for ( int i=0; i<bytes.length; i++ ) {
            j = i * 2;
            String hex_pair = hex.substring(j,j+2);
            byte b = (byte) (Integer.parseInt(hex_pair, 16) & 0xFF);
            bytes [i] = b;
        }
        String returnString = null;
        returnString = new String(bytes, encoding);
        
        return returnString;
    }
}
