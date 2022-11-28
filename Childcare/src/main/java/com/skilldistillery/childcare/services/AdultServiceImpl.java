package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Adult;
import com.skilldistillery.childcare.entities.User;
import com.skilldistillery.childcare.repositories.AdultRepository;
import com.skilldistillery.childcare.repositories.UserRepository;

@Service
public class AdultServiceImpl implements AdultService {

	@Autowired
	public AdultRepository adultRepo;

	@Autowired
	public UserRepository userRepo;

	@Override
	public List<Adult> listAllAdults(String username) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			return adultRepo.findAll();
		} else {
			return null;
		}
	}

	@Override
	public Adult showAdultById(String username, int id) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			return adultRepo.queryById(id);
		} else {
			return null;
		}
	}

	@Override
	public Adult create(Adult adult) {
			if (adult != null) {
				System.err.println(adult.getUser().getUsername());
				adultRepo.saveAndFlush(adult);
			} else {
				adult = null;
			}
			return adult;
	}

	@Override
	public Adult update(String username, int adultId, Adult adult) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			Adult adultToUpdate = adultRepo.queryById(adultId);
			adultToUpdate.setFirstName(adult.getFirstName());
			adultToUpdate.setLastName(adult.getLastName());
			adultToUpdate.setAddress(adult.getAddress());
			adultToUpdate.setPhoneNumber(adult.getPhoneNumber());
			adultToUpdate.setImageUrl(adult.getImageUrl());
			adultToUpdate.setEmergencyContact(adult.isEmergencyContact());
			adultRepo.saveAndFlush(adultToUpdate);
			return adultToUpdate;
		} else {
			return null;
		}
	}

	@Override
	public boolean delete(String username, int adultId) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			Adult adultToDelete = adultRepo.queryById(adultId);
			if (adultToDelete != null) {
				adultRepo.delete(adultToDelete);
				return true;
			}
		}
		return false;
	}
}
