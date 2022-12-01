package com.skilldistillery.childcare.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="mood_entry")
public class MoodEntry {
	
	@EmbeddedId
	private MoodId id;
	
	
	@Column(name="entered_at")
	@CreationTimestamp
	private LocalDateTime enteredAt;
	
	@JsonIgnoreProperties({"moodEntries"})
	@ManyToOne
	@JoinColumn(name="daily_report_id")
	@MapsId(value="dailyReportId")
	private DailyReport dailyReport;
	
	@JsonIgnoreProperties({"moodEntries"})
	@ManyToOne
	@JoinColumn(name="mood_id")
	@MapsId(value="moodId")
	private Mood mood;


	public DailyReport getDailyReport() {
		return dailyReport;
	}


	public void setDailyReport(DailyReport dailyReport) {
		this.dailyReport = dailyReport;
	}


	public Mood getMood() {
		return mood;
	}


	public void setMood(Mood mood) {
		this.mood = mood;
	}


	public MoodId getId() {
		return id;
	}


	public void setId(MoodId id) {
		this.id = id;
	}


	public LocalDateTime getEnteredAt() {
		return enteredAt;
	}


	public void setEnteredAt(LocalDateTime enteredAt) {
		this.enteredAt = enteredAt;
	}


	@Override
	public String toString() {
		return "MoodEntry [id=" + id + ", enteredAt=" + enteredAt + "]";
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
		MoodEntry other = (MoodEntry) obj;
		return Objects.equals(id, other.id);
	}
	
	public MoodEntry() {}

}
