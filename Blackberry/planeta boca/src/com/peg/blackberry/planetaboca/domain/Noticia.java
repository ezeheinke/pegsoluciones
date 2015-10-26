package com.peg.blackberry.planetaboca.domain;


 public class Noticia {

	private String id = "";
	private String autor = "";
	private String fecha = "";
	private String titulo = "";
	private String resumen = "";
	private String cantComentarios = "0";
	private String contenido = "";
	private String imagen ="";
	private String urlNoticia =""; 
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getCantComentarios() {
		return cantComentarios;
	}
	public void setCantComentarios(String cantComentarios) {
		this.cantComentarios = cantComentarios;
	}
	public String getContenido() {
		return contenido;
	}
	public void serContenido (String contenido) {
		this.contenido = contenido;
	}
	public String getImagen() {

		return imagen;
	}
	public void setImagen(String string) {
		this.imagen = string;
		
	}
	public void setUrlNoticia(String string) {
		this.urlNoticia = string;
		
	}
	public String getUrlNoticia(){
		return urlNoticia;
	}

	
	
		
}
