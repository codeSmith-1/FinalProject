package com.skilldistillery.childcare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.MoodEntry;

public interface MoodEntryRepository extends JpaRepository<MoodEntry, Integer> {
	public List<MoodEntry> findByDailyReportId(int reportId);
	
}
