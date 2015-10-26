package com.pegsoluciones.blackberry.common.ui.field;

import net.rim.device.api.system.Bitmap;

import com.pegsoluciones.blackberry.common.ui.GetterFieldInterface;
import com.pegsoluciones.blackberry.common.ui.ManagerBotones;


/**
 * @author poza pablo 
 *
 * boton con 4 estados:
 * 	-off
 * 	-on
 *  -unFo
 *  -unFocus no roll
 *  
 *  Cada vez que es apretado se encarga de avisarle a su ManagerBotones que
 *  avisara al resto de botones y mostrara su field asociado
 */
public class Boton4Estados extends BotonFieldAsociado {

	
	private Bitmap offPres;
	private Bitmap onPres;
	private Bitmap off;
	private Bitmap on;

	/**
	 * @param off
	 * @param on
	 * @param offFocus
	 * @param onPres
	 * @param mb: Manager de botones
	 * @param getterField : Objeto que nos retornara el field que se quiere 
	 * 						mostrar cuando se aprete.
	 */
	public Boton4Estados(Bitmap off,Bitmap on,Bitmap offFocus,Bitmap onPres
			,ManagerBotones mb,GetterFieldInterface getterField){
		super(off,on,mb,getterField);
		this.offPres = offFocus;
		this.onPres = onPres;
		this.on = on;
		this.off = off;
	}
	
	public boolean trackwheelClick(int status, int time){
		if(isApretado())
			return true;
		super.trackwheelClick(status, time);						
		return true;
	}

	public void cambiarImagenes() {
		if(isApretado())
			setBitmaps(offPres, onPres);
		else
			setBitmaps(off,on);
	}
	
	 public void changeEstado(){
		 apretado = !apretado;
		 cambiarImagenes();
	 }

}
