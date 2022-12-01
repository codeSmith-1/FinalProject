package com.skilldistillery.childcare.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.Food;
import com.skilldistillery.childcare.services.FoodService;

@RestController
@RequestMapping("api/")
@CrossOrigin({ "*", "http://localhost/" })
public class FoodController {

	@Autowired
	private FoodService foodServ;

	@GetMapping("food")
	public List<Food> listAll(Principal principal, HttpServletResponse res, String username) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
			return null;
		}
		return foodServ.listAllFoods(username);
	}

	@PostMapping("foods")
	public Food create(@RequestBody Food food, String username, HttpServletResponse res, HttpServletRequest req, Principal principal) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
			return null;
		}
		try {
			food = foodServ.create(username, food);
			res.setStatus(201);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			food = null;
		}
		return food;
	}

	@PutMapping("foods")
	public Food update(String username, @PathVariable int foodId, @RequestBody Food food, HttpServletResponse res, Principal principal) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
			return null;
		}
		try {
			return foodServ.update(username, food, foodId);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("foods/{reportId}")
	public List<Food> foodByReportId(@PathVariable int reportId, Principal principal, HttpServletResponse res){
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
			return null;
		}
		List<Food> foods = foodServ.foodByReportId(reportId);
		if (foods.isEmpty()) {
			res.setStatus(404);
		}
		return foods;
	}
	
	@DeleteMapping("foods/foodId")
	public void deleteFood(@PathVariable int foodId, Principal principal, HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
		}
		if (foodServ.delete(foodId)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
		
	}
}
