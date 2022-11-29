package com.skilldistillery.childcare.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.DailyReport;
import com.skilldistillery.childcare.services.DailyReportService;

@RestController
@RequestMapping("api/")
@CrossOrigin({"*", "http://localhost/"})
public class DailyReportController {
	
	@Autowired
	public DailyReportService reportSvc;
	
	@GetMapping("reports")
	public List<DailyReport> listReports(Principal principal){
		List<DailyReport> reports = reportSvc.listAllReports(principal.getName());
		return reports;
		
	}

}
