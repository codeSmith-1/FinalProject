package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.ReportImage;

public interface ReportImageService {

	List<ReportImage> showAll(int id);

	ReportImage show(int id);

	ReportImage create(int staffId, ReportImage ri, int dailyReportId);

	boolean delete(int rid);
	
	List<ReportImage> imagesByReportId(int reportId);
	
}
