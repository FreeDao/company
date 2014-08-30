package com.era.orm;

/**
 * SupplyAndDemand entity. @author MyEclipse Persistence Tools
 */

public class SupplyAndDemand implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private String price;
	private String address;
	private Integer cityId;
	private Integer typeId;
	private Integer statue;
	private Integer isfinish;
	private Integer sellerId;
	private String logo;
	private String corporate;
	private String info;
	private String phone;
	private String qq;
	private String email;
	private Integer userId;
	private String orderNum;
	private Integer type;
	private String matureTime;
	private String releaseTime;
	private String addTime;
	private String buyNum;
	private String productcontent;

	private String image;
	private String typeName;
	
	// Constructors

	/** default constructor */
	public SupplyAndDemand() {
	}

	/** full constructor */
	public SupplyAndDemand(String title, String content, String price,
			String address, Integer cityId, Integer typeId, Integer statue,
			Integer isfinish, Integer sellerId, String logo, String corporate,
			String info, String phone, String qq, String email, Integer userId,
			String orderNum, Integer type, String matureTime,
			String releaseTime, String addTime, String buyNum,
			String productcontent) {
		this.title = title;
		this.content = content;
		this.price = price;
		this.address = address;
		this.cityId = cityId;
		this.typeId = typeId;
		this.statue = statue;
		this.isfinish = isfinish;
		this.sellerId = sellerId;
		this.logo = logo;
		this.corporate = corporate;
		this.info = info;
		this.phone = phone;
		this.qq = qq;
		this.email = email;
		this.userId = userId;
		this.orderNum = orderNum;
		this.type = type;
		this.matureTime = matureTime;
		this.releaseTime = releaseTime;
		this.addTime = addTime;
		this.buyNum = buyNum;
		this.productcontent = productcontent;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Integer getStatue() {
		return this.statue;
	}

	public void setStatue(Integer statue) {
		this.statue = statue;
	}

	public Integer getIsfinish() {
		return this.isfinish;
	}

	public void setIsfinish(Integer isfinish) {
		this.isfinish = isfinish;
	}

	public Integer getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getCorporate() {
		return this.corporate;
	}

	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMatureTime() {
		return this.matureTime;
	}

	public void setMatureTime(String matureTime) {
		this.matureTime = matureTime;
	}

	public String getReleaseTime() {
		return this.releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getBuyNum() {
		return this.buyNum;
	}

	public void setBuyNum(String buyNum) {
		this.buyNum = buyNum;
	}

	public String getProductcontent() {
		return this.productcontent;
	}

	public void setProductcontent(String productcontent) {
		this.productcontent = productcontent;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}