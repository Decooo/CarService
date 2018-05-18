package com.serwis.entity;

import javax.persistence.*;

/**
 * Created by jakub on 18.05.2018.
 */
@Entity
@Table(name = "typeRepairs")
public class TypeParts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idTypeParts;
	private String type;

	@Override
	public String toString() {
		return "TypeParts{" +
				"idTypeParts=" + idTypeParts +
				", type='" + type + '\'' +
				'}';
	}

	public int getIdTypeParts() {
		return idTypeParts;
	}

	public void setIdTypeParts(int idTypeParts) {
		this.idTypeParts = idTypeParts;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
