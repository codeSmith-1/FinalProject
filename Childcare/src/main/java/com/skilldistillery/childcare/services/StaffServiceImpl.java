package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Staff;
import com.skilldistillery.childcare.entities.User;
import com.skilldistillery.childcare.repositories.StaffRepository;
import com.skilldistillery.childcare.repositories.UserRepository;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffRepo;
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Staff> listAllStaff() {
		List<Staff> employees = staffRepo.findAll();
		if (employees.size() > 0) {
			return employees;
		} else {
			return null;
		}
	}

	@Override
	public Staff showStaffByUsername(String username) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			return staffRepo.findByUser_Username(username);
		} else {
			return null;
		}
	}

	@Override
	public Staff create(Staff staff) {
		User user = userRepo.findByUsername(staff.getUser().getUsername());
		System.err.println(staff.getUser().getUsername());
		if (user.getRole().equals("staff")) {
			if (staff != null) {
				staff.setUser(user);
				staffRepo.saveAndFlush(staff);
			}
			return staff;
		} else {
			return null;
		}
	}

	@Override
	public Staff update(String username, Staff staff) {
		User user = userRepo.findByUsername(username);
		Staff staffToUpdate = staffRepo.findByUser_Username(username);
		if (user.getRole().equals("staff")) {
			staffToUpdate.setFirstName(staff.getFirstName());
			staffToUpdate.setLastName(staff.getLastName());
			staffToUpdate.setClassroom(staff.getClassroom());
			staffToUpdate.setUser(staff.getUser());
			staffToUpdate.setImageUrl(staff.getImageUrl());
			staffRepo.saveAndFlush(staffToUpdate);
			return staffToUpdate;
		} else {
			return null;
		}
	}

	@Override
	public boolean delete(String username, int staffId) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			Staff staffToDelete = staffRepo.queryById(staffId);
			if (staffToDelete != null) {
				staffRepo.delete(staffToDelete);
				return true;
			}
		}
		return false;
	}

}
