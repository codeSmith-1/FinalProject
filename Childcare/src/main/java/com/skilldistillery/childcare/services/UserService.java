package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.User;

public interface UserService {
	
	List<User> listAllUsers();

	User showUserById(int id);

	User create(User user);

	User update(int userId, User user);

	boolean delete(int userId);
}
