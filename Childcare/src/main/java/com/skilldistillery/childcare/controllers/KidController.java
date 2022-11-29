package com.skilldistillery.childcare.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.Kid;
import com.skilldistillery.childcare.services.KidService;

@RestController
@RequestMapping("api/")
@CrossOrigin({"*", "http://localhost/"})
public class KidController {
	
	@Autowired
	private KidService kidServ;
	
	@GetMapping("kids")
	public List<Kid> listAllKids(String username) {
		return kidServ.listAllKids(username);
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
	public Kid update(String username,@PathVariable int kidId,@RequestBody Kid kid, HttpServletResponse res) {
		try {
			return kidServ.update(username, kidId, kid);
		}
		catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return null;
	}

}
