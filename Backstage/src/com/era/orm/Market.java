package com.era.orm;

/**
 * Market entity. @author MyEclipse Persistence Tools
 */

public class Market implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private Integer villageId;
	private Integer sort;
	private String logo;
	private Integer typeEr;

	// Constructors

	/** default constructor */
	public Market() {
	}

	/** full constructor */
	public Market(String type, Integer villageId, Integer sort, String logo,
			Integer typeEr) {
		this.type = type;
		this.villageId = villageId;
		this.sort = sort;
		this.logo = logo;
		this.typeEr = typeEr;
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

	public Integer getVillageId() {
		return this.villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getTypeEr() {
		return this.typeEr;
	}

	public void setTypeEr(Integer typeEr) {
		this.typeEr = typeEr;
	}

}