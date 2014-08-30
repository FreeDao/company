package com.marck.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="info")
public class Info {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String title;
	@Column
	private String digest;
	@Column
	private String addTime;
	@Column
	private String logo;
	@Column
	private String source;
	@Column
	private String content;
	@Column
	private Integer menuId;
	@Column
	private Integer isTop;
	@Column
	private Integer category;
	@Column
	private Integer provinceId;
	@Column
	private Integer cityId;
	@Column
	private Integer areaId;
	@Column
	private String address;
	@Column
	private String time;
	@Column
	private Integer typeId;
	@Column
	private Integer subTypeId;
	@Transient
	private String provinceName;
	@Transient
	private String cityName;
	@Transient
	private String areaName;
	@Transient
	private String typeName;
	@Transient
	private String subTypeName;
	
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
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
	public String getTypeName() {
		return null == typeName ? "" : typeName;
	
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getSubTypeName() {
		return null == subTypeName ? "" : subTypeName;
	
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
	public Integer getTypeId() {
		return null == typeId ? 0 : typeId;
	
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getSubTypeId() {
		return null == subTypeId ? 0 : subTypeId;
	
	}
	public void setSubTypeId(Integer subTypeId) {
		this.subTypeId = subTypeId;
	}
	public String getAddress() {
		return null == address ? "" : address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTime() {
		return null == time ? "" : time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return null == title?"":title;
	
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCategory() {
		return null == category?0:category;
	
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getId() {
		return null == id ? 0 : id;
	
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDigest() {
		return null == digest ? "" : digest;
	
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	
	public String getAddTime() {
		return null == addTime?"":addTime;
	
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getLogo() {
		return null == logo ? "" : logo;
	
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getSource() {
		return null == source ? "" : source;
	
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getContent() {
		return null == content ? "" : content;
	
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getMenuId() {
		return null == menuId ? 0 : menuId;
	
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getIsTop() {
		return null == isTop ? 0 : isTop;
	
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

}
