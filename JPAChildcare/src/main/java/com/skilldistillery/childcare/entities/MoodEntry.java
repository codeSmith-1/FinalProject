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

@Entity
@Table(name="mood_entry")
public class MoodEntry {
	
	@EmbeddedId
	private MoodId id;
	
	
	@Column(name="entered_at")
	private LocalDateTime enteredAt;
	
	@ManyToOne
	@JoinColumn(name="daily_report")
	@MapsId(value="dailyReportId")
	private DailyReport dailyReport;
	
	@ManyToOne
	@JoinColumn(name="mood_id")
	@MapsId(value="moodId")
	private Mood mood;


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
