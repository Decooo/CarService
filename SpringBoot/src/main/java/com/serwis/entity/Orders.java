package com.serwis.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jakub on 23.05.2018.
 */
@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idOrders;
	private String status;
	private double value;
	private Date date;

	@Override
	public String toString() {
		return "Orders{" +
				"idOrders=" + idOrders +
				", status='" + status + '\'' +
				", value=" + value +
				", date=" + date +
				'}';
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIdOrders() {
		return idOrders;
	}

	public void setIdOrders(int idOrders) {
		this.idOrders = idOrders;
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
