package com.serwis.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jakub on 15.03.2018.
 */
@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue
	private int idusers;
	private String username;
	private String password;
	private String role;

	public int getIdusers() {
		return idusers;
	}

	public void setIdusers(int idusers) {
		this.idusers = idusers;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Users{" +
				"idusers=" + idusers +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", role='" + role + '\'' +
				'}';
	}

}
