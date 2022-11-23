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

class MoodEntryTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private MoodEntry moodEntry;
	
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
		moodEntry = em.find(MoodEntry.class, new MoodId(1,1));
	}
	
	@AfterEach
	void tearDown() throws Exception {
		em.close();
		moodEntry = null;
	}
	@Test
	void test() {
		assertEquals("2022-11-20T12:34:20", moodEntry.getEnteredAt().toString());
		
	}
	
	@Test
	void mapping_mood_entry() {
		assertEquals(1, moodEntry.getDailyReport().getId());
	}

}
