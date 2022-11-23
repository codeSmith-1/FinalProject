package com.skilldistillery.childcare.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GuardianTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Guardian guardian;
	
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
		GuardianId gid = new GuardianId();
//		gid.setAdultId(1);
//		gid.setKidId(2);
		guardian = em.find(Guardian.class, new GuardianId(2, 1));
	}
	
	@AfterEach
	void tearDown() {
		em.close();
		guardian = null;
	}
	
	@Test
	void test() {
		assertNotNull("Mother", guardian.getRelationship());
	}
	
}

