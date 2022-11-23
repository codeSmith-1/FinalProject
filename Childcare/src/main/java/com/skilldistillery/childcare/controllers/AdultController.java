package com.skilldistillery.childcare.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.Adult;
import com.skilldistillery.childcare.services.AdultService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class AdultController {
	
	@Autowired
	private AdultService adultService;
	
	@GetMapping("adults")
	public List<Adult> listAdults(Principal principal){
		return null;
		
	}

}
