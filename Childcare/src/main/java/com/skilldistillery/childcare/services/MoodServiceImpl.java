package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.DailyReport;
import com.skilldistillery.childcare.entities.Kid;
import com.skilldistillery.childcare.entities.Mood;
import com.skilldistillery.childcare.entities.MoodEntry;
import com.skilldistillery.childcare.entities.MoodId;
import com.skilldistillery.childcare.entities.User;
import com.skilldistillery.childcare.repositories.DailyReportRepository;
import com.skilldistillery.childcare.repositories.MoodEntryRepository;
import com.skilldistillery.childcare.repositories.MoodRepository;

@Service
public class MoodServiceImpl implements MoodService {
	
	@Autowired
	private MoodRepository moodRepo;
	@Autowired 
	private DailyReportRepository reportRepo;
	@Autowired
	private MoodEntryRepository moodEntryRepo;

	@Override
	public List<MoodEntry> moodByReportId(int reportId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean delete(int moodId) {
			Mood moodToDelete = moodRepo.queryById(moodId);
			if (moodToDelete != null) {
				moodRepo.delete(moodToDelete);
				return true;
			}
		return false;
	}
	
//	@Override
//	public List<MoodEntry> moodByReportId(int reportId) {
//		return moodRepo.findByMoodEntries_DailyReportId(reportId);
//	}
	
//	@Override
//	public Kid create(String username, Kid kid, String relationship) {
//		// get relationship from front end
//		System.out.println(username);
//		Adult adult = adultRepo.findByUser_Username(username);
//		
//		if (kid != null) {
////			kid.setClassroom(null);
//			System.out.println(kid.getClassroom());
//			kid = kidRepo.saveAndFlush(kid);
//
//		}
//		GuardianId guardianId = new GuardianId(adult.getId(), kid.getId());
//		Guardian guardian = new Guardian();
//		guardian.setId(guardianId);
//		guardian.setAdult(adult);
//		guardian.setKid(kid);
//		guardian.setRelationship(relationship);
//		adult.addGuardian(guardian);
//		kid.addGuardian(guardian);
//		guardRepo.saveAndFlush(guardian);
//		adult = adultRepo.saveAndFlush(adult);
//		return kid;
//	}

	@Override
	public Mood create(String username, Mood mood, int dailyReportId) {
		DailyReport dailyReport = reportRepo.queryById(dailyReportId);
		if (mood != null) {
			mood.setMoodEntries(null);
			mood = moodRepo.saveAndFlush(mood);
		}
		MoodId moodId = new MoodId(dailyReportId, mood.getId());
		MoodEntry moodEntry = new MoodEntry();
		moodEntry.setId(moodId);
		moodEntry.setDailyReport(dailyReport);
		moodEntry.setMood(mood);
		dailyReport.addMoodEntry(moodEntry);
		moodEntryRepo.saveAndFlush(moodEntry);
		return mood;
	}

	@Override
	public List<Mood> index() {
		return moodRepo.findAll();
	}

}
