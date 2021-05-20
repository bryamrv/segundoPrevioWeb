package com.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.entity.*;
import com.util.*;

public class TeamDao implements Serializable{

	private static final long serialVersionUID = 1L;
	private  QueryGeneric<Team> query;
	
	public TeamDao() {
	}
	
	public Team findName(String name) {
		if(name != null) {
			this.query = new QueryGeneric<Team>();
			this.query.setQuery("SELECT * WHERE name = '"+name+"'");
			try {
				this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
				this.query.setRs(this.query.getPs().executeQuery());
				this.query.setEntity(null);
				while (this.query.getRs().next()) {
					this.query.setEntity(new Team());
					this.query.getEntity().setId(this.query.getRs().getString(1));
					this.query.getEntity().setName(this.query.getRs().getString(2));
					this.query.getEntity().setCountry(this.query.getRs().getString(3));
				}
				return this.query.getEntity();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				ConnectionGeneric.closeConnection();
			}
		}
		return null;
	}
	
	public List<Team> list() {
		this.query = new QueryGeneric<Team>();
		
		return this.query.getList();
	}
	
	public void insert(Team t) {
		if(t != null) {
			this.query = new QueryGeneric<Team>();
			this.query.setQuery("INSERT INTO team(name,country) VALUES (?,?)");
			try {
				Team tt = findName(t.getId());
				if(tt == null) {
					this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
					this.query.getPs().setString(1, t.getName());
					this.query.getPs().setString(2, t.getCountry());
					this.query.getPs().executeUpdate();
				}else {
					System.out.println("Ya existe un equipo con ese nombre.");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				ConnectionGeneric.closeConnection();
			}
		}
	}
	
	public void update(Team t) {
		if(t != null) {
			this.query = new QueryGeneric<Team>();
			query.setQuery("UPDATE team SET nombre = ?, country = ?  WHERE id = '"+t.getId()+"'");
			try {
				Team cc = findName(t.getId());
				cc = (cc.getName().equalsIgnoreCase(t.getName())) ? null : cc;
				if(cc == null) {
					this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
					this.query.getPs().setString(1, t.getName());
					this.query.getPs().setString(2, t.getCountry());
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
	
	public boolean delete(String id) {
		if(id != null) {
			this.query = new QueryGeneric<Team>();
			query.setQuery("DELETE team FROM team WHERE id = '"+id+"'");
			try {
				this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
				if (this.query.getPs().executeUpdate() > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				ConnectionGeneric.closeConnection();
			}
		}
		return false;
	}

	public QueryGeneric<Team> getQuery() {
		return query;
	}

	public void setQuery(QueryGeneric<Team> query) {
		this.query = query;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
