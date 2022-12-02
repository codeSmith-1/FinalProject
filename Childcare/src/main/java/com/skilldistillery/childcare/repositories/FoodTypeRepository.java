package com.skilldistillery.childcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.FoodType;

public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {

}
