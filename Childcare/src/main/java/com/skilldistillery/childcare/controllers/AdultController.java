package com.skilldistillery.childcare.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.Adult;
import com.skilldistillery.childcare.entities.User;
import com.skilldistillery.childcare.services.AdultService;

@RestController
@RequestMapping("api/")
@CrossOrigin({"*", "http://localhost/"})
public class AdultController {
	
	@Autowired
	private AdultService adultService;
	
	@GetMapping("adults")
	public List<Adult> listAdults(Principal principal){
		return null;
		
	}
	
	@PostMapping("adults")
	public Adult create(@RequestBody Adult adult, HttpServletResponse res, HttpServletRequest req) {
		try {
			adult = adultService.create(adult);
			res.setStatus(201);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			adult = null;
		}
		
		return adult;
	}

	@GetMapping("adults/loggedInAdult")
	public Adult show(Principal principal, HttpSession session, HttpServletResponse res) {
		User user = (User) session.getAttribute("loggedInUser");
		try {
			return adultService.showAdultByUsername(principal.getName());
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return null;
	}
}
