package com.era.orm;

import java.sql.Timestamp;

/**
 * Checkversion entity. @author MyEclipse Persistence Tools
 */

public class Checkversion implements java.io.Serializable {

	// Fields

	private Integer id;
	private String versionNum;
	private String appUrl;
	private Timestamp addTime;
	private Integer appType;
	private Integer isUse;
	private String comment;

	// Constructors

	/** default constructor */
	public Checkversion() {
	}

	/** full constructor */
	public Checkversion(String versionNum, String appUrl, Timestamp addTime,
			Integer appType, Integer isUse, String comment) {
		this.versionNum = versionNum;
		this.appUrl = appUrl;
		this.addTime = addTime;
		this.appType = appType;
		this.isUse = isUse;
		this.comment = comment;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersionNum() {
		return this.versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public String getAppUrl() {
		return this.appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Integer getAppType() {
		return this.appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public Integer getIsUse() {
		return this.isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}