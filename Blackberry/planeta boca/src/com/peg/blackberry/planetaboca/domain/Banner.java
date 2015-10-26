package com.peg.blackberry.planetaboca.domain;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.EncodedImage;

import com.peg.blackberry.planetaboca.application.ApplicationEvent;
import com.peg.blackberry.planetaboca.ui.field.BannerField;
import com.pegsoluciones.blackberry.common.event.EventManager;
import com.pegsoluciones.blackberry.common.web.DescargaImagenesManager;
import com.pegsoluciones.blackberry.common.web.EsperaImagenEncodedInterface;
import com.pegsoluciones.blackberry.common.web.ImagesDownloader;

public class Banner implements EsperaImagenEncodedInterface {
	
	
	private String urlImagen,link;
	private Bitmap imagen;
	private String tamano;
	private String app;
	private String msj;
	private String nombre;
	private String direccion;
	private String hora;
	private String fecha;
	private String telefono;
	private BannerRunner bannerRunner;
	
	public void onClick() {
		bannerRunner.run(this);
	}
	
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getMsj() {
		return msj;
	}
	public void setMsj(String msj) {
		this.msj = msj;
	}
	public String getUrlImagen() {
		return urlImagen;
	}
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Bitmap getImagen() {
		return imagen;
	}
	public void setImagen(Bitmap imagen) {
		this.imagen = imagen;
	}
	public String getTamano() {
		return tamano;
	}
	public void setTamano(String tamano) {
		this.tamano = tamano;
	}
	public void descargarImagen() {
		DescargaImagenesManager.descargarImagenThreadEncoded(urlImagen, this);		
	}
	public void catchImagen(EncodedImage img, String ruta) {
		if(img != null){
			img = ImagesDownloader.scale(img, Display.getWidth(), 
					BannerField.getInstance().getAltoBanner());
			this.setImagen(img.getBitmap());
			EventManager.getInstance().notify(ApplicationEvent.BANNER_DESCARGADO, this);			
		}		
	}
	public void setBannerRunner(BannerRunner bannerRunner) {
		this.bannerRunner = bannerRunner;
	}
	public BannerRunner getBannerRunner() {
		return bannerRunner;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getHora() {
		return hora;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFecha() {
		return fecha;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTelefono() {
		return telefono;
	}

}
