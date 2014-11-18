package com.sapient.global.thread;

import java.io.Serializable;

public class Department implements Serializable {
	private String name;
	private Location location = new Location("Noida");

	public Department() { }
	
	public Department(String name) { 
		this.name = name;
	}
	
	public Department(String name, Location location) { 
		this.name = name;
		this.location = location;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
