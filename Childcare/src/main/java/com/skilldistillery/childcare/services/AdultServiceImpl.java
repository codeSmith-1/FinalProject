package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Adult;
import com.skilldistillery.childcare.repositories.AdultRepository;

@Service
public class AdultServiceImpl implements AdultService {
	
	@Autowired
	public AdultRepository adultRepo;

	@Override
	public List<Adult> listAllAdults() {
		return adultRepo.findAll();
	}

	@Override
	public Adult showAdultById(int id) {
		// TODO Auto-generated method stub
		return adultRepo.queryById(id);
	}

	@Override
	public Adult create(Adult adult) {
		if(adult != null) {
			adultRepo.saveAndFlush(adult);
		}
		return adult;
	}

	@Override
	public Adult update(int adultId, Adult adult) {
		Adult adultToUpdate = adultRepo.queryById(adultId);
		adultToUpdate.setFirstName(adult.getFirstName());
		adultToUpdate.setLastName(adult.getLastName());
		adultToUpdate.setAddress(adult.getAddress());
		adultToUpdate.setPhoneNumber(adult.getPhoneNumber());
		adultToUpdate.setImageUrl(adult.getImageUrl());
		adultToUpdate.setEmergencyContact(adult.isEmergencyContact());
		adultRepo.saveAndFlush(adultToUpdate);
		return adultToUpdate;
	}

	@Override
	public boolean delete(int adultId) {
		Adult adultToDelete = adultRepo.queryById(adultId);
		if(adultToDelete != null) {
			adultRepo.delete(adultToDelete);
			return true;
		}
		return false;
	}
}
