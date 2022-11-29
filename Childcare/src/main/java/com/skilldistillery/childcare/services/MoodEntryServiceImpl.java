package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.MoodEntry;
import com.skilldistillery.childcare.repositories.MoodEntryRepository;

@Service
public class MoodEntryServiceImpl implements MoodEntryService {

	@Autowired
	public MoodEntryRepository moodEntryRepo;
	
	@Override
	public List<MoodEntry> findByDailyReportId(int reportId){
		List<MoodEntry> moods = moodEntryRepo.findByDailyReportId(reportId);
		return moods;
	}
}
