package com.jdo.poc.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Consultorio {
	
	@Id
	private Integer id;
	//...

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Consultorio [id=" + id + "]";
	}
	
	
}
