package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.MoodEntry;
import com.skilldistillery.childcare.repositories.MoodRepository;

@Service
public class MoodServiceImpl implements MoodService {
	
	@Autowired
	public MoodRepository moodRepo;

	@Override
	public List<MoodEntry> moodByReportId(int reportId) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Override
//	public List<MoodEntry> moodByReportId(int reportId) {
//		return moodRepo.findByMoodEntries_DailyReportId(reportId);
//	}

}
