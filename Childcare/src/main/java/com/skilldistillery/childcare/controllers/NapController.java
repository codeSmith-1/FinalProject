package com.skilldistillery.childcare.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.Bathroom;
import com.skilldistillery.childcare.entities.Nap;
import com.skilldistillery.childcare.services.NapService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class NapController {

	@Autowired
	private NapService napServ;

	@GetMapping("reports/{reportId}/naps")
	public Nap showNapByReport(@PathVariable int reportId, Principal principal, HttpServletResponse res) {
		if (principal.getName() == null) {
			res.setStatus(401);
			return null;
		}
		Nap nap = napServ.napByReportId(reportId);
		if (nap != null) {
			res.setStatus(200);
			return nap;
		} else {
			res.setStatus(400);
			return nap;
		}
	}

	@DeleteMapping("reports/naps/{napId}")
	public void deleteNap(@PathVariable int napId, Principal principal, HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
		}
		if (napServ.delete(napId)) {
			res.setStatus(204);
		} else {
			res.setStatus(400);
		}

	}

	@PostMapping("reports/{reportId}/naps")
	public Nap createNap(@PathVariable int reportId, @RequestBody Nap nap, Principal principal, HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
			return null;
		}
		Nap newNap = napServ.create(nap, reportId);
		if (newNap != null) {
			res.setStatus(201);
			return newNap;
		} else {
			res.setStatus(400);
			return null;
		}
	}

	@PutMapping("reports/naps")
	public Nap update(Principal principal, @RequestBody Nap nap, HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
			return null;
		}
		nap = napServ.update(nap);
		if (nap != null) {
			res.setStatus(200);
			return nap;
		} else {
			res.setStatus(404);
			return null;
		}
	}
}
