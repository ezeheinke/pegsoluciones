package com.peg.blackberry.planetaboca.domain;

public class SubCategoria {

	String nombre;
	String idServicio;
	
	public SubCategoria(String nombre, String idServicio) {
		super();
		this.nombre = nombre;
		this.idServicio = idServicio;
	}

	public String getNombre() {
		return nombre;
	}

	public String getIdServicio() {
		return idServicio;
	}
	
}
