package com.era.orm;

import java.sql.Timestamp;

/**
 * Backmessage entity. @author MyEclipse Persistence Tools
 */

public class Backmessage implements java.io.Serializable {

	// Fields

	private Integer id;
	private String backContent;
	private String addTime;
	private String backPhone;
	private String user;

	// Constructors

	/** default constructor */
	public Backmessage() {
	}

	/** full constructor */
	public Backmessage(String backContent, String addTime, String backPhone,
			String user) {
		this.backContent = backContent;
		this.addTime = addTime;
		this.backPhone = backPhone;
		this.user = user;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBackContent() {
		return this.backContent;
	}

	public void setBackContent(String backContent) {
		this.backContent = backContent;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getBackPhone() {
		return this.backPhone;
	}

	public void setBackPhone(String backPhone) {
		this.backPhone = backPhone;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}