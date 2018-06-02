package com.serwis.wrappers;

import com.serwis.entity.Cars;
import com.serwis.entity.Clients;
import com.serwis.entity.Repairs;
import com.serwis.entity.TypeRepairs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;

/**
 * Created by jakub on 30.05.2018.
 */
public class RepairsWrapper {
	private Integer idRepairs;
	private String car;
	private String client;
	private Date date;
	private String status;
	private double price;
	private double dedicatedTime;
	private int idCar;
	private int idClient;
	private int idTypeRepair;
	private String typeRepairs;

	public String getTypeRepairs() {
		return typeRepairs;
	}

	public void setTypeRepairs(String typeRepairs) {
		this.typeRepairs = typeRepairs;
	}

	public int getIdCar() {
		return idCar;
	}

	public void setIdCar(int idCar) {
		this.idCar = idCar;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdTypeRepair() {
		return idTypeRepair;
	}

	public void setIdTypeRepair(int idTypeRepair) {
		this.idTypeRepair = idTypeRepair;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	private String comments;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDedicatedTime() {
		return dedicatedTime;
	}

	public void setDedicatedTime(double dedicatedTime) {
		this.dedicatedTime = dedicatedTime;
	}

	public ObservableList<RepairsWrapper> repairsWrappers(List<Cars> cars, List<Clients> clients, List<Repairs> repairs, List<TypeRepairs> typeRepairs) {
		ObservableList<RepairsWrapper> repairsWrappersList = FXCollections.observableArrayList();
		for (int i = 0; i < repairs.size(); i++) {
			RepairsWrapper repair = new RepairsWrapper();
			repair.setIdRepairs(repairs.get(i).getIdRepairs());
			repair.setDate(repairs.get(i).getStartDate());
			repair.setStatus(repairs.get(i).getStatus());
			repair.setCar(cars.get(i).getBrand() + " " + cars.get(i).getModel());
			repair.setClient(clients.get(i).getName() + " " + clients.get(i).getSurname());
			repair.setPrice(repairs.get(i).getPrice());
			repair.setDedicatedTime(repairs.get(i).getDedicatedTime());
			repair.setComments(repairs.get(i).getComments());
			repair.setIdTypeRepair(repairs.get(i).getIdTypeRepairs());
			repair.setIdCar(repairs.get(i).getIdCars());
			repair.setIdClient(repairs.get(i).getIdClient());
			repair.setTypeRepairs(typeRepairs.get(i).getType());
			repairsWrappersList.add(repair);
		}
		return repairsWrappersList;
	}

	@Override
	public String toString() {
		return "RepairsWrapper{" +
				"idRepairs=" + idRepairs +
				", car='" + car + '\'' +
				", client='" + client + '\'' +
				", date=" + date +
				", status='" + status + '\'' +
				'}';
	}

	public Integer getIdRepairs() {
		return idRepairs;
	}

	public void setIdRepairs(Integer idRepairs) {
		this.idRepairs = idRepairs;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
