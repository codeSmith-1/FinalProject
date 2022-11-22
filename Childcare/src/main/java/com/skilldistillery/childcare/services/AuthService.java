package com.skilldistillery.childcare.services;

import com.skilldistillery.childcare.entities.User;

public interface AuthService {

	public User register (User user);
	public User getUserByUsername(String username);
}
