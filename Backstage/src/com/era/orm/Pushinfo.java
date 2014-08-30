package com.era.orm;

import java.sql.Timestamp;

/**
 * Pushinfo entity. @author MyEclipse Persistence Tools
 */

public class Pushinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer cityId;
	private String key;
	private Integer isSure;
	private Integer appType;
	private String addTime;

	// Constructors

	/** default constructor */
	public Pushinfo() {
	}

	/** full constructor */
	public Pushinfo(Integer cityId, String key, Integer isSure,
			Integer appType, String addTime) {
		this.cityId = cityId;
		this.key = key;
		this.isSure = isSure;
		this.appType = appType;
		this.addTime = addTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getIsSure() {
		return this.isSure;
	}

	public void setIsSure(Integer isSure) {
		this.isSure = isSure;
	}

	public Integer getAppType() {
		return this.appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
}