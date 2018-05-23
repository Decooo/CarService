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
	private int id_parts_orders;
	private int id_parts;
	private int idOrders;
	private int quantity;

	@Override
	public String toString() {
		return "PartsOrders{" +
				"id_parts_orders=" + id_parts_orders +
				", id_parts=" + id_parts +
				", idOrders=" + idOrders +
				", quantity=" + quantity +
				'}';
	}

	public int getId_parts_orders() {
		return id_parts_orders;
	}

	public void setId_parts_orders(int id_parts_orders) {
		this.id_parts_orders = id_parts_orders;
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
