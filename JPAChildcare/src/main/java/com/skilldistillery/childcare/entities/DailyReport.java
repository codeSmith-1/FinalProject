package com.skilldistillery.childcare.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "daily_report")
@Entity
public class DailyReport {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	@Column(name = "time_in")
	@CreationTimestamp
	private LocalDateTime timeIn;
	
	@Column(name = "time_out")
	@CreationTimestamp
	private LocalDateTime timeOut;
	
	@Column(name ="diaper_low")
	private boolean diapersLow;
	
	@Column(name ="wipes_low")
	private boolean wipesLow;
	
	private String activities;
	
	private String notes;
	
	@Column(name = "report_date")
	private LocalDate reportDate;
	
	@JsonIgnore
	@OneToMany(mappedBy="dailyReport")
	private List<ReportImage> images;
	
	@JsonIgnore
	@OneToMany(mappedBy="dailyReport")
	private List<MoodEntry> moodEntries;
	
	@JsonIgnoreProperties({"dailyReport"})
	@ManyToOne
	@JoinColumn(name="kid_id")
	private Kid kid;
	
	@JsonIgnoreProperties({"day"})
	@OneToOne(mappedBy="day")
	private Nap nap;
	
	public List<MoodEntry> getMoodEntries() {
		return moodEntries;
	}
	
	

	public Nap getNap() {
		return nap;
	}



	public void setNap(Nap nap) {
		this.nap = nap;
	}



	public List<ReportImage> getImages() {
		return images;
	}


	public void setImages(List<ReportImage> images) {
		this.images = images;
	}


	public void setMoodEntries(List<MoodEntry> moodEntries) {
		this.moodEntries = moodEntries;
	}

	public DailyReport() {
		super();
	}

	@Override
	public String toString() {
		return "DailyReport [id=" + id + ", timeIn=" + timeIn + ", timeOut=" + timeOut + ", diaperLow=" + diapersLow
				+ ", wipesLow=" + wipesLow + ", activities=" + activities + ", notes=" + notes + ", reportDate="
				+ reportDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(LocalDateTime timeIn) {
		this.timeIn = timeIn;
	}

	public LocalDateTime getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(LocalDateTime timeOut) {
		this.timeOut = timeOut;
	}

	public boolean isDiapersLow() {
		return diapersLow;
	}

	public void setDiapersLow(boolean diaperLow) {
		this.diapersLow = diaperLow;
	}

	public boolean isWipesLow() {
		return wipesLow;
	}

	public void setWipesLow(boolean wipesLow) {
		this.wipesLow = wipesLow;
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public LocalDate getReportDate() {
		return reportDate;
	}

	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}
	

	public Kid getKid() {
		return kid;
	}


	public void setKid(Kid kid) {
		this.kid = kid;
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
		DailyReport other = (DailyReport) obj;
		return id == other.id;
	}	

}
