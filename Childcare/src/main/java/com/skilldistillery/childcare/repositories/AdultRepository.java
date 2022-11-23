package com.skilldistillery.childcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.Adult;

public interface AdultRepository extends JpaRepository<Adult, Integer> {
	public Adult queryById(int id);
}
