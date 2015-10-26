package com.pegsoluciones.blackberry.common.ui.manager;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.pegsoluciones.blackberry.common.ui.BuilderObjectInterface;
import com.pegsoluciones.blackberry.common.util.NumberUtil;

public class ListadoPorDemanda extends VerticalFieldManager {

	private BuilderObjectInterface builder;
	private Vector v;
	private int w;
	private int h;

	public ListadoPorDemanda(Vector v, BuilderObjectInterface builder, int fieldsIniciales, int w, int h) {
		super(Manager.VERTICAL_SCROLL);
		this.builder = builder;
		this.v = v;
		this.w = w;
		this.h = h;
		
		for(int i = 0 ; i < fieldsIniciales ; i++)
			if(!addElementIfThereAre())
				break;			
	}
	
	public boolean addElementIfThereAre(){
		boolean hay = v.size() > 0;
		if(hay){
			Object obj = v.elementAt(0);
			v.removeElementAt(0);
			add((Field)builder.build(obj));
			System.out.println("Se agrego un field, field count: " + this.getFieldCount());
		}
		return hay;
	}
	
	public boolean navigationMovement(int dx, int dy, int status, int time){		
		final int dx1= dx;
		final int dy1 = NumberUtil.PositivoUnoNegativoMenosUno(dy);
		final int status1 = status;
		final int time1 = time;
		
		addElementIfNeeded(dy1);
		
		UiApplication.getUiApplication().invokeLater(new Runnable(){
			public void run(){
				trackwheel(dx1, dy1, status1, time1);
			}
		});		
		
		return false;
	}
	
	
	public void trackwheel(int dx, int dy, int status, int time){
		super.navigationMovement(dx, dy, status, time);
	}

	private boolean addElementIfNeeded(int dy) {
		// si el que va a ganar foco es el ultimo elemento
		int withFocus = this.getFieldWithFocusIndex() + dy;
		int anteUltimo = this.getFieldCount() - 2;
		boolean isNeeded = withFocus == anteUltimo;
		if(isNeeded)
			addElementIfThereAre();
		return isNeeded;
	}
	
	public void sublayout(int width,int height){
		super.sublayout(w,h);
	}

}
