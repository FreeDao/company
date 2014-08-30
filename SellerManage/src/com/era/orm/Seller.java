package com.era.orm;

import java.sql.Timestamp;

/**
 * Seller entity. @author MyEclipse Persistence Tools
 */

public class Seller implements java.io.Serializable {

	// Fields

	private Integer id;
	private String titile;
	private String logo;
	private String phone;
	private Integer productId;
	private String log;
	private String dim;
	private Integer imgesId;
	private String brief;
	private String preferential;
	private Timestamp addtime;
	private String address;
	private Double level;
	private Integer cityId;
	private Integer typeId;

	// Constructors

	/** default constructor */
	public Seller() {
	}

	/** full constructor */
	public Seller(String titile, String logo, String phone, Integer productId,
			String log, String dim, Integer imgesId, String brief,
			String preferential, Timestamp addtime, String address,
			Double level, Integer cityId, Integer typeId) {
		this.titile = titile;
		this.logo = logo;
		this.phone = phone;
		this.productId = productId;
		this.log = log;
		this.dim = dim;
		this.imgesId = imgesId;
		this.brief = brief;
		this.preferential = preferential;
		this.addtime = addtime;
		this.address = address;
		this.level = level;
		this.cityId = cityId;
		this.typeId = typeId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitile() {
		return this.titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getLog() {
		return this.log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getDim() {
		return this.dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}

	public Integer getImgesId() {
		return this.imgesId;
	}

	public void setImgesId(Integer imgesId) {
		this.imgesId = imgesId;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getPreferential() {
		return this.preferential;
	}

	public void setPreferential(String preferential) {
		this.preferential = preferential;
	}

	public Timestamp getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLevel() {
		return this.level;
	}

	public void setLevel(Double level) {
		this.level = level;
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

}