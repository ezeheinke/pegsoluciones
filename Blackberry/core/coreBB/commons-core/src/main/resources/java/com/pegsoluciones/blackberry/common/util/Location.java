package com.pegsoluciones.blackberry.common.util;


public class Location {
    
    public double lat;
    
    
    public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public double lng;

    public Location( ) {
    }

    public Location( double latitude, double longitude ) {
        lat = latitude;
        lng = longitude;
    }
    
} 
