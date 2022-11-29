package com.skilldistillery.childcare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.ReportImage;

public interface ReportImageRepository extends JpaRepository<ReportImage, Integer> {
	ReportImage queryById(int id);
	
	List<ReportImage> findByDailyReportId(int reportId);
	
}
