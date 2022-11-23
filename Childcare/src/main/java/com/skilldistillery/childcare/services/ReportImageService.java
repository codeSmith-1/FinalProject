package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.ReportImage;

public interface ReportImageService {
	
	List<ReportImage> showAll(int username);
	ReportImage show(int username);
	
	ReportImage create(int username, ReportImage ri, int dailyReportId);
	ReportImage update(int username, ReportImage ri, int rid);
	void delete(int username, int rid);
}
