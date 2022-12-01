package com.skilldistillery.childcare.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable 
public class MoodId implements Serializable{

private static final long serialVersionUID = 1L;

@Column(name="daily_report_id")
private int dailyReportId;

@Column(name="mood_id")
private int moodId;


public int getDailyReportId() {
	return dailyReportId;
}

public void setDailyReportId(int dailyReportId) {
	this.dailyReportId = dailyReportId;
}

public int getMoodId() {
	return moodId;
}

public void setMoodId(int moodId) {
	this.moodId = moodId;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}


@Override
public int hashCode() {
	return Objects.hash(dailyReportId, moodId);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	MoodId other = (MoodId) obj;
	return dailyReportId == other.dailyReportId && moodId == other.moodId;
}

public MoodId () {
	
}

public MoodId(int dailyReportId, int moodId) {
	super();
	this.dailyReportId = dailyReportId;
	this.moodId = moodId;
}

}
