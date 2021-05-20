package com.entity;

import java.io.Serializable;

public class Team implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String country;
	private String name;
	
	public Team() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
