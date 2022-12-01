package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Mood;
import com.skilldistillery.childcare.entities.MoodEntry;

public interface MoodService {

	List<MoodEntry> moodByReportId(int reportId);
	
	Mood create(String username, Mood mood, int dailyReportId);

}
