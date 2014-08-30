package com.era.orm;

/**
 * Plan entity. @author MyEclipse Persistence Tools
 */

public class Plan implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String comment;
	private String addss;
	private Integer price;
	private String phone;
	private String userName;
	private String emali;
	private Integer classificationId;
	private String logo;
	private String contacts;

	// Constructors

	/** default constructor */
	public Plan() {
	}

	/** full constructor */
	public Plan(String title, String comment, String addss, Integer price,
			String phone, String userName, String emali,
			Integer classificationId, String logo, String contacts) {
		this.title = title;
		this.comment = comment;
		this.addss = addss;
		this.price = price;
		this.phone = phone;
		this.userName = userName;
		this.emali = emali;
		this.classificationId = classificationId;
		this.logo = logo;
		this.contacts = contacts;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAddss() {
		return this.addss;
	}

	public void setAddss(String addss) {
		this.addss = addss;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmali() {
		return this.emali;
	}

	public void setEmali(String emali) {
		this.emali = emali;
	}

	public Integer getClassificationId() {
		return this.classificationId;
	}

	public void setClassificationId(Integer classificationId) {
		this.classificationId = classificationId;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

}