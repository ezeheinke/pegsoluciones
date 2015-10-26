package com.peg.blackberry.planetaboca.ui.field;

import net.rim.device.api.ui.component.NullField;

public class ExNullField extends NullField {

	int w,h;

	public ExNullField(int w, int h) {
		super(NON_FOCUSABLE);
		this.w = w;
		this.h = h;
	}
	
	public void layout(int w,int h){
		setExtent(this.w, this.h);
	}
	
}
