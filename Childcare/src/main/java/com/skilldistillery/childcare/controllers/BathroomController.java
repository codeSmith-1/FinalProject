package com.skilldistillery.childcare.controllers;

import java.security.Principal;
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
import com.skilldistillery.childcare.entities.BathroomType;
import com.skilldistillery.childcare.services.BathroomService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class BathroomController {

	@Autowired
	private BathroomService bathroomServ;

	@GetMapping("bathroomTypes")
	public List<BathroomType> listAll(Principal principal, HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(400);
			return null;
		}
		return bathroomServ.listAllBathroomTypes();
	}

	@PostMapping("bathrooms")
	public Bathroom create(@RequestBody Bathroom bathroom, Principal principal, String username,
			HttpServletResponse res, HttpServletRequest req) {
		if (principal.getName().isEmpty()) {
			res.setStatus(400);
			return null;
		}
		bathroom = bathroomServ.create(username, bathroom);
		res.setStatus(201);
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

	@GetMapping("bathrooms/{reportId}")
	public List<Bathroom> listBathroomsByReportId(@PathVariable int reportId, Principal principal, HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(400);
			return null;
		}
		List<Bathroom> bathrooms = bathroomServ.bathroomsByReportId(reportId);
		if (bathrooms.isEmpty()) {
			res.setStatus(404);
		}
		res.setStatus(200);
		return bathrooms;
	}

}
