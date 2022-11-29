package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.MoodEntry;

public interface MoodEntryService {

	List<MoodEntry> findByDailyReportId(int reportId);

}
