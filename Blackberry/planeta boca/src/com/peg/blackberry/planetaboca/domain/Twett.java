package com.peg.blackberry.planetaboca.domain;

public class Twett {
	
	public String getUser() {
		return user;
	}

	public String getUrlAvatar() {
		return urlAvatar;
	}

	public String getText() {
		return text;
	}

	String user,urlAvatar,text;
	private String retwetts;
	private String creado;
	private String id;

	public Twett(String user, String urlAvatar, String text, String retwetts, String creado, String id) {
		this.user = user;
		this.urlAvatar = urlAvatar;
		this.text = text;
		this.retwetts = retwetts;
		this.setCreado(creado);
		this.setId(id);
	}

	public String getRetwetts() {
		return retwetts;
	}

	public void setCreado(String creado) {
		this.creado = creado;
	}

	public String getCreado() {
		return creado;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
