package com.peg.blackberry.planetaboca.ui.manager;

import net.rim.device.api.system.Display;

import com.peg.blackberry.planetaboca.ui.field.DeporteField;
import com.peg.blackberry.planetaboca.util.Colores;
import com.peg.blackberry.planetaboca.util.Strings;
import com.pegsoluciones.blackberry.common.ui.field.Linea;
import com.pegsoluciones.blackberry.common.ui.manager.ExVerticalFieldManager;

public class ListadoDeportesManager extends ExVerticalFieldManager {

	// Alto deportes y lines
	public static final int ALTO  = DeporteField.ALTO * 4 + 2 * 3;
	public static final int ANCHO = Display.getWidth() - 100;

	public ListadoDeportesManager() {
		super(ANCHO , ALTO);
		
		this.add(new DeporteField("FUTBOL",Strings.FUTBOL));
		this.add(new Linea(Colores.AZUL_BARRA, Colores.CASI_BLANCO,
				ListadoDeportesManager.ANCHO,2, 10,NON_FOCUSABLE));
		
		this.add(new DeporteField("BASQUET",Strings.BASKET));
		this.add(new Linea(Colores.AZUL_BARRA, Colores.CASI_BLANCO,
				ListadoDeportesManager.ANCHO,2, 10,NON_FOCUSABLE));
		
		this.add(new DeporteField("VOLEY", Strings.VOLEY));
		this.add(new Linea(Colores.AZUL_BARRA, Colores.CASI_BLANCO,
				ListadoDeportesManager.ANCHO,2, 10,NON_FOCUSABLE));
		
		this.add(new DeporteField("INSTITUCIONAL",Strings.INSTITUCIONAL));
	}

}
