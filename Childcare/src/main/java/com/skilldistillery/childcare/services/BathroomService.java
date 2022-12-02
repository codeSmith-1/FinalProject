package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Bathroom;
import com.skilldistillery.childcare.entities.BathroomType;

public interface BathroomService {
	List<Bathroom> listAllBathrooms();
	
	Bathroom create(String username, Bathroom bathroom);
	
	Bathroom update(String username, Bathroom bathroom, int bathroomId);
	
	boolean delete(int bathroomId);

	List<Bathroom> bathroomsByReportId(int reportId);

	List<BathroomType> listAllBathroomTypes();

}
