package com.peg.blackberry.planetaboca.ui.manager;

import com.peg.blackberry.planetaboca.ui.field.BannerField;
import com.peg.blackberry.planetaboca.ui.field.HeaderField;
import com.pegsoluciones.blackberry.common.ui.manager.ExVerticalFieldManager;

public class PlanetaBocaBaseManager extends ExVerticalFieldManager{

	public PlanetaBocaBaseManager() {
		super(HeaderField.ALTO + BannerField.getInstance().getAltoBanner());
	}

}
