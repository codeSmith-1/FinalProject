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

class BathroomTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Bathroom br;
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
	br = em.find(Bathroom.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		br = null;
	}

//	@Test
//	void test() {
//		assertNotNull(br);
//		assertEquals("2022-11-20 09:17:48", br.getBathroomTime());
//	}

	@Test
	void test_bathroom_to_type_mapping() {
		assertNotNull(br);
		assertEquals("Dry", br.getType().getStatus());
	}
	
	@Test
	void test_bathroom_to_staff_mapping() {
		assertNotNull(br);
		assertEquals("William", br.getStaff().getFirstName());
	}
	
	@Test
	void test_bathroom_to_daily_report_mapping() {
		assertNotNull(br);
		assertEquals(1, br.getDay().getId());
	}
	

}
