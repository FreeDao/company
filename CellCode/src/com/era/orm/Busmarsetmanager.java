package com.era.orm;

import java.sql.Timestamp;

/**
 * Busmarsetmanager entity. @author MyEclipse Persistence Tools
 */

public class Busmarsetmanager implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer bmsmId;
	private String qq;
	private String telePhone;
	private String email;
	private String addTime;
	private String userName;
	private String userPwd;

	// Constructors

	/** default constructor */
	public Busmarsetmanager() {
	}

	/** full constructor */
	public Busmarsetmanager(Integer bmsmId, String qq, String telePhone,
			String email, String addTime, String userName, String userPwd) {
		this.bmsmId = bmsmId;
		this.qq = qq;
		this.telePhone = telePhone;
		this.email = email;
		this.addTime = addTime;
		this.userName = userName;
		this.userPwd = userPwd;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBmsmId() {
		return this.bmsmId;
	}

	public void setBmsmId(Integer bmsmId) {
		this.bmsmId = bmsmId;
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

}