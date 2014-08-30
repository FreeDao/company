package com.era.orm;

/**
 * City entity. @author MyEclipse Persistence Tools
 */

public class UserToken implements java.io.Serializable {

	// Fields

	private Integer id;
	private String addTime;
	private Integer userId;
	private String token;
	private Integer cityId;

	// Constructors

	/** default constructor */
	public UserToken() {
	}

	public Integer getCityId() {
		return null == cityId ? 0 : cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getId() {
		return null == id ? 0 : id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddTime() {
		return null == addTime ? "" : addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getUserId() {
		return null == userId ? 0 : userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getToken() {
		return null == token ? "" : token;
	}

	public void setToken(String token) {
		this.token = token;
	}


}