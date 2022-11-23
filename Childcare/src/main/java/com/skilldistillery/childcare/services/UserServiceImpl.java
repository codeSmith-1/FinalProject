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
		public List<User> listAllUsers() {
			return userRepo.findAll();
		}

		@Override
		public User showUserById(int id) {
			return userRepo.queryById(id);
		}

		@Override
		public User create(User user) {
			if(user != null) {
				userRepo.saveAndFlush(user);
			}
			return user;
		}

		@Override
		public User update(int userId, User user) {
			User userToUpdate = userRepo.queryById(userId);
			userToUpdate.setUsername(user.getUsername());
			userToUpdate.setPassword(user.getPassword());
			userToUpdate.setRole(user.getRole());
			userToUpdate.setEnabled(user.getEnabled());
			userRepo.saveAndFlush(userToUpdate);
			return userToUpdate;
		}

		@Override
		public boolean delete(int userId) {
			User userToDelete = userRepo.queryById(userId);
			if(userToDelete != null) {
				userRepo.delete(userToDelete);
				return true;
			}
			return false;
		}
}
