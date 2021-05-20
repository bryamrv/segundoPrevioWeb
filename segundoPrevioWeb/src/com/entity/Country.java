package com.entity;

import java.io.Serializable;

public class Country implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	
	public Country() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
