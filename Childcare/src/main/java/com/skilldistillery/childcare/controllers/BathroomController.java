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

import com.skilldistillery.childcare.entities.Bathroom;
import com.skilldistillery.childcare.services.BathroomService;

@RestController
@RequestMapping("api/")
@CrossOrigin({ "*", "http://localhost/" })
public class BathroomController {

	@Autowired
	private BathroomService bathroomServ;

	@GetMapping("bathroom")
	public List<Bathroom> listAll(String username) {
		return bathroomServ.listAllBathrooms(username);
	}

	@PostMapping("bathroom")
	public Bathroom create(@RequestBody Bathroom bathroom, String username, HttpServletResponse res,
			HttpServletRequest req) {
		try {
			bathroom = bathroomServ.create(username, bathroom);
			res.setStatus(201);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			bathroom = null;
		}
		return bathroom;
	}

	@PutMapping("bathrooms")
	public Bathroom update(String username, @PathVariable int bathroomId, @RequestBody Bathroom bathroom,
			HttpServletResponse res) {
		try {
			return bathroomServ.update(username, bathroom, bathroomId);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return null;
	}

}
