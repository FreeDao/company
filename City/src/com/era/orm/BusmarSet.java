package com.era.orm;

import java.sql.Timestamp;

/**
 * BusmarSet entity. @author MyEclipse Persistence Tools
 */

public class BusmarSet implements java.io.Serializable {

	// Fields

	private Integer id;
	private String bmsUserName;
	private Timestamp addTime;
	private String telephone;
	private String bmsIntroduction;
	private Integer marketId;

	// Constructors

	/** default constructor */
	public BusmarSet() {
	}

	/** full constructor */
	public BusmarSet(String bmsUserName, Timestamp addTime, String telephone,
			String bmsIntroduction, Integer marketId) {
		this.bmsUserName = bmsUserName;
		this.addTime = addTime;
		this.telephone = telephone;
		this.bmsIntroduction = bmsIntroduction;
		this.marketId = marketId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBmsUserName() {
		return this.bmsUserName;
	}

	public void setBmsUserName(String bmsUserName) {
		this.bmsUserName = bmsUserName;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getBmsIntroduction() {
		return this.bmsIntroduction;
	}

	public void setBmsIntroduction(String bmsIntroduction) {
		this.bmsIntroduction = bmsIntroduction;
	}

	public Integer getMarketId() {
		return this.marketId;
	}

	public void setMarketId(Integer marketId) {
		this.marketId = marketId;
	}

}