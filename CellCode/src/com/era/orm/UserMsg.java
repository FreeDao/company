package com.era.orm;

/**
 * City entity. @author MyEclipse Persistence Tools
 */

public class UserMsg implements java.io.Serializable {

	// Fields

	private Integer id;
	private String addTime;
	private Integer userId;
	private String title;
	private String content;
	private Integer cityId;

	// Constructors

	/** default constructor */
	public UserMsg() {
	}

	public String getTitle() {
		return null == title ? "" : title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return null == content ? "" : content;
	}

	public void setContent(String content) {
		this.content = content;
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

}