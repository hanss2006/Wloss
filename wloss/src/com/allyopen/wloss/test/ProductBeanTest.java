package com.allyopen.wloss.test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.allyopen.wloss.model.Product;
import com.allyopen.wloss.model.ProductBean;

import static junit.framework.Assert.assertNull;

public class ProductBeanTest {

	private static EntityManager em = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if (em == null) {
			em = (EntityManager) Persistence
					.createEntityManagerFactory("wloss").createEntityManager();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if (em != null) {
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
	public void DeleteProductTest() {
		em.getTransaction().begin();
		Product p1 = new Product();
		p1.setColories(10);
		p1.setFat(100);
		p1.setName("carrot");
		p1.setProteins(13);
		em.persist(p1);
		em.flush();
		Long prodId = p1.getId();
		em.getTransaction().commit();

		ProductBean pb = new ProductBean();
		pb.deleteProduct(prodId);
		Product result1 = pb.getProductById(prodId);
		assertNull(result1);

	}

	@Test
	public void getProductsTest() {
		em.getTransaction().begin();
		Product p1 = new Product();
		p1.setColories(10);
		p1.setFat(100);
		p1.setName("carrot");
		p1.setProteins(13);
		Product p2 = new Product();
		p2.setColories(10);
		p2.setFat(100);
		p2.setName("potato");
		p2.setProteins(13);

		em.persist(p1);
		em.persist(p2);
		em.flush();
		em.getTransaction().commit();

		Long prodId = p1.getId();

		Boolean isP1 = false, isP2 = false;
		ProductBean pb = new ProductBean();
		List<Product> products = (List<Product>) pb.getProducts();
		Iterator iterator = products.iterator();
		while (iterator.hasNext()) {
			Product element = (Product) iterator.next();

			if (element.equals(p1)) {
				isP1 = true;
			}

			if (element.equals(p2)) {
				isP2 = true;
			}

		}

		em.getTransaction().begin();
		em.remove(p1);
		em.remove(p2);
		em.getTransaction().commit();
		assertEquals(true, isP1);
		assertEquals(true, isP2);
	}

	@Test
	public void GetProductByIdTest() {
		em.getTransaction().begin();
		Product p1 = new Product();
		p1.setColories(10);
		p1.setFat(100);
		p1.setName("carrot");
		p1.setProteins(13);
		em.persist(p1);
		em.flush();
		em.getTransaction().commit();
		ProductBean pb = new ProductBean();
		Product product = pb.getProductById(p1.getId());

		assertEquals(p1, product);
		em.getTransaction().begin();
		em.remove(p1);
		em.getTransaction().commit();
	}

	@Test
	public void createProductTest() {
		Product p1 = new Product();
		p1.setColories(10);
		p1.setFat(100);
		p1.setName("carrot");
		p1.setProteins(13);

		ProductBean pb = new ProductBean();
		pb.createNewProduct(p1);
		assertNotNull(p1.getId());
		assertTrue(p1.getId() > 0);
		pb.deleteProduct(p1.getId());
	}
}
