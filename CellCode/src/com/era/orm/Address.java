package com.era.orm;

/**
 * Address entity. @author MyEclipse Persistence Tools
 */

public class Address implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String area;
	private String address;
	private String iphone;
	private String emali;
	private String code;
	private String addtime;
	private Integer userId;
	private Integer thedefault;

	// Constructors

	/** default constructor */
	public Address() {
	}

	/** full constructor */
	public Address(String name, String area, String address, String iphone,
			String emali, String code, String addtime, Integer userId,
			Integer thedefault) {
		this.name = name;
		this.area = area;
		this.address = address;
		this.iphone = iphone;
		this.emali = emali;
		this.code = code;
		this.addtime = addtime;
		this.userId = userId;
		this.thedefault = thedefault;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIphone() {
		return this.iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

	public String getEmali() {
		return this.emali;
	}

	public void setEmali(String emali) {
		this.emali = emali;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getThedefault() {
		return this.thedefault;
	}

	public void setThedefault(Integer thedefault) {
		this.thedefault = thedefault;
	}

}