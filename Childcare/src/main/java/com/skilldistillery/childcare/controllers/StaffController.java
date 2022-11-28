package com.skilldistillery.childcare.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.Staff;
import com.skilldistillery.childcare.services.StaffService;

@RestController
@RequestMapping("api/")
@CrossOrigin({ "*", "http://localhost" })
public class StaffController {

	@Autowired
	private StaffService staffSvc;

	@PostMapping("staff")
	public Staff registerStaff(@RequestBody Staff staff, HttpServletResponse res) {
		if (staff == null) {
			res.setStatus(400);
			return null;
		}
		try {
			staff = staffSvc.create(staff);
			res.setStatus(201);
			return staff;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
}
