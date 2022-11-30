package com.skilldistillery.childcare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.Bathroom;
import com.skilldistillery.childcare.entities.ReportImage;

public interface BathroomRepository extends JpaRepository<Bathroom, Integer> {
	Bathroom queryById(int id);
	
	List<Bathroom> findByDayId(int reportId);
}
