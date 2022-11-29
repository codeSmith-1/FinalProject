package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Staff;

public interface StaffService {

	List<Staff> listAllStaff(String username);

	Staff showStaffByUsername(String username);

	Staff create(Staff staff);

	Staff update(String username, Staff staff);

	boolean delete(String username, int staffId);

	
	
}
