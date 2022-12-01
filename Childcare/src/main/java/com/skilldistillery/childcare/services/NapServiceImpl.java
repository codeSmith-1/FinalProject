package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Nap;
import com.skilldistillery.childcare.repositories.NapRepository;

@Service
public class NapServiceImpl implements NapService {
	
	@Autowired
	public NapRepository napRepo;

	@Override
	public Nap napByReportId(int reportId) {
		Nap nap = napRepo.findByDayId(reportId);
		return nap;
	}

	@Override
	public Nap create(Nap nap) {
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
