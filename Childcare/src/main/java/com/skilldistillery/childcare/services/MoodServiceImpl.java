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

//	@Override
//	public boolean delete(int moodId, int reportId) {
//			Mood moodToDelete = moodRepo.queryById(moodId);
//			if (moodToDelete != null) {
//				moodRepo.delete(moodToDelete);
//				return true;
//			}
//		return false;
//	}
	@Override
	public boolean delete(int moodId, int reportId) {
		System.out.println("*****************************************************");
		System.out.println("this is moodId" + moodId);
		System.out.println("this is reportID " + reportId);
		System.out.println("*****************************************************");
		MoodId moodToDelete = new MoodId(reportId, moodId);
		moodEntryRepo.delete(moodEntryRepo.findById(moodToDelete).get());
		return moodEntryRepo.existsById(moodToDelete);
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
	public Mood create(MoodEntry moodEntry, int dailyReportId, int moodId) {
		DailyReport dailyReport = reportRepo.queryById(dailyReportId);
		Mood mood = moodRepo.queryById(moodId);
		if (mood != null) {
			mood.addMoodEntry(moodEntry);
		}
		MoodId moodEntryId = new MoodId(dailyReportId, mood.getId());
		moodEntry.setId(moodEntryId);
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
