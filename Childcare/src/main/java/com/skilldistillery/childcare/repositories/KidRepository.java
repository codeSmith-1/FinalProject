package com.skilldistillery.childcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.Kid;

public interface KidRepository extends JpaRepository<Kid, Integer> {
	public Kid queryById(int id);
}
