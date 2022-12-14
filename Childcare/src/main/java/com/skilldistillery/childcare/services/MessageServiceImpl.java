package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Message;
import com.skilldistillery.childcare.entities.User;
import com.skilldistillery.childcare.repositories.MessageRepository;
import com.skilldistillery.childcare.repositories.UserRepository;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageRepository messageRepo;
	
	@Autowired 
	private UserRepository userRepo;

	@Override
	public List<Message> listAllMessages(String username) {
		return messageRepo.getByRecipientUsername(username);
	}
	
	@Override
	public Message create(Message message, String username) {
		User user = userRepo.findByUsername(username);
		User recipient = userRepo.findByUsername(message.getRecipient().getUsername());
		if (user != null) {
			message.setSender(user);
			message.setRecipient(recipient);
			return message = messageRepo.saveAndFlush(message);
		}
		return null;
	}

//	@Override
//	public Message update(Message message) {
//		Message messageToUpdate = messageRepo.queryById(message.getId());
//		
//		return null;
//	}

	@Override
	public boolean delete(int messageId) {
		Message messageToDelete = messageRepo.queryById(messageId);
		if(messageToDelete != null) {
			messageRepo.delete(messageToDelete);
		}
		return true;
	}

	@Override
	public Message show(int messageId) {
		return messageRepo.queryById(messageId);
	}

	@Override
	public List<Message> index(String username) {
		return messageRepo.getByRecipientUsername(username);
	}

}
