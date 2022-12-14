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

public class ReportImageTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private ReportImage ri;
	
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
	ri = em.find(ReportImage.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		ri = null;
	}

	@Test
	void test() {
		assertNotNull(ri);
		assertEquals("https://cloudfront-us-east-1.images.arcpublishing.com/coindesk/WMXJCFJ3ERCETA6TJNZ5NQPNKA.webp", ri.getImageUrl());
	}
	
	@Test
	void test_join_mappingsDailyReport() {
		assertNotNull(ri);
		assertEquals(1, ri.getDailyReport().getId());
	}
	
	@Test
	void test_join_mappingsStaff() {
		assertNotNull(ri);
		assertEquals(2, ri.getStaff().getId());
	}

}
