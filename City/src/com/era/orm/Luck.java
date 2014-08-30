package com.era.orm;

/**
 * Luck entity. @author MyEclipse Persistence Tools
 */

public class Luck implements java.io.Serializable {

	// Fields

	private Integer id;
	private String iphone;
	private Integer luckOne;

	// Constructors

	/** default constructor */
	public Luck() {
	}

	/** full constructor */
	public Luck(String iphone, Integer luckOne) {
		this.iphone = iphone;
		this.luckOne = luckOne;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIphone() {
		return this.iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

	public Integer getLuckOne() {
		return this.luckOne;
	}

	public void setLuckOne(Integer luckOne) {
		this.luckOne = luckOne;
	}

}