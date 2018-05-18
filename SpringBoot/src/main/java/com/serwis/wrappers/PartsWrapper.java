package com.serwis.wrappers;

import com.serwis.entity.Parts;
import com.serwis.entity.TypeParts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by jakub on 18.05.2018.
 */
public class PartsWrapper {

	private int idParts;
	private String name;
	private String type;
	private int quantity;
	private double price;

	public int getIdParts() {
		return idParts;
	}

	public void setIdParts(int idParts) {
		this.idParts = idParts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public ObservableList<PartsWrapper> PartsWrapper(List<Parts> parts, List<TypeParts> typeParts) {
		ObservableList<PartsWrapper> partsWrappers = FXCollections.observableArrayList();
		for (int i = 0; i < parts.size(); i++) {
			PartsWrapper part = new PartsWrapper();
			part.setIdParts(parts.get(i).getIdparts());
			part.setName(parts.get(i).getName());
			part.setQuantity(parts.get(i).getQuantity());
			part.setPrice(parts.get(i).getPrice());
			part.setType(typeParts.get(i).getType());
			partsWrappers.add(part);
		}
		return partsWrappers;
	}

}
