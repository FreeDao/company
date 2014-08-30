package com.era.orm;

/**
 * Seller entity. @author MyEclipse Persistence Tools
 */

public class Seller implements java.io.Serializable {

	// Fields

	private Integer id;
	private String sellerName;
	private Integer cityId;
	private Integer typeId;
	private String content;
	private String address;
	private String sellerOwner;
	private String phone;
	private String email;
	private String imgUrl;
	private Integer userId;

	// Constructors

	/** default constructor */
	public Seller() {
	}

	/** full constructor */
	public Seller(String sellerName, Integer cityId, Integer typeId,
			String content, String address, String sellerOwner, String phone,
			String email, String imgUrl, Integer userId) {
		this.sellerName = sellerName;
		this.cityId = cityId;
		this.typeId = typeId;
		this.content = content;
		this.address = address;
		this.sellerOwner = sellerOwner;
		this.phone = phone;
		this.email = email;
		this.imgUrl = imgUrl;
		this.userId = userId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSellerName() {
		return this.sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSellerOwner() {
		return this.sellerOwner;
	}

	public void setSellerOwner(String sellerOwner) {
		this.sellerOwner = sellerOwner;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}