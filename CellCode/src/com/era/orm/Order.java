package com.era.orm;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */

public class Order implements java.io.Serializable {

	// Fields

	private Integer id;
	private String shopId;
	private String price;
	private String yf;
	private Integer addressId;
	private String addtime;
	private Integer userId;
	private Integer buy;
	private String state;
	private Integer number;

	private String title;//标题
	private String logo;//logo
	private Integer integral = 0;//获得积分
	private String addressName;//地址
	private String iphone;//电话
	private String name;//姓名
	
	// Constructors

	/** default constructor */
	public Order() {
	}

	/** full constructor */
	public Order(String shopId, String price, String yf, Integer addressId,
			String addtime, Integer userId, Integer buy, String state,
			String title, String logo, Integer number) {
		this.shopId = shopId;
		this.price = price;
		this.yf = yf;
		this.addressId = addressId;
		this.addtime = addtime;
		this.userId = userId;
		this.buy = buy;
		this.state = state;
		this.title = title;
		this.logo = logo;
		this.number = number;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getIphone() {
		return iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShopId() {
		return this.shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getYf() {
		return this.yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public Integer getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
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

	public Integer getBuy() {
		return this.buy;
	}

	public void setBuy(Integer buy) {
		this.buy = buy;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}