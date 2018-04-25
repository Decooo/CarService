package com.serwis.wrappers;

import com.serwis.entity.Clients;
import com.serwis.entity.ServiceContracts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by jakub on 24.04.2018.
 */
public class ServiceContractsWrapper {

	private String name;
	private String surname;
	private double amountForParts;
	private double workingTime;
	private double remainingAmountForParts;
	private double remainingWorkingTime;

	public ObservableList<ServiceContractsWrapper> serviceContractsWrappers(List<Clients> clients, List<ServiceContracts> contracts){
		ObservableList<ServiceContractsWrapper> serviceContractsList = FXCollections.observableArrayList();
		for (int i = 0; i <contracts.size() ; i++) {
			ServiceContractsWrapper serviceContractsWrapper = new ServiceContractsWrapper();
			serviceContractsWrapper.setName(clients.get(i).getName());
			serviceContractsWrapper.setSurname(clients.get(i).getSurname());
			serviceContractsWrapper.setAmountForParts(contracts.get(i).getAmountForParts());
			serviceContractsWrapper.setWorkingTime(contracts.get(i).getWorkingTime());
			serviceContractsWrapper.setRemainingAmountForParts(contracts.get(i).getRemainingAmountForParts());
			serviceContractsWrapper.setRemainingWorkingTime(contracts.get(i).getRemainingWorkingTime());
			serviceContractsList.add(serviceContractsWrapper);
		}
		return serviceContractsList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public double getAmountForParts() {
		return amountForParts;
	}

	public void setAmountForParts(double amountForParts) {
		this.amountForParts = amountForParts;
	}

	public double getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(double workingTime) {
		this.workingTime = workingTime;
	}

	public double getRemainingAmountForParts() {
		return remainingAmountForParts;
	}

	public void setRemainingAmountForParts(double remainingAmountForParts) {
		this.remainingAmountForParts = remainingAmountForParts;
	}

	public double getRemainingWorkingTime() {
		return remainingWorkingTime;
	}

	public void setRemainingWorkingTime(double remainingWorkingTime) {
		this.remainingWorkingTime = remainingWorkingTime;
	}

}
