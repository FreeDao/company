package com.era.orm;

/**
 * UserFeedBack entity. @author MyEclipse Persistence Tools
 */

public class UserFeedBack implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userName;
	private String content;
	private String iphone;

	// Constructors

	/** default constructor */
	public UserFeedBack() {
	}

	/** full constructor */
	public UserFeedBack(String userName, String content, String iphone) {
		this.userName = userName;
		this.content = content;
		this.iphone = iphone;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIphone() {
		return this.iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

}