package com.era.orm;

/**
 * Company entity. @author MyEclipse Persistence Tools
 */

public class Company implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String addss;
	private String phone;
	private String comment;
	private String userName;
	private String contacts;
	private String emali;
	private String logo;

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** full constructor */
	public Company(String title, String addss, String phone, String comment,
			String userName, String contacts, String emali, String logo) {
		this.title = title;
		this.addss = addss;
		this.phone = phone;
		this.comment = comment;
		this.userName = userName;
		this.contacts = contacts;
		this.emali = emali;
		this.logo = logo;
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

	public String getAddss() {
		return this.addss;
	}

	public void setAddss(String addss) {
		this.addss = addss;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getEmali() {
		return this.emali;
	}

	public void setEmali(String emali) {
		this.emali = emali;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}