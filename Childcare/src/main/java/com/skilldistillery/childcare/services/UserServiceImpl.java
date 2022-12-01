package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.User;
import com.skilldistillery.childcare.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> index(String username) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			return userRepo.findAll();
		} else {
			return null;
		}
	}
	
	

	@Override
	public User showUserById(String username, int id) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			return userRepo.queryById(id);
		} else {
			return null;
		}
	}

	@Override
	public User create(String username, User user) {
		User userCheck = userRepo.findByUsername(username);
		if (userCheck.getRole().equals("staff")) {
			if (user != null) {
				userRepo.saveAndFlush(user);
			}
			return user;
		} else {
			return null;
		}
	}

	@Override
	public User update(String username, int userId, User user) {
		User userCheck = userRepo.findByUsername(username);
		if (userCheck.getRole().equals("staff")) {
			User userToUpdate = userRepo.queryById(userId);
			userToUpdate.setUsername(user.getUsername());
			userToUpdate.setPassword(user.getPassword());
			userToUpdate.setRole(user.getRole());
			userToUpdate.setEnabled(user.getEnabled());
			userRepo.saveAndFlush(userToUpdate);
			return userToUpdate;
		} else {
			return null;
		}
	}

	@Override
	public boolean delete(String username, int userId) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			User userToDelete = userRepo.queryById(userId);
			if (userToDelete != null) {
				userRepo.delete(userToDelete);
				return true;
			}
		}
		return false;
	}



	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
}
