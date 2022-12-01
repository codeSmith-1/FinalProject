package com.skilldistillery.childcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.DailyReport;
import com.skilldistillery.childcare.entities.Nap;
import com.skilldistillery.childcare.repositories.DailyReportRepository;
import com.skilldistillery.childcare.repositories.NapRepository;

@Service
public class NapServiceImpl implements NapService {
	
	@Autowired
	public NapRepository napRepo;
	@Autowired
	public DailyReportRepository reportRepo;

	@Override
	public Nap napByReportId(int reportId) {
		Nap nap = napRepo.findByDayId(reportId);
		return nap;
	}

	@Override
	public Nap create(Nap nap, int reportId) {
		DailyReport dailyReport = reportRepo.queryById(reportId);
		nap.setDay(dailyReport);
		return napRepo.saveAndFlush(nap);
	}

	@Override
	public boolean delete(int napId) {
		Nap nap = napRepo.queryById(napId);
		if(nap != null) {
			napRepo.delete(nap);
		}
		return !napRepo.existsById(napId);
	}
}
