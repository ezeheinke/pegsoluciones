package com.pegsoluciones.blackberry.common.collection;

import java.util.Vector;

import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.Comparator;

import com.pegsoluciones.blackberry.common.util.NodeString;

public abstract class CollectionUtils {

	
	
	public static Vector ordenarVector(Vector v,Comparator comparator) {
		
		NodeString[] a = new NodeString[v.size()];
		
		for(int i = 0; i < v.size() ; i++)
			a[i] = (NodeString)v.elementAt(i);
		
		Arrays.sort(a, comparator);
		return arrayToVector(a);
	
	}

	private static Vector arrayToVector(Object[] os) {
		Vector v = new Vector();
		for(int i = 0 ; i < os.length ; i++)
			v.addElement(os[i]);
		return v;
	}
	
	/**
	 * 
	 * Agrega en v todos los elementos de v2
	 * 
	 * */
	public static void addAll(Vector v, Vector v2) {
		for(int i = 0; i < v2.size(); i++)
			v.addElement(v2.elementAt(i));
		
	}
	
	/**
	 * 
	 * 
	 * @param v Vector de numero en formato String > "0"
	 * @return
	 */
	public static String getMenor(Vector v) {
		String min ="ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"; 
		for(int i = 0 ; i < v.size() ; i++){
			String s = (String)v.elementAt(i);
			if(min.compareTo(s) > 0)
				min = s;
		}
		return min;
	}	

}
