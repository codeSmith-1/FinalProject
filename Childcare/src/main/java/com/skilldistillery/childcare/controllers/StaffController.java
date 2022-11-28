package com.skilldistillery.childcare.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.Classroom;
import com.skilldistillery.childcare.entities.Staff;
import com.skilldistillery.childcare.services.ClassroomService;
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
	
//	@GetMapping("staff/classrooms")
//	public List<Classroom> showAll(Principal principal){
//		return crSvc.index(principal.getName());
//	}
}
