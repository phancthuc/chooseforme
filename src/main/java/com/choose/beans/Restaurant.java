package com.choose.beans;

public class Restaurant {
	
	private int longitude;
	private int latitude;
	private String name;
	
	
	public Restaurant(String name) {
		this.name = name;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
