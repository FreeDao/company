package com.era.orm;

import java.util.Date;

/**
 * Village entity. @author MyEclipse Persistence Tools
 */

public class VillageManager implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userName;
	private String passWord;
	private Integer villageId;
	private Date addTime;

	// Constructors

	/** default constructor */
	public VillageManager() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}