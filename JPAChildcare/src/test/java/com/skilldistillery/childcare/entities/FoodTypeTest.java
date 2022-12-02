package com.skilldistillery.childcare.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FoodTypeTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private FoodType ft;
	
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
		ft = em.find(FoodType.class, 1);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		em.close();
		ft = null;
	}
	
//	@Test
//	void test() {
//		assertNotNull(food);
//		assertEquals("Cheez Its, Cantelope, Grapes", food.getAmSnack());
//	}
	
	@Test
	void test_ManyToOne_mapping() {
		assertNotNull(ft);
		assertEquals("Morning Snack", ft.getName());
	}
		
}
