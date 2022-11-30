package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Bathroom;
import com.skilldistillery.childcare.entities.Food;
import com.skilldistillery.childcare.entities.User;
import com.skilldistillery.childcare.repositories.BathroomRepository;
import com.skilldistillery.childcare.repositories.UserRepository;

@Service
public class BathroomServiceImpl implements BathroomService {
	
	@Autowired
	private BathroomRepository bathroomRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Bathroom> listAllBathrooms(String username) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			return bathroomRepo.findAll();
		} else {
			return null;
		}
	}

	@Override
	public Bathroom create(String username, Bathroom bathroom) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			if (bathroom != null) {
				bathroomRepo.saveAndFlush(bathroom);
			}
			return bathroom;
		} else {
			return null;
		}
	}

	@Override
	public Bathroom update(String username, Bathroom bathroom, int bathroomId) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			Bathroom bathroomToUpdate = bathroomRepo.queryById(bathroomId);
			bathroomToUpdate.setDescription(bathroom.getDescription());
			bathroomToUpdate.setBathroomTime(bathroom.getBathroomTime());
			bathroomToUpdate.setDay(bathroom.getDay());
			bathroomToUpdate.setStaff(bathroom.getStaff());
			bathroomToUpdate.setType(bathroom.getType());
			return bathroomToUpdate;
		}else {
			return null;
		}
	}

	@Override
	public boolean delete(String username, int bathroomId) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			Bathroom bathroomToDelete = bathroomRepo.queryById(bathroomId);
			if (bathroomToDelete != null) {
				bathroomRepo.delete(bathroomToDelete);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<Bathroom> bathroomsByReportId(int reportId) {
		List<Bathroom> bathrooms = bathroomRepo.findByDayId(reportId);
		return bathrooms;
	}

}
