package com.era.orm;

import java.sql.Timestamp;

/**
 * Sellermanager entity. @author MyEclipse Persistence Tools
 */

public class Sellermanager implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userName;
	private String userPwd;
	private String qq;
	private String telePhone;
	private String email;
	private String addTime;
	private Integer sellerId;

	// Constructors

	/** default constructor */
	public Sellermanager() {
	}

	/** full constructor */
	public Sellermanager(String userName, String userPwd, String qq,
			String telePhone, String email, String addTime, Integer sellerId) {
		this.userName = userName;
		this.userPwd = userPwd;
		this.qq = qq;
		this.telePhone = telePhone;
		this.email = email;
		this.addTime = addTime;
		this.sellerId = sellerId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTelePhone() {
		return this.telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

}