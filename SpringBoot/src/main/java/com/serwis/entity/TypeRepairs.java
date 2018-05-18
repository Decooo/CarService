package com.serwis.entity;

import javax.persistence.*;

/**
 * Created by jakub on 18.05.2018.
 */
@Entity
@Table(name = "typeRepairs")
public class TypeRepairs {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idTypeRepairs;
	private String type;

	@Override
	public String toString() {
		return "TypeRepairs{" +
				"idTypeRepairs=" + idTypeRepairs +
				", type='" + type + '\'' +
				'}';
	}

	public int getIdTypeRepairs() {
		return idTypeRepairs;
	}

	public void setIdTypeRepairs(int idTypeRepairs) {
		this.idTypeRepairs = idTypeRepairs;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
