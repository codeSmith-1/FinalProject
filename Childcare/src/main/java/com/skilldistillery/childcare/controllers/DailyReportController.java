package com.skilldistillery.childcare.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.DailyReport;
import com.skilldistillery.childcare.entities.MoodEntry;
import com.skilldistillery.childcare.entities.Nap;
import com.skilldistillery.childcare.services.DailyReportService;
import com.skilldistillery.childcare.services.MoodEntryService;
import com.skilldistillery.childcare.services.NapService;

@RestController
@RequestMapping("api/")
@CrossOrigin({"*", "http://localhost/"})
public class DailyReportController {
	
	@Autowired
	public DailyReportService reportSvc;
	
	@Autowired
	public MoodEntryService moodEntryService;
	@Autowired
	public NapService napService;
	
	@GetMapping("reports")
	public List<DailyReport> listReports(Principal principal){
		List<DailyReport> reports = reportSvc.listAllReports(principal.getName());
		return reports;
		
	}
	
	@GetMapping("reports/mood/{reportId}")
	public List<MoodEntry> listMoodByReport(@PathVariable ("reportId") int reportId){
		List<MoodEntry> moods = moodEntryService.findByDailyReportId(reportId);
		return moods;
	}
	
	@GetMapping("naps/{reportId}")
	public Nap showNapByReport(@PathVariable ("reportId") int reportId){
		Nap nap = napService.napByReportId(reportId);
		return nap;
	}
	
	

}
