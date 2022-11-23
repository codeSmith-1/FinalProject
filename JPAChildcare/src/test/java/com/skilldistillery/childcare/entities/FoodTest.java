package com.skilldistillery.childcare.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FoodTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Food food;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAChildcare");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		food = em.find(Food.class, 1);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		em.close();
		food = null;
	}
	
	@Test
	void test() {
		assertNotNull(food);
		assertEquals(" ", food.getLunch());
	}

	
}
