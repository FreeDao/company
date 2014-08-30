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
	private Timestamp addTime;
	private Integer isOpen;

	// Constructors

	/** default constructor */
	public City() {
	}

	/** full constructor */
	public City(String cityName, String cityWord, Timestamp addTime,
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
		return this.cityWord;
	}

	public void setCityWord(String cityWord) {
		this.cityWord = cityWord;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Integer getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

}