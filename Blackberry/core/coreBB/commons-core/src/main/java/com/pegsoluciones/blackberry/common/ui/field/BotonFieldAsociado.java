package com.pegsoluciones.blackberry.common.ui.field;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;

import com.pegsoluciones.blackberry.common.ui.GetterFieldInterface;
import com.pegsoluciones.blackberry.common.ui.ManagerBotones;


/**
 * @author poza pablo
 * 
 * boton con mismo comportamiento que ImageButtonField, salvo que tiene un objeto 
 * que devuelve un field, sirve, por ejemplo, cuando hay una barra de botones 
 * con cada uno con un field asociado
 *
 */
public class BotonFieldAsociado extends ImageButtonField{
	
	GetterFieldInterface getterField;

	/**
	 * @param bmpOFF
	 * @param bmpON
	 * @param toggleStyle
	 * @param getterField
	 */
	public BotonFieldAsociado(Bitmap bmpOFF, Bitmap bmpON, int toggleStyle, GetterFieldInterface getterField) {
		super(bmpOFF, bmpON);
		this.getterField = getterField;
	}

	/**
	 * @param bmpOFF
	 * @param bmpON
	 * @param getterField
	 */
	public BotonFieldAsociado(Bitmap bmpOFF, Bitmap bmpON, GetterFieldInterface getterField) {
		super(bmpOFF, bmpON);
		this.getterField = getterField;
	}

	/**
	 * @param name
	 * @param mb
	 * @param getterField
	 */
	public BotonFieldAsociado(String name, ManagerBotones mb, GetterFieldInterface getterField) {
		super(name, mb);
		this.getterField = getterField;
	}

	/**
	 * @param bmpOFF
	 * @param bmpON
	 * @param mb
	 * @param getterField
	 */
	public BotonFieldAsociado(Bitmap bmpOFF, Bitmap bmpON, ManagerBotones mb , GetterFieldInterface getterField) {
		super(bmpOFF, bmpON,mb);
		this.getterField = getterField;
	}
	
	/**
	 * @param bmpOFF
	 * @param bmpON
	 * @param getterField
	 */
	public BotonFieldAsociado(String bmpOFF, String bmpON, GetterFieldInterface getterField) {
		super(bmpOFF, bmpON);
		this.getterField = getterField;
	}

	/**
	 * @param name
	 * @param getterField
	 */
	public BotonFieldAsociado(String name, GetterFieldInterface getterField) {
		super(name);
		this.getterField = getterField;
	}


	public Field getFieldAsosciado() {
		return this.getterField.getField();
	}



}
