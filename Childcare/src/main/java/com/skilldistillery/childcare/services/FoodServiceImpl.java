package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Food;
import com.skilldistillery.childcare.entities.User;
import com.skilldistillery.childcare.repositories.FoodRepository;
import com.skilldistillery.childcare.repositories.UserRepository;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<Food> listAllFoods(String username) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			return foodRepo.findAll();
		} else {
			return null;
		}
	}

	@Override
	public Food create(String username, Food food) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			if (food != null) {
				foodRepo.saveAndFlush(food);
			}
			return food;
		} else {
			return null;
		}
	}

	@Override
	public Food update(String username, Food food, int foodId) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			Food foodToUpdate = foodRepo.queryById(foodId);
			foodToUpdate.setAmSnack(food.getAmSnack());
			foodToUpdate.setLunch(food.getLunch());
			foodToUpdate.setPmSnack(food.getPmSnack());
			foodRepo.saveAndFlush(foodToUpdate);
			return foodToUpdate;
		}else {
			return null;
		}
	}

	@Override
	public boolean delete(String username, int foodId) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			Food foodToDelete = foodRepo.queryById(foodId);
			if (foodToDelete != null) {
				foodRepo.delete(foodToDelete);
				return true;
			}
		}
		return false;
	}
	
	

}
