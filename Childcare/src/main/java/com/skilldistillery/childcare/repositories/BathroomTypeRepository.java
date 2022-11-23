package com.skilldistillery.childcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.BathroomType;

public interface BathroomTypeRepository extends JpaRepository<BathroomType, Integer> {
	BathroomType queryById(int id);
}
