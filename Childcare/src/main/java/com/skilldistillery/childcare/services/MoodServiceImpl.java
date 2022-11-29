package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Mood;
import com.skilldistillery.childcare.repositories.MoodRepository;

@Service
public class MoodServiceImpl implements MoodService {
	
	@Autowired
	public MoodRepository moodRepo;
	
	@Override
	public List<Mood> moodByReportId(int reportId) {
		return moodRepo.findByDailyReportId(reportId);
	}

}
