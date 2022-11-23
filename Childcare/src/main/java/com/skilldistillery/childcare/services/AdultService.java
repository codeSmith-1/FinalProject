package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Adult;

public interface AdultService {

	List<Adult> listAllAdults();

	Adult showAdultById(int id);

	Adult create(Adult adult);

	Adult update(int adultId, Adult adult);

	boolean delete(int adultId);
}
