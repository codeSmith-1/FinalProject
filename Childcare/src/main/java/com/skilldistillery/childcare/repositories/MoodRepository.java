package com.skilldistillery.childcare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.Mood;

public interface MoodRepository extends JpaRepository<Mood, Integer> {
	Mood queryById(int id);
	
	List<Mood> findByDailyReportId(int reportId);
}
