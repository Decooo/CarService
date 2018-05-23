package com.serwis.wrappers;

import com.serwis.entity.Parts;
import com.serwis.entity.PartsOrders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by jakub on 23.05.2018.
 */
public class CurrentOrderWrapper {

	private int id_parts_orders;
	private int id_parts;
	private String name;
	private int quantity;
	private double price;
	private double value;

	public ObservableList<CurrentOrderWrapper> currentOrderWrappers(List<Parts> parts, List<PartsOrders> partsOrders) {
		ObservableList<CurrentOrderWrapper> currentOrderWrappers = FXCollections.observableArrayList();
		for (int i = 0; i < partsOrders.size(); i++) {
			CurrentOrderWrapper part = new CurrentOrderWrapper();
			part.setId_parts_orders(partsOrders.get(i).getId_parts_orders());
			part.setName(parts.get(i).getName());
			part.setQuantity(partsOrders.get(i).getQuantity());
			part.setPrice(parts.get(i).getPrice());
			part.setId_parts(parts.get(i).getIdparts());
			part.setValue(parts.get(i).getPrice() * partsOrders.get(i).getQuantity());
			currentOrderWrappers.add(part);
		}
		return currentOrderWrappers;
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
