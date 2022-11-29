package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Kid;
import com.skilldistillery.childcare.entities.User;
import com.skilldistillery.childcare.repositories.KidRepository;
import com.skilldistillery.childcare.repositories.UserRepository;

@Service
public class KidServiceImpl implements KidService {

	@Autowired
	private KidRepository kidRepo;
	@Autowired
	private UserRepository userRepo;

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
		if (user.getRole().equals("staff")) {
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
	public Kid update(String username, int kidId, Kid kid) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			Kid kidToUpdate = kidRepo.queryById(kidId);
			kidToUpdate.setFirstName(kid.getFirstName());
			kidToUpdate.setLastName(kid.getLastName());
			kidToUpdate.setBirthday(kid.getBirthday());
			kidToUpdate.setClassroom(kid.getClassroom());
			kidToUpdate.setImageUrl(kid.getImageUrl());
			kidRepo.saveAndFlush(kidToUpdate);
			return kidToUpdate;
		} else {
			return null;
		}
	}

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
	
	

}
