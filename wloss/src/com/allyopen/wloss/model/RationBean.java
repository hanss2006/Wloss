package com.allyopen.wloss.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.allyopen.wloss.service.JPAResourceBean;

public class RationBean {
	protected JPAResourceBean jpaResourceBean = new JPAResourceBean();

	public void deleteRation(long rationId) {
		// Create an EntityManager from the Factory stored in the
		// JPAResourceBean
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		try {
			// changes will be made so begin a transaction
			em.getTransaction().begin();
			// find the order that will be deleted. This step ensures the order
			// will be managed as the specification requires the object be
			// managed before remove can be called.
			Ration ration = em.find(Ration.class, rationId);
			// set the order to be delet4ed
			em.remove(ration);
			// commit the transaction, this will cause the the delete SQL to be
			// sent to the database.
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

	public List<Ration> getRations() {
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
			Query query = em.createQuery("SELECT r FROM Ration r");
			List<Ration> result = query.getResultList();
			return result;
		} finally {
			em.close();
		}
	}

	public void createNewRation(Ration ration) {
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		try {
			em.getTransaction().begin();
			// calling persist on the order object will mark the object as new
			// within the persistence context.
			em.persist(ration);
			// commit the transaction to have the object data inserted to the
			// database
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

	public Ration getRationById(long rationId) {
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		try {
			return em.find(Ration.class, rationId);
		} finally {
			em.close();
		}
	}
}
