package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Kid;
import com.skilldistillery.childcare.repositories.KidRepository;

@Service
public class KidServiceImpl implements KidService {

	@Autowired
	private KidRepository kidRepo;

	@Override
	public List<Kid> listAllExercises() {
		return kidRepo.findAll();
	}

	@Override
	public Kid showExercise(int id) {
		return kidRepo.queryById(id);
	}

	@Override
	public Kid create(Kid kid) {
		if(kid != null) {
			kidRepo.saveAndFlush(kid);
		}
		return kid;
	}

	@Override
	public Kid update(int kidId, Kid kid) {
		Kid kidToUpdate = kidRepo.queryById(kidId);
		kidToUpdate.setFirstName(kid.getFirstName());
		kidToUpdate.setLastName(kid.getLastName());
		kidToUpdate.setBirthday(kid.getBirthday());
		kidToUpdate.setClassroom(kid.getClassroom());
		kidToUpdate.setImageUrl(kid.getImageUrl());
		kidRepo.saveAndFlush(kidToUpdate);
		return kidToUpdate;
	}

	@Override
	public boolean delete(int kidId) {
		Kid kidToDelete = kidRepo.queryById(kidId);
		if(kidToDelete != null) {
			kidRepo.delete(kidToDelete);
			return true;
		}
		return false;
	}
	
}
