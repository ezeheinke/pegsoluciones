package com.pegsoluciones.blackberry.common.util;

public class ExBoolean {

	private boolean value;

	public ExBoolean(boolean value) {
		this.value = value;
	}

	public boolean isTrue() {
		return value;
	}

	public boolean isFalse() {
		return !value;
	}	
	
	public void setValue(boolean value) {
		this.value = value;
	}
}
