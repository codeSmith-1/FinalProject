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

public class StaffTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Staff s;
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
	s = em.find(Staff.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		s = null;
	}

	@Test
	void test_staff_mapping() {
		assertNotNull(s);
		assertEquals("Rob", s.getFirstName());
	}
	
	@Test
	void test_staff_classroom_mapping() {
		assertNotNull(s);
		assertEquals("Fancy Sunshine", s.getClassroom().getRoomName());
	}
	
	@Test
	void test_staff_to_user_mapping() {
		assertNotNull(s);
		assertEquals("admin", s.getUser().getUsername());
	}
}
