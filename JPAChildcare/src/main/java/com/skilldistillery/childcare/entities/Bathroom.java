package com.skilldistillery.childcare.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bathroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	@Column(name="bathroom_time")
	private String bathroomTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBathroomTime() {
		return bathroomTime;
	}

	public void setBathroomTime(String bathroomTime) {
		this.bathroomTime = bathroomTime;
	}

	@Override
	public String toString() {
		return "Bathroom [id=" + id + ", description=" + description + ", bathroomTime=" + bathroomTime + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bathroom other = (Bathroom) obj;
		return id == other.id;
	}
	
	//@ManyToOne day_id
	//Todo
	
	//@ManyToOne staff_id
	//Todo
	
	//@ManyToOne bathroom_type_id
	//Todo
	
	public Bathroom() {
		
	}
}
