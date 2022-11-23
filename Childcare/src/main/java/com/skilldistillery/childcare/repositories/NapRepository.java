package com.skilldistillery.childcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.Nap;

public interface NapRepository extends JpaRepository<Nap, Integer> {
	Nap queryById(int id);
}
