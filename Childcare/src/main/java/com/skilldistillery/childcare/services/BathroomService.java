package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Bathroom;

public interface BathroomService {
	List<Bathroom> listAllBathrooms(String username);
	
	Bathroom create(String username, Bathroom bathroom);
	
	Bathroom update(String username, Bathroom bathroom, int bathroomId);
	
	boolean delete(String username, int bathroomId);

}
