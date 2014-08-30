package com.era.orm;

/**
 * BusinessMan entity. @author MyEclipse Persistence Tools
 */

public class BusinessMan implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String pwd;
	private Integer sellerId;

	// Constructors

	/** default constructor */
	public BusinessMan() {
	}

	/** full constructor */
	public BusinessMan(String name, String pwd, Integer sellerId) {
		this.name = name;
		this.pwd = pwd;
		this.sellerId = sellerId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

}