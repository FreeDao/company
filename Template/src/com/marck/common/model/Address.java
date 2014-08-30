package com.marck.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="address")
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String name;
	@Column
	private Integer provinceId;
	@Column
	private Integer cityId;
	@Column
	private Integer areaId;
	@Column
	private String address;
	@Column
	private String tel;
	@Column
	private String email;
	@Column
	private String postcode;
	@Column
	private Integer userId;
	@Column
	private Integer isDefualt;
	@Transient
	private String provinceName;
	@Transient
	private String cityName;
	@Transient
	private String areaName;
	
	public String getProvinceName() {
		return null == provinceName ? "" : provinceName;
	
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return null == cityName ? "" : cityName;
	
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAreaName() {
		return null == areaName ? "" : areaName;
	
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getId() {
		return null == id ? 0 : id;
	
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return null == name ? "" : name;
	
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getProvinceId() {
		return null == provinceId ? 0 : provinceId;
	
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return null == cityId ? 0 : cityId;
	
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getAreaId() {
		return null == areaId ? 0 : areaId;
	
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAddress() {
		return null == address ? "" : address;
	
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return null == tel ? "" : tel;
	
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return null == email ? "" : email;
	
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostcode() {
		return null == postcode ? "" : postcode;
	
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public Integer getUserId() {
		return null == userId ? 0 : userId;
	
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getIsDefualt() {
		return null == isDefualt ? 0 : isDefualt;
	
	}
	public void setIsDefualt(Integer isDefualt) {
		this.isDefualt = isDefualt;
	}
	
}
