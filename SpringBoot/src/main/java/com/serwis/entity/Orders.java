package com.serwis.entity;

import javax.persistence.*;

/**
 * Created by jakub on 23.05.2018.
 */
@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idOrders;
	private int idPartsOrders;
	private String status;
	private double value;

	@Override
	public String toString() {
		return "Orders{" +
				"idOrders=" + idOrders +
				", idPartsOrders=" + idPartsOrders +
				", status='" + status + '\'' +
				", value=" + value +
				'}';
	}

	public int getIdOrders() {
		return idOrders;
	}

	public void setIdOrders(int idOrders) {
		this.idOrders = idOrders;
	}

	public int getIdPartsOrders() {
		return idPartsOrders;
	}

	public void setIdPartsOrders(int idPartsOrders) {
		this.idPartsOrders = idPartsOrders;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
