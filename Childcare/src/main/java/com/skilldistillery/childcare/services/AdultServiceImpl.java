package com.skilldistillery.childcare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Adult;
import com.skilldistillery.childcare.entities.User;
import com.skilldistillery.childcare.repositories.AddressRepository;
import com.skilldistillery.childcare.repositories.AdultRepository;
import com.skilldistillery.childcare.repositories.UserRepository;

@Service
public class AdultServiceImpl implements AdultService {

	@Autowired
	public AdultRepository adultRepo;

	@Autowired
	public UserRepository userRepo;
	
	@Autowired
	public AddressRepository addressRepo;

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
			Optional<User> opt = userRepo.findById(adult.getUser().getId());
			if (opt.isPresent()) {
				adult.setUser(opt.get());
				adult.setAddress(addressRepo.saveAndFlush(adult.getAddress()));
				adultRepo.saveAndFlush(adult);
			}
		} else {
			adult = null;
		}
		return adult;
	}

	@Override
	public Adult update(String username, Adult adult) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Adult adultToUpdate = adultRepo.findByUser_Username(username);
			adultToUpdate.setFirstName(adult.getFirstName());
			adultToUpdate.setLastName(adult.getLastName());
			// save and flush address object before assigning to adult
			adultToUpdate.setAddress(addressRepo.saveAndFlush(adult.getAddress()));
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

	@Override
	public Adult showAdultByUserId(int id) {
		return adultRepo.findByUserId(id);
	}

	@Override
	public Adult showAdultByUsername(String username) {
		return adultRepo.findByUser_Username(username);
	}

	@Override
	public Adult editEnableSpecific(Adult adult, String username, int adultId) {
		User user = userRepo.findByUsername(username);
		Adult adultToUpdate = null;
		adultToUpdate = adultRepo.queryById(adultId);
		if(adultToUpdate != null) {
			adultToUpdate.getUser().setEnabled(adult.getUser().getEnabled());
			return adultRepo.saveAndFlush(adultToUpdate);
		}
		return adultToUpdate;
	}
}
