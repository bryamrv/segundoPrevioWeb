package com.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.entity.*;
import com.util.*;

public class CyclistDao implements Serializable {
	private static final long serialVersionUID = 1L;

	private QueryGeneric<Cyclist> query;

	public CyclistDao() {
	}

	public Cyclist findName(String name) {
		if (name != null) {
			this.query = new QueryGeneric<Cyclist>();
			this.query.setQuery("SELECT * FROM cyclist WHERE name = '" + name + "'");
			try {
				this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
				this.query.setRs(this.query.getPs().executeQuery());
				this.query.setEntity(null);
				while (this.query.getRs().next()) {
					this.query.setEntity(new Cyclist());
					this.query.getEntity().setId(this.query.getRs().getInt(1));
					this.query.getEntity().setName(this.query.getRs().getString(2));
					this.query.getEntity().setEmail(this.query.getRs().getString(3));
					this.query.getEntity().setBirthdate(this.query.getRs().getString(4));
					this.query.getEntity().setCountry(this.query.getRs().getString(5));
					this.query.getEntity().setTeam(this.query.getRs().getString(4));
				}
				return this.query.getEntity();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				ConnectionGeneric.closeConnection();
			}
		}
		return null;
	}

	public List<Cyclist> list() {
		this.query = new QueryGeneric<Cyclist>();
		return this.query.getList();
	}

	public void insert(Cyclist t) {
		if (t != null) {
			this.query = new QueryGeneric<Cyclist>();
			this.query.setQuery("INSERT INTO cyclist(name,email,birthdate,country,team) VALUES (?,?,?,?,?)");
			try {
				Cyclist tt = findName(t.getId());
				if (tt == null) {
					this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
					this.query.getPs().setString(1, t.getName());
					this.query.getPs().setString(2, t.getCountry());
					this.query.getPs().executeUpdate();
				} else {
					System.out.println("Ya existe un equipo con ese nombre.");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				ConnectionGeneric.closeConnection();
			}
		}
	}

	public void update(Team t) {
		if (t != null) {
			this.query = new QueryGeneric<Team>();
			query.setQuery("UPDATE team SET nombre = ?, country = ?  WHERE id = '" + t.getId() + "'");
			try {
				Team cc = findName(t.getId());
				cc = (cc.getName().equalsIgnoreCase(t.getName())) ? null : cc;
				if (cc == null) {
					this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
					this.query.getPs().setString(1, t.getName());
					this.query.getPs().setString(2, t.getCountry());
					this.query.getPs().executeUpdate();
				} else {
					System.out.println("Ya existe un pais con ese nombre.");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				ConnectionGeneric.closeConnection();
			}
		}
	}

	public boolean delete(String id) {
		if (id != null) {
			this.query = new QueryGeneric<Team>();
			query.setQuery("DELETE team FROM team WHERE id = '" + id + "'");
			try {
				this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
				if (this.query.getPs().executeUpdate() > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
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
