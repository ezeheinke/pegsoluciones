package com.peg.blackberry.planetaboca.domain;

public class Comentario {

	private String nombre;
	private String mail;
	private String comentario;
	private String fecha;
	private String id;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public void setId(String id) {
		this.id = id;
		
	}
	public String getId(){
		return id;
	}
	
	
	
}
