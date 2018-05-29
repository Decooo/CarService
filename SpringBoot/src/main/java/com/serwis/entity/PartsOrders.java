package com.serwis.entity;

import javax.persistence.*;

/**
 * Created by jakub on 23.05.2018.
 */
@Entity
@Table(name = "parts_orders")
public class PartsOrders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPartsOrders;
	private int id_parts;
	private int idOrders;
	private int quantity;

	@Override
	public String toString() {
		return "PartsOrders{" +
				"idPartsOrders=" + idPartsOrders +
				", id_parts=" + id_parts +
				", idOrders=" + idOrders +
				", quantity=" + quantity +
				'}';
	}

	public int getIdPartsOrders() {
		return idPartsOrders;
	}

	public void setIdPartsOrders(int idPartsOrders) {
		this.idPartsOrders = idPartsOrders;
	}

	public int getId_parts() {
		return id_parts;
	}

	public void setId_parts(int id_parts) {
		this.id_parts = id_parts;
	}

	public int getIdOrders() {
		return idOrders;
	}

	public void setIdOrders(int idOrders) {
		this.idOrders = idOrders;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
