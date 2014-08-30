package com.era.orm;

/**
 * Regionallocations entity. @author MyEclipse Persistence Tools
 */

public class Regionallocations implements java.io.Serializable {

	// Fields

	private Integer id;
	private String areaRegion;
	private Integer areaId;
	private Integer sort;

	// Constructors

	/** default constructor */
	public Regionallocations() {
	}

	/** full constructor */
	public Regionallocations(String areaRegion, Integer areaId, Integer sort) {
		this.areaRegion = areaRegion;
		this.areaId = areaId;
		this.sort = sort;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAreaRegion() {
		return this.areaRegion;
	}

	public void setAreaRegion(String areaRegion) {
		this.areaRegion = areaRegion;
	}

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}