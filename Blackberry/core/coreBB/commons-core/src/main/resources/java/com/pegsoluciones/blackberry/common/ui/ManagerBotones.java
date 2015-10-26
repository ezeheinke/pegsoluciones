package com.pegsoluciones.blackberry.common.ui;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;

import com.pegsoluciones.blackberry.common.ui.field.BotonFieldAsociado;
import com.pegsoluciones.blackberry.common.ui.field.ImageButtonField;



/**
 * @author Poza Pablo
 * 
 * @Descripcion Se le pasa la misma insancia a los botones que pertenecen por ejemplo a una mismas
 * 	barra cada vez que se aprete un boton se encarga de apagar al resto.
 * 	
 *	@public ManagerBotones(Vector botones)
 * 	@public ManagerBotones(Vector botones,Field f, Manager m)
 * 
 */
public class ManagerBotones {

	Vector botones;
	private Field f;
	private GetterFieldInterface getter;
	private Manager m;
	private ImageButtonField botonapretado;
	private ImageButtonField botonanterior;
	private ImageButtonField boton;

	public ManagerBotones(Vector botones) {
		this.botones = botones;
	}

	/**
	 * 
	 * Sirve para administrar una barra de botones en la cual cada boton tenga asociado un field o manager,
	 * para hacerlo a nivel pantallas manejar con pushManager, etc en SimulaScreen, es para un manager con una barra de 
	 * botones que al apretar cada uno mostrara un manager distinto
	 * 
	 * @param botones, los botones de este vector deben implementar GetterFieldInterface asi que cada 
	 * 				   vez que se apretan nos dan el field asociado
	 * @param f	es el fiel que pertenece a m el cual sera cambiado por el fiel asociado al boton que se apreta
	 * 
	 * @param m manager en el cual se agrego f, y en el cual se reemplazara a f por el fiel asociado al boton que se apreta
	 * 
	 * 
	 */
	
	public ManagerBotones(Vector botones,Field f, Manager m){
		this.botones = botones;
		this.f = f;
		this.m = m;
	}
	

	public ManagerBotones() {
		botones = new Vector();
	}

	
	/**
	 * Agrega un a b al listado de botones manegas por este manager
	 * */
	public void addElementBotton(ImageButtonField b){
		botones.addElement(b);
	}


	/**
	 * Metodo al que llamara el boton que fue apretado pasandose el como parametro
	 * */
	public void OnClick(ImageButtonField boton){
		botonanterior 	= botonapretado;
		botonapretado 	= boton;
		this.boton		= boton;
		boton.prendete();
		
		for(int i = 0 ; i < botones.size();i++){
			ImageButtonField b = (ImageButtonField) botones.elementAt(i);
			if(!b.equals(boton))
				b.apagate();
		}
		
		//intento de castear, si se castea realizo lo correspondiente para un boton de ese tipo
		try{
			BotonFieldAsociado botonFieldAsociado = (BotonFieldAsociado) boton;
			Field f1 = botonFieldAsociado.getFieldAsosciado();
			m.replace(f, f1);
			f = f1;
		}catch(Exception e){
			System.out.println("Error al casteas: " + e.getMessage());
		}
		
	}

	/**
	 * Setea el manager en el que se cambiaran field
	 * */
	public void setManager(Manager m) {
		this.m = m;
	}

	public void setField(Field f) {
		this.f = f;
	}
	
	public void rollbackStateButtons(){ 
		if(botonanterior != null){
			botonanterior.prendete();
			botonanterior.setFocus();
			boton.apagate();
			botonapretado = botonanterior;
			
		}
	}
	
}
