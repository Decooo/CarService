package com.serwis.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jakub on 10.04.2018.
 */
@Entity
@Table(name = "clients")
public class Clients {

	@Id
	@GeneratedValue
	private int idClients;
	private String name;
	private String surname;
	private Long pesel;

	public int getIdClients() {
		return idClients;
	}

	public void setIdClients(int idClients) {
		this.idClients = idClients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public long getPesel() {
		return pesel;
	}

	public void setPesel(Long pesel) {
		this.pesel = pesel;
	}

	@Override
	public String toString() {
		return "Clients{" +
				"idClients=" + idClients +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", pesel=" + pesel +
				'}';
	}
}
