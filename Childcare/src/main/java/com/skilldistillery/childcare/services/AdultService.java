package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Adult;
import com.skilldistillery.childcare.entities.User;

public interface AdultService {

	List<Adult> listAllAdults(String username);

	Adult showAdultById(String username, int id);

	Adult create(Adult adult);

	Adult update(String username, Adult adult);

	boolean delete(String username, int adultId);
	
	Adult showAdultByUserId(int id);
	
	Adult showAdultByUsername(String username);
	
	Adult editEnableSpecific(String username, Adult adult, int adultId);

	User showUserByUsername(String username);
	
	
}
