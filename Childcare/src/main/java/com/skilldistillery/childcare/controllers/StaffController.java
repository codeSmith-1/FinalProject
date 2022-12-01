package com.skilldistillery.childcare.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		try {
			staff = staffSvc.create(staff);
			res.setStatus(201);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			staff = null;
		}
		return staff;
	}
	
	@GetMapping("staff/loggedInStaff")
	public Staff show(Principal principal, HttpSession session, HttpServletResponse res) {
		try {
			return staffSvc.showStaffByUsername(principal.getName());
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return null;
	}
	
	@PutMapping("staff")
	public Staff update(@RequestBody Staff staff, Principal principal, HttpServletResponse res) {
		try {
			return staffSvc.update(principal.getName(), staff);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return null;
	}
	
	
	
	@GetMapping("staff")
	public List<Staff> showAll(Principal principal, HttpServletResponse res){
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
			return null;
		}
		return staffSvc.listAllStaff();
	}
}
