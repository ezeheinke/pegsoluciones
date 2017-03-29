package com.pozasoft.android.osferroviarios.service;

import java.util.ArrayList;

import com.pozasoft.android.osferroviarios.domain.Zona;

public class ZonasManager {

	private static ArrayList<Zona> zonas;

	public static Zona[] getZonas(){		
		Zona[] arrayZonas = new Zona[zonas.size()];
		for (int i = 0; i < arrayZonas.length; i++) {
			arrayZonas[i] = zonas.get(i);
		}
		return arrayZonas ;
	}

	public static void setZonas(ArrayList<Zona> zonas) {
		ZonasManager.zonas = zonas;
	}
	
	
}
