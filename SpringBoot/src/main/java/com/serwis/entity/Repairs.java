package com.serwis.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jakub on 29.05.2018.
 */
@Entity
@Table(name = "repairs")
public class Repairs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRepairs;
	private int idCars;
	private int idClient;
	private int idTypeRepairs;
	private double dedicatedTime;
	private String comments;
	private String status;
	private double price;
	private Date startDate;

	@Override
	public String toString() {
		return "Repairs{" +
				"idRepairs=" + idRepairs +
				", idCars=" + idCars +
				", idClient=" + idClient +
				", idTypeRepairs=" + idTypeRepairs +
				", dedicatedTime=" + dedicatedTime +
				", comments='" + comments + '\'' +
				", status='" + status + '\'' +
				", price=" + price +
				", startDate=" + startDate +
				'}';
	}

	public int getIdRepairs() {
		return idRepairs;
	}

	public void setIdRepairs(int idRepairs) {
		this.idRepairs = idRepairs;
	}

	public int getIdCars() {
		return idCars;
	}

	public void setIdCars(int idCars) {
		this.idCars = idCars;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdTypeRepairs() {
		return idTypeRepairs;
	}

	public void setIdTypeRepairs(int idTypeRepairs) {
		this.idTypeRepairs = idTypeRepairs;
	}

	public double getDedicatedTime() {
		return dedicatedTime;
	}

	public void setDedicatedTime(double dedicatedTime) {
		this.dedicatedTime = dedicatedTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
