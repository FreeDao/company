package com.era.orm;

/**
 * Image entity. @author MyEclipse Persistence Tools
 */

public class Image implements java.io.Serializable {

	// Fields

	private Integer id;
	private String imgUrl;
	private Integer type;
	private Integer compositeId;
	
	// Constructors

	/** default constructor */
	public Image() {
	}

	/** full constructor */
	public Image(String imgUrl, Integer type, Integer compositeId) {
		this.imgUrl = imgUrl;
		this.type = type;
		this.compositeId = compositeId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCompositeId() {
		return this.compositeId;
	}

	public void setCompositeId(Integer compositeId) {
		this.compositeId = compositeId;
	}

}