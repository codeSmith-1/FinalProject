package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.childcare.entities.Classroom;
import com.skilldistillery.childcare.repositories.ClassroomRepository;

public class ClassroomServiceImpl implements ClassroomService {

	@Autowired
	private ClassroomRepository crRepo;

	@Override
	public List<Classroom> index(String username) {
		if (!username.isEmpty()) {
			return crRepo.findAll();
		} else {
			return null;
		}
	}

	@Override
	public Classroom create(String roomName) {
		// TODO Auto-generated method stub
		return null;
	}

}
