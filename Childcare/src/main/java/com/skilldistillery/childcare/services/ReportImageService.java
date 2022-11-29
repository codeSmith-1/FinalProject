package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.ReportImage;

public interface ReportImageService {

	List<ReportImage> showAll(String username);

	ReportImage show(int userId);

	ReportImage create(int username, ReportImage ri, int dailyReportId);

	void delete(int username, int rid);
}
