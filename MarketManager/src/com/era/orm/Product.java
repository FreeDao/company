package com.era.orm;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer seller;
	private String name;
	private Integer imagesId;

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** full constructor */
	public Product(Integer seller, String name, Integer imagesId) {
		this.seller = seller;
		this.name = name;
		this.imagesId = imagesId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSeller() {
		return this.seller;
	}

	public void setSeller(Integer seller) {
		this.seller = seller;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getImagesId() {
		return this.imagesId;
	}

	public void setImagesId(Integer imagesId) {
		this.imagesId = imagesId;
	}

}