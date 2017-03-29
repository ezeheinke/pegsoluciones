package com.pozasoft.android.osferroviarios.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GlobalEventManager {

	//TODO: Hacer que se pueda tirar eventos desde thread
	private static GlobalEventManager instance;
	private static HashMap<String, List<IGEventListener>> listeners;
	
	static {		
		listeners = new HashMap<String, List<IGEventListener>>();
	}
	
	public static GlobalEventManager getInstance() {
		if (instance == null)
			instance = new GlobalEventManager();
		return instance;
	}

	private GlobalEventManager() {
	}
	
	public void addListener(String name,IGEventListener listener){
		addListener(new IGEvent(name),listener);
	}
	
	public void addListener(IGEvent event,IGEventListener listener){
		List<IGEventListener> list = listeners.get(event.getName());
		if(list == null){
			list = new ArrayList<IGEventListener>();
			listeners.put(event.getName(), list);
		}
		list.add(listener);		
	}
	
	public void notifyEvent(IGEvent event) {
		List<IGEventListener> list = listeners.get(event.getName());
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				IGEventListener listener = list.get(i);
				listener.onEvent(event);
			}
		}
	}
	
	public void notifyEvent(String name) {
		notifyEvent(new IGEvent(name));
	}
}
