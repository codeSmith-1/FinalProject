package com.skilldistillery.childcare.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.Nap;
import com.skilldistillery.childcare.services.NapService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class NapController {
	
	@Autowired
	private NapService napServ;
	
	@GetMapping("naps/{reportId}")
	public Nap napByReport(int reportId, HttpServletRequest req, HttpServletResponse res) {
		return napServ.napByReportId(reportId);
	}
	
	@PostMapping("naps")
	public Nap create(HttpServletRequest req, HttpServletResponse res, @RequestBody Nap nap) {
		return napServ.create(nap);
	}

}
