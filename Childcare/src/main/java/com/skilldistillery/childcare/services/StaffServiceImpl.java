package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Staff;
import com.skilldistillery.childcare.repositories.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	private StaffRepository staffRepo;

	@Override
	public List<Staff> listAllStaff() {
		return staffRepo.findAll();
	}

	@Override
	public Staff showStaffById(int id) {
		return staffRepo.queryById(id);
	}

	@Override
	public Staff create(Staff staff) {
		if(staff != null) {
			staffRepo.saveAndFlush(staff);
		}
		return staff;
	}

	@Override
	public Staff update(int staffId, Staff staff) {
		Staff staffToUpdate = staffRepo.queryById(staffId);
		staffToUpdate.setFirstName(staff.getFirstName());
		staffToUpdate.setLastName(staff.getLastName());
		staffToUpdate.setClassroom(staff.getClassroom());
		staffToUpdate.setUser(staff.getUser());
		staffToUpdate.setImageUrl(staff.getImageUrl());
		staffRepo.saveAndFlush(staffToUpdate);
		return null;
	}

	@Override
	public boolean delete(int staffId) {
		Staff staffToDelete = staffRepo.queryById(staffId);
		if(staffToDelete != null) {
			staffRepo.delete(staffToDelete);
			return true;
		}
		return false;
	}
}
