package com.skilldistillery.childcare.services;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
		if (nap!=null) {
		DailyReport dailyReport = reportRepo.queryById(reportId);
		nap.setDay(dailyReport);
		return napRepo.saveAndFlush(nap);
		}
		return null;
	}

	@Override
	public boolean delete(int napId) {
		Nap nap = napRepo.queryById(napId);
		if(nap != null) {
			napRepo.delete(nap);
		}
		return !napRepo.existsById(napId);
	}

	@Override
	public Nap update(Nap nap) {
		Nap update = napRepo.queryById(nap.getId());
		if (update!=null) {
			update.setDay(nap.getDay());
			update.setTimeFinish(nap.getTimeFinish());
			update.setTimeStart(nap.getTimeStart());
			return napRepo.saveAndFlush(update);
		}
		return null;
	}
}
