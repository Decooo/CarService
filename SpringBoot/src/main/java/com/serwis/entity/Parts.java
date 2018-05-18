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
	private int idTypeParts;
	private String name;
	private int quantity;
	private double price;

	@Override
	public String toString() {
		return "Parts{" +
				"idparts=" + idparts +
				", idTypeParts=" + idTypeParts +
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

	public int getIdTypeParts() {
		return idTypeParts;
	}

	public void setIdTypeParts(int idTypeParts) {
		this.idTypeParts = idTypeParts;
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
