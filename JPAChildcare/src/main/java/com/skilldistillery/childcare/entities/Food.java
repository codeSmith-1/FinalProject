package com.skilldistillery.childcare.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Food {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String lunch;
	
	@Column(name="am_snack")
	private String amSnack;
	@Column(name="pm_snack")
	private String pmSnack;
	private String other;
	//day
	
	
	public int getId() {
		return id;
	}
	
	
	public Food() {
		super();
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
		Food other = (Food) obj;
		return id == other.id;
	}


	public void setId(int id) {
		this.id = id;
	}
	public String getLunch() {
		return lunch;
	}
	public void setLunch(String lunch) {
		this.lunch = lunch;
	}
	public String getAmSnack() {
		return amSnack;
	}
	public void setAmSnack(String amSnack) {
		this.amSnack = amSnack;
	}
	public String getPmSnack() {
		return pmSnack;
	}
	public void setPmSnack(String pmSnack) {
		this.pmSnack = pmSnack;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}


	@Override
	public String toString() {
		return "Food [id=" + id + ", lunch=" + lunch + ", amSnack=" + amSnack + ", pmSnack=" + pmSnack + ", other="
				+ other + "]";
	}
	
	
	
}
