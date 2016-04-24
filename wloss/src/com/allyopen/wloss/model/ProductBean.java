package com.allyopen.wloss.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.allyopen.wloss.service.*;

public class ProductBean {
	protected JPAResourceBean jpaResourceBean = new JPAResourceBean();

	public void deleteProduct(long productId) {
		// Create an EntityManager from the Factory stored in the
		// JPAResourceBean
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		try {
			// changes will be made so begin a transaction
			em.getTransaction().begin();
			// find the order that will be deleted. This step ensures the order
			// will be managed as the specification requires the object be
			// managed before remove can be called.
			Product product = em.find(Product.class, productId);
			// set the order to be deleted
			em.remove(product);
			// commit the transaction, this will cause the the delete SQL to be
			// sent to the database.
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public List<Product> getProducts() {
		// Create an EntityManager from the Factory stored in the
		// JPAResourceBean
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();

		try {
			// execute a JPQL query that collects summary data and stores it in
			// a
			// non-entity class Category. This query will pass the results of
			// the
			// query into the constructor of Category and return a list of
			// Category
			// objects
			Query query = em.createQuery("SELECT p FROM Product p");
			List<Product> result = query.getResultList();
			return result;
		} finally {
			em.close();
		}
	}

	public Product createNewProduct(Product product) {
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		try {
			em.getTransaction().begin();
			// calling persist on the order object will mark the object as new
			// within the persistence context.
			em.persist(product);
			// commit the transaction to have the object data inserted to the
			// database
			em.getTransaction().commit();
			return product;
		} finally {
			em.close();
		}
	}
	
	public Product safeProduct(Product product){
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(product);
			em.getTransaction().commit();
			return product;
		} finally {
			em.close();
		}
	
	}

	public Product getProductById(long productId) {
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		try {
			return em.find(Product.class, productId);
		} finally {
			em.close();
		}
	}

	public void setJpaResourceBean(JPAResourceBean jpaResourceBean) {
		this.jpaResourceBean = jpaResourceBean;
	}

	public JPAResourceBean getJpaResourceBean() {
		return jpaResourceBean;
	}
}
