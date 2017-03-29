package com.pozasoft.android.osferroviarios.core;

public class IGEvent {

	private String name;
	private Object value;

	public Object getValue() {
		return value;
	}

	public IGEvent(String name) {
		this.name = name;
	}

	public String getName() {		
		return name;
	}

	public IGEvent(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

}
