package com.tcsh.model.remot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * City entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="city")
public class City implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String cityName;
	@Column
	private String cityWord;

	// Constructors

	/** default constructor */
	public City() {
	}

	/** full constructor */
	public City(String cityName) {
		this.cityName = cityName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public String getCityWord() {
		return cityWord;
	}

	public void setCityWord(String cityWord) {
		this.cityWord = cityWord;
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

}