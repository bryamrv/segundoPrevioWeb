package com.entity;

import java.io.Serializable;

public class Cyclist implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String email;
	private String birthdate;  // Al insertar la convierto a Date
	private String country;
	private String team;
	
	public Cyclist() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
