package com.pozasoft.android.osferroviarios.service;

import java.util.Hashtable;

import com.pozasoft.android.osferroviarios.domain.Barrio;

public class BarrriosManager {

	private static Hashtable<String, Barrio[]> barrios;	

	public static void setBarriosXZona(Hashtable<String, Barrio[]>  barrios) {
		BarrriosManager.barrios = barrios;
	}

	public static Barrio[] getBarrios(String idZona){
				
		return barrios.get(idZona);
	}
	
	
}
