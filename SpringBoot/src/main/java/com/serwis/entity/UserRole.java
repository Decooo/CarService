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
	private int id_user_role;
	private String role;

	@Override
	public String toString() {
		return "UserRole{" +
				"id_user_role=" + id_user_role +
				", role='" + role + '\'' +
				'}';
	}

	public int getId_user_role() {
		return id_user_role;
	}

	public void setId_user_role(int id_user_role) {
		this.id_user_role = id_user_role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
