package com.skilldistillery.childcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.User;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordEncoder encoder;

//	@Autowired
//	private UserRepository userRepo;

	@Override
	public User register(User user) {
//		String encoded = encoder.encode(user.getPassword());
//		user.setPassword(encoded);
//		user.setEnabled(true);
//		user.setRole("standard");
//		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		return null;
//		return userRepo.findByUsername(username);
	}

}
