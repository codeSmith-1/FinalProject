package com.skilldistillery.childcare.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.DailyReport;
import com.skilldistillery.childcare.entities.Kid;
import com.skilldistillery.childcare.entities.User;
import com.skilldistillery.childcare.repositories.DailyReportRepository;
import com.skilldistillery.childcare.repositories.KidRepository;
import com.skilldistillery.childcare.repositories.UserRepository;

@Service
public class DailyReportServiceImpl implements DailyReportService {

	@Autowired
	public DailyReportRepository reportRepo;
	@Autowired
	public UserRepository userRepo;
	@Autowired
	public KidRepository kidRepo;
	
	@Override
	public List<DailyReport> listAllReports(String username) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			return reportRepo.findAll();
		} else {
			return null;
		}
	}

	@Override
	public DailyReport create(int kidId, String username) {
		Kid kid = kidRepo.queryById(kidId);
		DailyReport dailyReport = new DailyReport();
		dailyReport.setKid(kid);
		return reportRepo.saveAndFlush(dailyReport);
	}
	
	
	
	
	
}
