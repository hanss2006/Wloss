package com.allyopen.wloss.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="birth_date")
	private Date birthDate;

	private int hight;

	@Column(length=255)
	private String login;

	@Column(length=255)
	private String name;

	@Column(length=255)
	private String password;

	private int weight;

	//bi-directional many-to-one association to Ration
	@OneToMany(mappedBy="user")
	private List<Ration> rations;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getHight() {
		return this.hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public List<Ration> getRations() {
		return this.rations;
	}

	public void setRations(List<Ration> rations) {
		this.rations = rations;
	}

	public Ration addRation(Ration ration) {
		getRations().add(ration);
		ration.setUser(this);

		return ration;
	}

	public Ration removeRation(Ration ration) {
		getRations().remove(ration);
		ration.setUser(null);

		return ration;
	}

}