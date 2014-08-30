package com.era.orm;


/**
 * Busmarset entity. @author MyEclipse Persistence Tools
 */

public class Busmarset implements java.io.Serializable {

	// Fields

	private Integer id;
	private String bmsUserName;
	private String addTime;
	private String telephone;
	private String bmsIntroduction;
	private Integer villageId;
	private Integer cityId;
	private Integer areaId;

	// Constructors

	/** default constructor */
	public Busmarset() {
	}

	/** full constructor */
	public Busmarset(String bmsUserName, String addTime, String telephone,
			String bmsIntroduction, Integer villageId) {
		this.bmsUserName = bmsUserName;
		this.addTime = addTime;
		this.telephone = telephone;
		this.bmsIntroduction = bmsIntroduction;
		this.villageId = villageId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBmsUserName() {
		return this.bmsUserName;
	}

	public void setBmsUserName(String bmsUserName) {
		this.bmsUserName = bmsUserName;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getBmsIntroduction() {
		return this.bmsIntroduction;
	}

	public void setBmsIntroduction(String bmsIntroduction) {
		this.bmsIntroduction = bmsIntroduction;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}


}