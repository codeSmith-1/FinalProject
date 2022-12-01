package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Message;

public interface MessageService {
	
	List<Message> listAllMessages(String username);
	
	Message show(int messageId);
	
	Message create(Message message, String username);
	
//	Message update(Message message);
	
	boolean delete(int messageId);
	
	List<Message> index(String username);
}
