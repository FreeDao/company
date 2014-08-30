package com.marck.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seller")
public class Seller {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String name;
	@Column
	private String tel;
	@Column
	private String address;
	@Column
	private Double lng;
	@Column
	private Double lat;
	@Column
	private String productBrief;
	@Column
	private String sellerBrief;
	@Column
	private String logo;
	@Column
	private String job;
	@Column
	private String contact;
	@Column
	private Integer cityId;
	@Column
	private Integer areaId;
	@Column
	private String education;
	@Column
	private String experience;
	@Column
	private String salary;
	@Column
	private Integer typeId;
	@Column
	private Integer subTypeId;
	@Column
	private Integer menuId;
	@Column
	private Integer isTop;
	@Column
	private Integer category;
	@Column
	private String addTime;
	@Column
	private Integer userId;
	@Column
	private String email;
	
	public String getEmail() {
		return null == email ? "" : email;
	
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getUserId() {
		return null == userId ? 0 : userId;
	
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAddTime() {
		return null == addTime?"":addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
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
	public String getName() {
		return null == name?"":name;
	
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return null == tel ? "" : tel;
	
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return null == address ? "" : address;
	
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLng() {
		return null == lng?0:lng;
	
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return null == lat?0:lat;
	
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public String getProductBrief() {
		return null == productBrief ? "" : productBrief;
	
	}
	public void setProductBrief(String productBrief) {
		this.productBrief = productBrief;
	}
	public String getSellerBrief() {
		return null == sellerBrief ? "" : sellerBrief;
	
	}
	public void setSellerBrief(String sellerBrief) {
		this.sellerBrief = sellerBrief;
	}
	public String getLogo() {
		return null == logo ? "" : logo;
	
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getJob() {
		return null == job ? "" : job;
	
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getContact() {
		return null == contact ? "" : contact;
	
	}
	public void setContact(String contact) {
		this.contact = contact;
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
	public String getEducation() {
		return null == education ? "" : education;
	
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getExperience() {
		return null == experience ? "" : experience;
	
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getSalary() {
		return null == salary ? "" : salary;
	
	}
	public void setSalary(String salary) {
		this.salary = salary;
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
