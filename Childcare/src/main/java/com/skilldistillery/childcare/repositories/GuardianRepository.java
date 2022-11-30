package com.skilldistillery.childcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.Guardian;

public interface GuardianRepository extends JpaRepository<Guardian, Integer>{
	

}
