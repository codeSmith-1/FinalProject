package com.skilldistillery.childcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.Bathroom;

public interface BathroomRepository extends JpaRepository<Bathroom, Integer> {
	Bathroom queryById(int id);
}
