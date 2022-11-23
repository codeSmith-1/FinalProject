package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Staff;

public interface StaffService {

	List<Staff> listAllStaff();

	Staff showStaffById(int id);

	Staff create(Staff staff);

	Staff update(int staffId, Staff staff);

	boolean delete(int staffId);
}
