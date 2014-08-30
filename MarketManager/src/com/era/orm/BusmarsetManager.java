package com.era.orm;

import java.sql.Timestamp;

/**
 * BusmarsetManager entity. @author MyEclipse Persistence Tools
 */

public class BusmarsetManager implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer bmsmId;
	private String qq;
	private String telePhone;
	private String email;
	private Timestamp addTime;
	private String userName;
	private String userPwd;
	private Integer root;

	// Constructors

	/** default constructor */
	public BusmarsetManager() {
	}

	/** full constructor */
	public BusmarsetManager(Integer bmsmId, String qq, String telePhone,
			String email, Timestamp addTime, String userName, String userPwd,
			Integer root) {
		this.bmsmId = bmsmId;
		this.qq = qq;
		this.telePhone = telePhone;
		this.email = email;
		this.addTime = addTime;
		this.userName = userName;
		this.userPwd = userPwd;
		this.root = root;
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

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
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

	public Integer getRoot() {
		return this.root;
	}

	public void setRoot(Integer root) {
		this.root = root;
	}

}