package com.serwis.entity;

import javax.persistence.*;

/**
 * Created by jakub on 18.04.2018.
 */
@Entity
@Table(name = "user_role")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUserRole;
	private String role;

	@Override
	public String toString() {
		return "UserRole{" +
				"idUserRole=" + idUserRole +
				", role='" + role + '\'' +
				'}';
	}

	public int getIdUserRole() {
		return idUserRole;
	}

	public void setIdUserRole(int idUserRole) {
		this.idUserRole = idUserRole;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
