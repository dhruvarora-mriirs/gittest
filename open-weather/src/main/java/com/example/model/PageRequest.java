package com.example.model;

public class PageRequest {
	
	String appid="d8f9c3b73afcf9b59515ee8786c38ad1";
	
	String exclude="daily,minutely,hourly";
	double lat;
	double lon;
	String q;
	
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String getAPPID() {
		return appid;
	}
	public void setAPPID(String appid) {
		this.appid = appid;
	}
	public String getExclude() {
		return exclude;
	}
	public void setExclude(String exclude) {
		this.exclude = exclude;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}

	
	
}
