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

import com.skilldistillery.childcare.entities.DailyReport;
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
	public List<Kid> listAllKids(Principal principal, HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
			return null;
		}
		User user = userRepo.findByUsername(principal.getName());
		if (user.getRole().equals("staff")) {
			return kidServ.listAllKids(principal.getName());
		}
		res.setStatus(404);
		return null;
	}

	@PostMapping("kids/relationship/{relationship}")
	public Kid create(@RequestBody Kid kid, Principal principal, @PathVariable String relationship, HttpServletResponse res, HttpServletRequest req) {
		try {
			kid = kidServ.create(principal.getName(), kid, relationship);
			res.setStatus(201);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			kid = null;
		}

		return kid;
	}

	@PutMapping("kids")
	public Kid update(Principal principal, @RequestBody Kid kid, HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
			return null;
		}
		kid = kidServ.update(principal.getName(), kid);
		res.setStatus(200);
		return kid;
	}
	
	@GetMapping("kids/reports/kidId/{kidId}")
	public List<DailyReport> indexReports(Principal principal, @PathVariable int kidId, HttpServletResponse res){
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
			return null;
		}
		List<DailyReport> reports = kidServ.findReportsByKidId(kidId);
		if (reports==null) {
			res.setStatus(400);
			return null;
		}else {
			return reports;
		}
	}

}
