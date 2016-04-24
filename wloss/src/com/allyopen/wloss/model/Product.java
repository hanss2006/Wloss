package com.allyopen.wloss.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name = "product")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	private int colories;

	private int fat;

	@Column(length = 255)
	private String name;

	private int proteins;

	// bi-directional many-to-one association to Ration
	@OneToMany(mappedBy = "product")
	private List<Ration> rations;

	public Product() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getColories() {
		return this.colories;
	}

	public void setColories(int colories) {
		this.colories = colories;
	}

	public int getFat() {
		return this.fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProteins() {
		return this.proteins;
	}

	public void setProteins(int proteins) {
		this.proteins = proteins;
	}

	public List<Ration> getRations() {
		return this.rations;
	}

	public void setRations(List<Ration> rations) {
		this.rations = rations;
	}

	public Ration addRation(Ration ration) {
		getRations().add(ration);
		ration.setProduct(this);

		return ration;
	}

	public Ration removeRation(Ration ration) {
		getRations().remove(ration);
		ration.setProduct(null);

		return ration;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			if (((Product) obj).getId() == this.getId() && ((Product) obj).getName().equals(this.getName())
					&& ((Product) obj).getColories() == this.getColories()
					&& ((Product) obj).getProteins() == this.getProteins()
					&& ((Product) obj).getFat() == this.getFat()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}