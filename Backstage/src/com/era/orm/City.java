package com.era.orm;

/**
 * City entity. @author MyEclipse Persistence Tools
 */

public class City implements java.io.Serializable {

	// Fields

	private Integer id;
	private String cityName;
	private String cityWord;

	// Constructors

	/** default constructor */
	public City() {
	}

	/** full constructor */
	public City(String cityName, String cityWord) {
		this.cityName = cityName;
		this.cityWord = cityWord;
	}

	// Property accessors


	public String getCityName() {
		return this.cityName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}