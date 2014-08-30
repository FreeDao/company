package com.era.orm;

/**
 * Mall entity. @author MyEclipse Persistence Tools
 */

public class Mall implements java.io.Serializable {

	// Fields

	private Integer id;
	private String logo;
	private String title;
	private Integer number;
	private Integer integral;
	private String bife;
	private Integer cityId;

	// Constructors

	/** default constructor */
	public Mall() {
	}

	/** full constructor */
	public Mall(String logo, String title, Integer number, Integer integral,
			String bife) {
		this.logo = logo;
		this.title = title;
		this.number = number;
		this.integral = integral;
		this.bife = bife;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Integer getCityId() {
		return null == cityId ? 0 : cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getIntegral() {
		return this.integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getBife() {
		return this.bife;
	}

	public void setBife(String bife) {
		this.bife = bife;
	}

}