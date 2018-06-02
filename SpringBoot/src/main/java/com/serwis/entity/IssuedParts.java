package com.serwis.entity;

import javax.persistence.*;

/**
 * Created by jakub on 31.05.2018.
 */
@Entity
@Table(name = "issued_parts")
public class IssuedParts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idIssuedParts;
	private int idRepairs;
	private int idParts;
	private int quantity;
	private String status;

	@Override
	public String toString() {
		return "IssuedParts{" +
				"idIssuedParts=" + idIssuedParts +
				", idRepairs=" + idRepairs +
				", idParts=" + idParts +
				", quantity=" + quantity +
				", status='" + status + '\'' +
				'}';
	}

	public int getIdIssuedParts() {
		return idIssuedParts;
	}

	public void setIdIssuedParts(int idIssuedParts) {
		this.idIssuedParts = idIssuedParts;
	}

	public int getIdRepairs() {
		return idRepairs;
	}

	public void setIdRepairs(int idRepairs) {
		this.idRepairs = idRepairs;
	}

	public int getIdParts() {
		return idParts;
	}

	public void setIdParts(int idParts) {
		this.idParts = idParts;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
