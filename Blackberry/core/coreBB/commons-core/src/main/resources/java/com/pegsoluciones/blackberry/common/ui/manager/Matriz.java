package com.pegsoluciones.blackberry.common.ui.manager;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;

import com.pegsoluciones.blackberry.common.ui.ScreenInfo;


/**
 *
 * Manager que dispone fields en forma de matriz
 * 
 * **/

public class Matriz extends Manager {
	
	private int iConFoco;
	private int jConFoco;



	/**
	 * @param v fields
	 * @param filas
	 * @param columnas
	 * @param width
	 * @param heigth
	 */
	public Matriz(Vector v, int filas, int columnas,int width, int heigth) {
		super(FOCUSABLE);
		fields  = new Field[columnas][filas];
		
		int n = 0;
		
		for(int i = 0; i < columnas ; i++)
			for(int j = 0; j < filas ; j++){
				fields[i][j] = (Field)v.elementAt(n++);
				add(fields[i][j]);
			}
		
		this.filas = filas;
		this.columnas = columnas;
		this.width = width;
		this.heigth = heigth;
		iConFoco = 0;
		jConFoco = 0;
		
	}



	Field[][] fields;
	int filas, columnas;
	int width,heigth;

	
	
	protected void sublayout(int width, int height) {
		
		int espacioH = (ScreenInfo.getScreenWidth() - columnas*fields[0][0].getPreferredWidth())/columnas + 1; 
		int espacioV = (ScreenInfo.getScreenHeight() - filas*fields[0][0].getPreferredHeight())/filas + 1; ; 
		
		for(int i = 0; i < columnas ; i++)
			for(int j = 0; j < filas ; j++){
				int ancho=fields[i][j].getPreferredWidth();
				int alto=fields[i][j].getPreferredHeight();
				int x =ancho*i;
				int y = alto* j;
				setPositionChild(fields[i][j],x,y);
				layoutChild(fields[i][j],ancho,alto);
				
				
		}
		
		setExtent(this.width,this.heigth);
	}
	
	public boolean navigationMovement(int dx,int  dy,int status,int time){
		
		System.out.println(dx + " " + dy + " " + iConFoco + " " + jConFoco);
		
		if(iConFoco + dx < 0 || iConFoco + dx > columnas -1 || jConFoco + dy < 0 || jConFoco + dy > filas -1 )			
			return super.navigationMovement(dx, dy, status, time);
		
		iConFoco += dx;
		jConFoco += dy;
		fields[iConFoco][jConFoco].setFocus();
		return true;
		
		
		
	}


}
