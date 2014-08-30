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

	// Constructors

	/** default constructor */
	public Order() {
	}

	/** full constructor */
	public Order(String shopId, String price, String yf, Integer addressId,
			String addtime, Integer userId, Integer buy, String state) {
		this.shopId = shopId;
		this.price = price;
		this.yf = yf;
		this.addressId = addressId;
		this.addtime = addtime;
		this.userId = userId;
		this.buy = buy;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
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

}