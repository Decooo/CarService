package com.serwis.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jakub on 05.04.2018.
 */
@Entity
@Table(name = "cars")
public class Cars {

	@Id
	@GeneratedValue
	private int idCars;
	private String brand;
	private String model;
	private int year_production;
	private String VIN;
	private String registration_number;

	@Override
	public String toString() {
		return "cars{" +
				"idCars=" + idCars +
				", brand='" + brand + '\'' +
				", model='" + model + '\'' +
				", year_production=" + year_production +
				", VIN='" + VIN + '\'' +
				", registration_number='" + registration_number + '\'' +
				'}';
	}

	public int getIdCars() {
		return idCars;
	}

	public void setIdCars(int idCars) {
		this.idCars = idCars;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear_production() {
		return year_production;
	}

	public void setYear_production(int year_production) {
		this.year_production = year_production;
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String VIN) {
		this.VIN = VIN;
	}

	public String getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}
}
