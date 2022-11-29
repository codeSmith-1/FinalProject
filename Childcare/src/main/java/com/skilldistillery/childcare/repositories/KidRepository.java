package com.skilldistillery.childcare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.Kid;

public interface KidRepository extends JpaRepository<Kid, Integer> {
	public Kid queryById(int id);
	public List<Kid> findByClassroom_roomName(String roomName);
	public List<Kid> findByGuardians_AdultId(int id);
}
