package com.era.orm;

/**
 * Outdoor entity. @author MyEclipse Persistence Tools
 */

public class Outdoor implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private String userName;
	private String icon;
	private String des;
	private String addtime;
	private String userImage;

	// Constructors

	/** default constructor */
	public Outdoor() {
	}

	/** full constructor */
	public Outdoor(Integer userId, String userName, String icon, String des,
			String addtime) {
		this.userId = userId;
		this.userName = userName;
		this.icon = icon;
		this.des = des;
		this.addtime = addtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

}