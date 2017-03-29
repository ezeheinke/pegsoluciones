package com.pozasoft.android.osferroviarios.service;

import com.pozasoft.android.osferroviarios.domain.Especialidad;

public class EspecialidadesManager {

	private static Especialidad[] especialidades;

	public static Especialidad[] getEspecialidades(){		
		return especialidades;
	}

	public static void setEspecialidades(Especialidad[] especialidades) {
		EspecialidadesManager.especialidades = especialidades;
	}
	
	
}
