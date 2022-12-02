package com.skilldistillery.childcare.services;

import java.util.List;

import com.skilldistillery.childcare.entities.Food;
import com.skilldistillery.childcare.entities.FoodType;

public interface FoodService {
 List<Food> listAllFoods(String username);
 
 
 Food update(String username, Food food, int foodId);
 
 boolean delete(int foodId);
 
 List<Food> foodByReportId(int reportId);

List<FoodType> getFoodTypes();

Food create(String username, Food food, int reportId);
}
