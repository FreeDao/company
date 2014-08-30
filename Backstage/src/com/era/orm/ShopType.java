package com.era.orm;

/**
 * ShopType entity. @author MyEclipse Persistence Tools
 */

public class ShopType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private String addtime;

	// Constructors

	/** default constructor */
	public ShopType() {
	}

	/** full constructor */
	public ShopType(String type, String addtime) {
		this.type = type;
		this.addtime = addtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

}