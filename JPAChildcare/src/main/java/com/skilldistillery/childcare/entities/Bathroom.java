package com.skilldistillery.childcare.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bathroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	@Column(name="bathroom_time")
	private String bathroomTime;

	@JoinColumn(name="bathroom_type_id")
	@ManyToOne
	private BathroomType type;
	
	@JoinColumn(name="day_id")
	@ManyToOne
	private DailyReport day;
	
	@JoinColumn(name="staff_id")
	@ManyToOne
	private Staff staff;
	
	public Bathroom() {
		
	}
	
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
	
	public BathroomType getType() {
		return type;
	}

	public void setType(BathroomType type) {
		this.type = type;
	}

	public DailyReport getDay() {
		return day;
	}

	public void setDay(DailyReport day) {
		this.day = day;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
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
}
