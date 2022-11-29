package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Adult;
import com.skilldistillery.childcare.entities.Kid;
import com.skilldistillery.childcare.entities.User;
import com.skilldistillery.childcare.repositories.AdultRepository;
import com.skilldistillery.childcare.repositories.KidRepository;
import com.skilldistillery.childcare.repositories.UserRepository;

@Service
public class KidServiceImpl implements KidService {

	@Autowired
	private KidRepository kidRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AdultRepository adultRepo;

	@Override
	public List<Kid> listAllKids(String username) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			return kidRepo.findAll();
		} else {
			return null;
		}
	}
	

	@Override
	public Kid showKid(String username, int id) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff") /*place access for guardian?*/) {
			return kidRepo.queryById(id);
		} else {
			return null;
		}
	}

	@Override
	public Kid create(String username, Kid kid) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			if (kid != null) {
				kidRepo.saveAndFlush(kid);
			}
			return kid;
		} else {
			return null;
		}
	}

	@Override
	public Kid update(String username, Kid kid) {
		User user = userRepo.findByUsername(username);
//		if (user != null) {
			Kid kidToUpdate = kidRepo.queryById(kid.getId());
			kidToUpdate.setFirstName(kid.getFirstName());
			kidToUpdate.setLastName(kid.getLastName());
			kidToUpdate.setBirthday(kid.getBirthday());
			kidToUpdate.setClassroom(kid.getClassroom());
			kidToUpdate.setImageUrl(kid.getImageUrl());
			kidRepo.saveAndFlush(kidToUpdate);
			return kidToUpdate;
//		} else {
//			return null;
		}
//	}

	@Override
	public boolean delete(String username, int kidId) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			Kid kidToDelete = kidRepo.queryById(kidId);
			if (kidToDelete != null) {
				kidRepo.delete(kidToDelete);
				return true;
			}
		}
		return false;
	}


	@Override
	public List<Kid> listByClassroom(String roomName) {
		return kidRepo.findByClassroom_roomName(roomName);
	}


	@Override
	public List<Kid> findKidsByAdultId(String username) {
		// TODO Auto-generated method stub
		Adult adult = adultRepo.findByUser_Username(username);
		List<Kid> kids = kidRepo.findByGuardians_AdultId(adult.getId());
		return kids;
	}
	
	

}
