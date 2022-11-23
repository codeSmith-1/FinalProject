package com.skilldistillery.childcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.Mood;

public interface MoodRepository extends JpaRepository<Mood, Integer> {

}
