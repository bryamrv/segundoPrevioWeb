package com.dao;

import java.io.Serializable;
import java.sql.SQLException;

import com.entity.Country;
import com.util.ConnectionGeneric;
import com.util.QueryGeneric;

public class CountryDao implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private  QueryGeneric<Country> query;
	
	public CountryDao() 
	{
	}
	
	public Country find(String id) 
	{
		if(id != null) 
		{
			this.query = new QueryGeneric<Country>();
			this.query.setQuery("SELECT * FROM country WHERE id = '"+id+"'");
			
			try 
			    {
				this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
				this.query.setRs(this.query.getPs().executeQuery());
				this.query.setEntity(null);
				while (this.query.getRs().next())
					
				{
					
				this.query.setEntity(new Country());
				this.query.getEntity().setId(this.query.getRs().getString(1));
				this.query.getEntity().setName(this.query.getRs().getString(2));
			}
				return this.query.getEntity();
			} catch (SQLException e) 
			{
				System.out.println(e.getMessage());
			}finally 
			{
				ConnectionGeneric.closeConnection();
			}
		}
		return null;
	}
	
	public Country findName(String name) 
	{
		if(name != null) 
		{
			this.query = new QueryGeneric<Country>();
			this.query.setQuery("SELECT * FROM country WHERE name = '"+name+"'");
			try {
				this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
				this.query.setRs(this.query.getPs().executeQuery());
				this.query.setEntity(null);
				while (this.query.getRs().next()) 
				{
					this.query.setEntity(new Country());
					this.query.getEntity().setId(this.query.getRs().getString(1));
					this.query.getEntity().setName(this.query.getRs().getString(2));
				}
				return this.query.getEntity();
			} 
			catch (SQLException e)
			{
				System.out.println(e.getMessage());
			}
			finally 
			{
				ConnectionGeneric.closeConnection();
			}
		}
		return null;
	}
	
	public void insert(Country c) {
		if(c != null) {
			this.query = new QueryGeneric<Country>();
			this.query.setQuery("INSERT INTO country(name) VALUES (?)");
			try {
				Country cc = findName(c.getId());
				if(cc == null) {
					this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
					this.query.getPs().setString(1, c.getName());
					this.query.getPs().executeUpdate();
				}else {
					System.out.println("Ya existe un pais con ese nombre.");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				ConnectionGeneric.closeConnection();
			}
		}
	}
	
	public void update(Country c) {
		if(c != null) {
			this.query = new QueryGeneric<Country>();
			query.setQuery("UPDATE country SET nombre = ? WHERE id = '"+c.getId()+"'");
			try {
				Country cc = findName(c.getId());
				cc = (cc.getName().equalsIgnoreCase(c.getName())) ? null : cc;
				if(cc == null) 
				{
					this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
					this.query.getPs().setString(1, c.getName());
					this.query.getPs().executeUpdate();
				}
				else 
				{
					System.out.println("Ya existe un pais con ese nombre.");
				}
			} 
			catch (SQLException e) 
			{
				System.out.println(e.getMessage());
			}
			finally 
			{
				ConnectionGeneric.closeConnection();
			}
		}
	}	

	public QueryGeneric<Country> getQuery() {
		return query;
	}

	public void setQuery(QueryGeneric<Country> query) {
		this.query = query;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
