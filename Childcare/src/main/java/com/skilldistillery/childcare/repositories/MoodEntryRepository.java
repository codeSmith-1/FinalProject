package com.skilldistillery.childcare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.MoodEntry;
import com.skilldistillery.childcare.entities.MoodId;

public interface MoodEntryRepository extends JpaRepository<MoodEntry, MoodId> {
	public List<MoodEntry> findByDailyReportId(int reportId);
	
	
}
