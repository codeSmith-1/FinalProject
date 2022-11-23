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

public class DailyReportTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private DailyReport dr;
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
	dr = em.find(DailyReport.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		dr = null;
	}

	@Test
	void test() {
		assertNotNull(dr);
		assertEquals("Circle Time, Arts & Crafts, Outside Time", dr.getActivities());
	}
	
	@Test
	void mapping_moodEntry() {
		assertEquals(1, dr.getMoodEntries().size());
	}

}
