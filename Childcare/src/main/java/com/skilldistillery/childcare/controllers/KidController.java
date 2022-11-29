package com.skilldistillery.childcare.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.Kid;
import com.skilldistillery.childcare.entities.User;
import com.skilldistillery.childcare.repositories.UserRepository;
import com.skilldistillery.childcare.services.KidService;

@RestController
@RequestMapping("api/")
@CrossOrigin({ "*", "http://localhost/" })
public class KidController {

	@Autowired
	private KidService kidServ;
	@Autowired
	private UserRepository userRepo;

	@GetMapping("kids")
	public List<Kid> listAllKids(String username) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			return kidServ.listAllKids(username);
		}
		return null;
	}

	@PostMapping("kids")
	public Kid create(@RequestBody Kid kid, String username, HttpServletResponse res, HttpServletRequest req) {
		try {
			kid = kidServ.create(username, kid);
			res.setStatus(201);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			kid = null;
		}

		return kid;
	}

	@PutMapping("kids")
	public Kid update(Principal principal, String username, @RequestBody Kid kid, HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(400);
			return null;
		}
		kid = kidServ.update(username, kid);
		res.setStatus(200);
		return kid;
	}

}
