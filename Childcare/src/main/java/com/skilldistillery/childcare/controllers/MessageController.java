package com.skilldistillery.childcare.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.Message;
import com.skilldistillery.childcare.services.MessageService;

@RestController
@RequestMapping("api/")
@CrossOrigin({ "*", "http://localhost" })
public class MessageController {

	@Autowired
	private MessageService messageSvc;
	
	@PostMapping("messages")
	public Message createMessage(@RequestBody Message message, HttpServletResponse res, HttpServletRequest req, Principal principal) {
		try {
			message = messageSvc.create(principal.getName(), message);
			res.setStatus(201);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return message;
	}
	
	@GetMapping("messages/{messageId}")
	public Message show(Principal principal, HttpSession session, HttpServletResponse res, @PathVariable Integer messageId) {
		try {
			return messageSvc.show(messageId);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("messages")
	public List<Message> index(Principal principal, HttpSession session, HttpServletResponse res){
		return messageSvc.index(principal.getName());
	}
	
	
	@DeleteMapping("messages/{messageId}")
	public void destroy(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable Integer messageId) {
		try {
			if(messageSvc.delete(messageId)) {
				res.setStatus(204);
			}
			else {
				res.setStatus(400);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	

}
