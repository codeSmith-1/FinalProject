package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Staff;

public interface StaffService {

	List<Staff> listAllStaff(String username);

	Staff showStaffById(String username, int id);

	Staff create(String username, Staff staff);

	Staff update(String username, int staffId, Staff staff);

	boolean delete(String username, int staffId);
}
