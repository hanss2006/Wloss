package com.allyopen.wloss.test;

//import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.allyopen.wloss.model.Product;

import static junit.framework.Assert.assertEquals;

public class ProductTest {
    private static EntityManager em = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if (em == null){
			em = (EntityManager) Persistence.createEntityManagerFactory("wloss").createEntityManager();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if (em != null){
			    em.close();
		}
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		em.getTransaction().begin();
		Product p1 = new Product();
		p1.setColories(10);
		p1.setFat(100);
		p1.setName("carrot");
		p1.setProteins(13);
		em.persist(p1);
		em.flush();
		System.out.println("id producta" + p1.getId());
		javax.persistence.Query query = em.createQuery("Select p from Product p  where p.id = :id");
		query.setParameter("id", p1.getId());
		Product result1 = (Product) query.getSingleResult();
		assertEquals(p1.getId(), result1.getId());
		assertEquals(p1.getColories(), result1.getColories());
		assertEquals(p1.getFat(), result1.getFat());
		assertEquals(p1.getName(), result1.getName());
		assertEquals(p1.getProteins(), result1.getProteins());
		em.remove(p1);
		em.getTransaction().commit();
		
	}

}
