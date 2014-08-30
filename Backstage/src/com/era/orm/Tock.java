package com.era.orm;

/**
 * Tock entity. @author MyEclipse Persistence Tools
 */

public class Tock implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tockId;
	private String dim;
	private String dog;
	private String city;

	// Constructors

	/** default constructor */
	public Tock() {
	}

	/** full constructor */
	public Tock(String tockId, String dim, String dog, String city) {
		this.tockId = tockId;
		this.dim = dim;
		this.dog = dog;
		this.city = city;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTockId() {
		return this.tockId;
	}

	public void setTockId(String tockId) {
		this.tockId = tockId;
	}

	public String getDim() {
		return this.dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}

	public String getDog() {
		return this.dog;
	}

	public void setDog(String dog) {
		this.dog = dog;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}