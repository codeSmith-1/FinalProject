package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.User;

public interface UserService {
	
	List<User> index(String username);
	
	User showUserById(String username, int id);

	User create(String username, User user);

	User update(String username, int userId, User user);

	boolean delete(String username, int userId);
}
