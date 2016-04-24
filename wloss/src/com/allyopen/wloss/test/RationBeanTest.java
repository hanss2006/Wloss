package com.allyopen.wloss.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.allyopen.wloss.model.User;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.allyopen.wloss.model.Product;
import com.allyopen.wloss.model.ProductBean;
import com.allyopen.wloss.model.Ration;
import com.allyopen.wloss.model.RationBean;

public class RationBeanTest {
	private static EntityManager em = null;
	private static User user = null;
	private static Product product = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if (em == null) {
			em = (EntityManager) Persistence
					.createEntityManagerFactory("wloss").createEntityManager();
		}
		user = em.find(User.class, 1);
		em.getTransaction().begin();
		product = new Product();
		product.setColories(10);
		product.setFat(10);
		product.setName("carot");
		product.setProteins(10);
		em.persist(product);
		em.flush();
		em.getTransaction().commit();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		if (em != null) {
			em.getTransaction().begin();
			em.remove(product);
			em.getTransaction().commit();
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
	public void DeleteRationTest() {
		em.getTransaction().begin();
		Ration r1 = new Ration();
		r1.setUser(user);
		r1.setProduct(product);
		r1.setEatenDate(new Date());
		r1.setAmount(14);
		em.persist(r1);
		em.flush();
		em.getTransaction().commit();
		Long rationId = r1.getId();

		RationBean rb = new RationBean();
		rb.deleteRation(rationId);
		Ration result1 = rb.getRationById(rationId);
		assertNull(result1);

	}

	@Test
	public void getRationsTest() {
		em.getTransaction().begin();
		Ration r1 = new Ration();
		r1.setUser(user);
		r1.setProduct(product);
		r1.setEatenDate(new Date());
		r1.setAmount(23);
		em.persist(r1);
		Ration r2 = new Ration();
		r2.setUser(user);
		r2.setProduct(product);
		r2.setEatenDate(new Date());
		r2.setAmount(14);
		em.persist(r2);
		em.flush();
		em.getTransaction().commit();

		Boolean isP1 = false, isP2 = false;
		RationBean rb = new RationBean();
		List<Ration> rations = (List<Ration>) rb.getRations();
		Iterator iterator = rations.iterator();
		while (iterator.hasNext()) {
			Ration element = (Ration) iterator.next();

			if (element.getId().equals(r1.getId())) {
				isP1 = true;
			}

			if (element.getId().equals(r2.getId())) {
				isP2 = true;
			}
			
		}
		em.getTransaction().begin();
		em.remove(r1);
		em.remove(r2);
		em.getTransaction().commit();
		assertEquals(true, isP1);
		assertEquals(true, isP2);
	}

	@Test
	public void GetRationByIdTest() {
		em.getTransaction().begin();
		Ration r1 = new Ration();
		r1.setUser(user);
		r1.setProduct(product);
		r1.setEatenDate(new Date());
		r1.setAmount(14);
		em.persist(r1);
		em.flush();
		em.getTransaction().commit();
		RationBean rb = new RationBean();

		Ration ration = rb.getRationById(r1.getId());

		assertEquals(r1.getId(), ration.getId());
		em.getTransaction().begin();
		em.remove(r1);
		em.getTransaction().commit();
	}
	@Test
	public void createNewRationTest() {
		
		Ration r1 = new Ration();
		r1.setUser(user);
		r1.setProduct(product);
		r1.setEatenDate(new Date());
		r1.setAmount(14);
		RationBean rb = new RationBean();
		rb.createNewRation(r1);
		assertNotNull(r1.getId());
		assertTrue(r1.getId() > 0);
		rb.deleteRation(r1.getId());
		em.getTransaction().begin();
		em.remove(r1);
		em.getTransaction().commit();
	}
}
