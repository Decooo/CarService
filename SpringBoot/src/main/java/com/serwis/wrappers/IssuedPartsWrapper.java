package com.serwis.wrappers;

import com.serwis.entity.IssuedParts;
import com.serwis.entity.Parts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by jakub on 31.05.2018.
 */
public class IssuedPartsWrapper {
	private int idIssuedWrapper;
	private String namePart;
	private int quantity;
	private String status;

	public ObservableList<IssuedPartsWrapper> issuedPartsWrappers(List<Parts> parts, List<IssuedParts> issuedParts) {
		ObservableList<IssuedPartsWrapper> partsWrappers = FXCollections.observableArrayList();
		for (int i = 0; i < issuedParts.size(); i++) {
			IssuedPartsWrapper part = new IssuedPartsWrapper();
			part.setIdIssuedWrapper(issuedParts.get(i).getIdIssuedParts());
			part.setNamePart(parts.get(i).getName());
			part.setQuantity(issuedParts.get(i).getQuantity());
			part.setStatus(issuedParts.get(i).getStatus());
			partsWrappers.add(part);
		}
		return partsWrappers;
	}

	public int getIdIssuedWrapper() {
		return idIssuedWrapper;
	}

	public void setIdIssuedWrapper(int idIssuedWrapper) {
		this.idIssuedWrapper = idIssuedWrapper;
	}

	public String getNamePart() {
		return namePart;
	}

	public void setNamePart(String namePart) {
		this.namePart = namePart;
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
