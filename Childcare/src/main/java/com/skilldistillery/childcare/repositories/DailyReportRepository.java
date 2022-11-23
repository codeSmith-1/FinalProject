package com.skilldistillery.childcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.DailyReport;

public interface DailyReportRepository extends JpaRepository<DailyReport, Integer> {
	DailyReport queryById(int id);
}
