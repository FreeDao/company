package com.era.orm;

/**
 * ShopTypeTwo entity. @author MyEclipse Persistence Tools
 */

public class ShopTypeTwo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String addtime;
	private Integer shopTypeId;

	// Constructors

	/** default constructor */
	public ShopTypeTwo() {
	}

	/** full constructor */
	public ShopTypeTwo(String name, String addtime, Integer shopTypeId) {
		this.name = name;
		this.addtime = addtime;
		this.shopTypeId = shopTypeId;
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

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getShopTypeId() {
		return this.shopTypeId;
	}

	public void setShopTypeId(Integer shopTypeId) {
		this.shopTypeId = shopTypeId;
	}

}