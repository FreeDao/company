package com.era.orm;

/**
 * Market entity. @author MyEclipse Persistence Tools
 */

public class Market implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private Integer cityId;
	private Integer sort;

	// Constructors

	/** default constructor */
	public Market() {
	}

	/** full constructor */
	public Market(String type, Integer cityId, Integer sort) {
		this.type = type;
		this.cityId = cityId;
		this.sort = sort;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}