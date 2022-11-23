package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Kid;

public interface KidService {
	
	List<Kid> listAllKids(String username);

	Kid showKid(String username, int id);

	Kid create(String username, Kid kid);

	Kid update(String username, int kidId, Kid kid);

	boolean delete(String username, int kidId);
}
