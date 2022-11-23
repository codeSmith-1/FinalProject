package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Kid;

public interface KidService {
	
	List<Kid> listAllExercises();

	Kid showExercise(int id);

	Kid create(Kid kid);

	Kid update(int kidId, Kid kid);

	boolean delete(int kidId);
}
