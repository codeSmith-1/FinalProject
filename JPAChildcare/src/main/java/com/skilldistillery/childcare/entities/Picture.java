package com.skilldistillery.childcare.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Picture {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
}
