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
public class Food {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
		
	private String description;
	
	@ManyToOne
	@JoinColumn(name="day_id")
	private DailyReport dailyReport;
	
	public DailyReport getDailyReport() {
		return dailyReport;
	}
	
	@ManyToOne
	@JoinColumn(name="food_type_id")
	private FoodType foodType;

	public void setDailyReport(DailyReport dailyReport) {
		this.dailyReport = dailyReport;
	}

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




	public Food(int id) {
		super();
		this.id = id;
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

	@Override
	public String toString() {
		return "Food [id=" + id + ", description=" + description + ", dailyReport=" + dailyReport + ", foodType="
				+ foodType + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	
	
}
