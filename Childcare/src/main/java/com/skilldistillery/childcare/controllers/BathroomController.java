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
	
	@GetMapping("bathrooms/{reportId}")
	public List<Bathroom> listMoodByReport(@PathVariable ("reportId") int reportId){
		List<Bathroom> bathrooms = bathroomServ.bathroomsByReportId(reportId);
		return bathrooms;
	}

}
