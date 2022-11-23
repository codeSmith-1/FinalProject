package com.skilldistillery.childcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
	public Staff queryById(int id);
}
