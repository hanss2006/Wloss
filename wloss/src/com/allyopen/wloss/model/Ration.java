package com.allyopen.wloss.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the ration database table.
 * 
 */
@Entity
@Table(name = "ration")
@NamedQuery(name = "Ration.findAll", query = "SELECT r FROM Ration r")
public class Ration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	private int amount;

	@Temporal(TemporalType.DATE)
	@Column(name = "eaten_date")
	private Date eatenDate;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Ration() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getEatenDate() {
		return this.eatenDate;
	}

	public void setEatenDate(Date eatenDate) {
		this.eatenDate = eatenDate;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Ration) {
			if (((Ration) obj).getId() == this.getId() && ((Ration) obj).getUser().equals(this.getUser())
					&& ((Ration) obj).getProduct() == this.getProduct()
					&& ((Ration) obj).getAmount() == this.getAmount()
					&& ((Ration) obj).getEatenDate() == this.getEatenDate()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}