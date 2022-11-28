package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Classroom;

public interface ClassroomService {
	
	public List<Classroom> index(String username);
	
	public Classroom create(String roomName);
	
	// update?

}
