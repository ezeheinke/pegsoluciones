package com.pegsoluciones.blackberry.common.util;

	
public class Point{
	
	private double y,x;

	public Point(double x, double y) {
		this.y = y;
		this.x = x;
	}
	
	public Point(String x, String y) {
		this.y = Double.parseDouble(y);
		this.x = Double.parseDouble(x);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	



}
