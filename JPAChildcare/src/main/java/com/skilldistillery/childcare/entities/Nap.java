package com.skilldistillery.childcare.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "time_start")
	private LocalDateTime timeStart;
	
	@Column(name = "time_finish")
	private LocalDateTime timeFinish;

	// day
	
	public Nap() {
		super();
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(LocalDateTime timeStart) {
		this.timeStart = timeStart;
	}

	public LocalDateTime getTimeFinish() {
		return timeFinish;
	}

	public void setTimeFinish(LocalDateTime timeFinish) {
		this.timeFinish = timeFinish;
	}

	@Override
	public String toString() {
		return "Nap [id=" + id + ", timeStart=" + timeStart + ", timeFinish=" + timeFinish + ", getId()=" + getId()
				+ ", getTimeStart()=" + getTimeStart() + ", getTimeFinish()=" + getTimeFinish() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
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
		Nap other = (Nap) obj;
		return id == other.id;
	}

}
