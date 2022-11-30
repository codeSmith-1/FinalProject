package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.DailyReport;

public interface DailyReportService {

	List<DailyReport> listAllReports(String username);
	
	DailyReport create(int kidId, String username);
	
	DailyReport update(DailyReport dailyReport);
	

}
