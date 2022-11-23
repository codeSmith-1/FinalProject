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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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
	private boolean diaperLow;
	@Column(name ="wipes_low")
	private boolean wipesLow;
	
	private String activities;
	
	private String notes;
	
	@Column(name = "report_date")
	private LocalDate reportDate;
	
	@OneToMany(mappedBy="dailyReport")
	private List<ReportImage> images;
	
	@OneToMany(mappedBy="dailyReport")
	private List<MoodEntry> moodEntries;
	
	public List<MoodEntry> getMoodEntries() {
		return moodEntries;
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
		return "DailyReport [id=" + id + ", timeIn=" + timeIn + ", timeOut=" + timeOut + ", diaperLow=" + diaperLow
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

	public boolean isDiaperLow() {
		return diaperLow;
	}

	public void setDiaperLow(boolean diaperLow) {
		this.diaperLow = diaperLow;
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
	
	//child
	
	

}
