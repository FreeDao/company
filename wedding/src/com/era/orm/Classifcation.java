package com.era.orm;

/**
 * Classifcation entity. @author MyEclipse Persistence Tools
 */

public class Classifcation implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;

	// Constructors

	/** default constructor */
	public Classifcation() {
	}

	/** full constructor */
	public Classifcation(String title) {
		this.title = title;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}