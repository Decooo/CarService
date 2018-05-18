package com.serwis.entity;

import javax.persistence.*;

/**
 * Created by jakub on 18.05.2018.
 */
@Entity
@Table(name = "parts")
public class Parts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idparts;
	private int id_type_parts;
	private String name;
	private int quantity;
	private double price;

	@Override
	public String toString() {
		return "Parts{" +
				"idparts=" + idparts +
				", id_type_parts=" + id_type_parts +
				", name='" + name + '\'' +
				", quantity=" + quantity +
				", price=" + price +
				'}';
	}

	public int getIdparts() {
		return idparts;
	}

	public void setIdparts(int idparts) {
		this.idparts = idparts;
	}

	public int getId_type_parts() {
		return id_type_parts;
	}

	public void setId_type_parts(int id_type_parts) {
		this.id_type_parts = id_type_parts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
