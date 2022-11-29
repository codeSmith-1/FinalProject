package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Mood;

public interface MoodService {

	List<Mood> moodByReportId(int reportId);

}
