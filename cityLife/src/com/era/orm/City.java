package com.era.orm;

import java.sql.Timestamp;

/**
 * City entity. @author MyEclipse Persistence Tools
 */

public class City implements java.io.Serializable {

	// Fields

	private Integer id;
	private String cityName;
	private String cityWord;
	private String addTime;
	private Integer isOpen;//是否已开通

	// Constructors

	/** default constructor */
	public City() {
	}

	/** full constructor */
	public City(String cityName, String cityWord, String addTime,
			Integer isOpen) {
		this.cityName = cityName;
		this.cityWord = cityWord;
		this.addTime = addTime;
		this.isOpen = isOpen;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityWord() {
		return cityWord;
	}

	public void setCityWord(String cityWord) {
		this.cityWord = cityWord;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
}