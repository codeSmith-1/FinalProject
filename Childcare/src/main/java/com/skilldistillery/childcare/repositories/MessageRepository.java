package com.skilldistillery.childcare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	Message queryById(int id);
	
	List<Message> getByRecipientUsername(String username);
	
	
	
}
