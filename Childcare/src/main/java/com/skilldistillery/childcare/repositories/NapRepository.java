package com.skilldistillery.childcare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.Nap;

public interface NapRepository extends JpaRepository<Nap, Integer> {
	Nap queryById(int id);
	
	Nap findByDayId(int reportId);
}
