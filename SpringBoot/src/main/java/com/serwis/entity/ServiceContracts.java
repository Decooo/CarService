package com.serwis.entity;

import javax.persistence.*;

/**
 * Created by jakub on 23.04.2018.
 */
@Entity
@Table(name = "serviceContracts")
public class ServiceContracts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idServiceContracts;
	private int idClient;
	private double remainingWorkingTime;
	private double remainingAmountForParts;
	private double workingTime;
	private double amountForParts;

	@Override
	public String toString() {
		return "serviceContracts{" +
				"idServiceContracts=" + idServiceContracts +
				", idClient=" + idClient +
				", remainingWorkingTime=" + remainingWorkingTime +
				", remainingAmountForParts=" + remainingAmountForParts +
				", workingTime=" + workingTime +
				", amountForParts=" + amountForParts +
				'}';
	}

	public int getIdServiceContracts() {
		return idServiceContracts;
	}

	public void setIdServiceContracts(int idServiceContracts) {
		this.idServiceContracts = idServiceContracts;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public double getRemainingWorkingTime() {
		return remainingWorkingTime;
	}

	public void setRemainingWorkingTime(double remainingWorkingTime) {
		this.remainingWorkingTime = remainingWorkingTime;
	}

	public double getRemainingAmountForParts() {
		return remainingAmountForParts;
	}

	public void setRemainingAmountForParts(double remainingAmountForParts) {
		this.remainingAmountForParts = remainingAmountForParts;
	}

	public double getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(double workingTime) {
		this.workingTime = workingTime;
	}

	public double getAmountForParts() {
		return amountForParts;
	}

	public void setAmountForParts(double amountForParts) {
		this.amountForParts = amountForParts;
	}
}
