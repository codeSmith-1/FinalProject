package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.DailyReport;
import com.skilldistillery.childcare.entities.Kid;

public interface KidService {
	
	List<Kid> listAllKids(String username);

	Kid showKid(String username, int id);

	Kid create(String username, Kid kid, String relationship);

	Kid update(String username, Kid kid);

	boolean delete(String username, int kidId);
	
	List<Kid> listByClassroom(String roomName);
	
	List<Kid> findKidsByAdultId(String username);
	
	List<DailyReport> findReportsByKidId(int id);
}
