package com.skilldistillery.childcare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {
	
	Food queryById(int id);
	
	List<Food> findByDailyReportId(int reportId);
	
}
