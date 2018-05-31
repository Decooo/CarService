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

	private int idIssued;
	private String namePart;
	private int quantity;
	private String status;
	private int idRepair;
	private int idPart;

	public int getIdRepair() {
		return idRepair;
	}

	public void setIdRepair(int idRepair) {
		this.idRepair = idRepair;
	}

	public int getIdPart() {
		return idPart;
	}

	public void setIdPart(int idPart) {
		this.idPart = idPart;
	}

	public ObservableList<IssuedPartsWrapper> issuedPartsWrappers(List<Parts> parts, List<IssuedParts> issuedParts) {
		ObservableList<IssuedPartsWrapper> partsWrappers = FXCollections.observableArrayList();
		for (int i = 0; i < issuedParts.size(); i++) {
			IssuedPartsWrapper part = new IssuedPartsWrapper();
			part.setIdIssued(issuedParts.get(i).getIdIssuedParts());
			part.setNamePart(parts.get(i).getName());
			part.setQuantity(issuedParts.get(i).getQuantity());
			part.setStatus(issuedParts.get(i).getStatus());
			part.setIdRepair(issuedParts.get(i).getIdRepairs());
			part.setIdPart(issuedParts.get(i).getIdParts());
			partsWrappers.add(part);
		}
		return partsWrappers;
	}

	public int getIdIssued() {
		return idIssued;
	}

	public void setIdIssued(int idIssued) {
		this.idIssued = idIssued;
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
