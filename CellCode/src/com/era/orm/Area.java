package com.era.orm;


/**
 * Area entity. @author MyEclipse Persistence Tools
 */

public class Area implements java.io.Serializable {

	// Fields

	private Integer id;
	private String areaName;
	private String addTime;
	private Integer cityId;

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** full constructor */
	public Area(String areaName, String addTime, Integer cityId) {
		this.areaName = areaName;
		this.addTime = addTime;
		this.cityId = cityId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
}