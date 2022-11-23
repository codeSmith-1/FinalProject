package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Adult;

public interface AdultService {

	List<Adult> listAllAdults(String username);

	Adult showAdultById(String username, int id);

	Adult create(String username, Adult adult);

	Adult update(String username, int adultId, Adult adult);

	boolean delete(String username, int adultId);
}
