package com.tcsh.model.remot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="recommend")
public class Recommend {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String name;
	@Column
	private Integer isIos;
	@Column
	private String logo;
	@Column
	private String ifNo;
	@Column
	private String bife;
	@Column
	private String aurl;
	@Column
	private String iurl;
	@Column
	private Integer sort;
	@Column
	private Integer ismend;
	@Column
	private Integer typeId;
	@Column
	private Integer cityId;
	@Column
	private Integer isCompany;
	
	public Integer getIsCompany() {
		return isCompany;
	}
	public void setIsCompany(Integer isCompany) {
		this.isCompany = isCompany;
	}
	public String getAurl() {
		return aurl;
	}
	public void setAurl(String aurl) {
		this.aurl = aurl;
	}
	public String getIurl() {
		return iurl;
	}
	public void setIurl(String iurl) {
		this.iurl = iurl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsIos() {
		return isIos;
	}
	public void setIsIos(Integer isIos) {
		this.isIos = isIos;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getIfNo() {
		return ifNo;
	}
	public void setIfNo(String ifNo) {
		this.ifNo = ifNo;
	}
	public String getBife() {
		return bife;
	}
	public void setBife(String bife) {
		this.bife = bife;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getIsmend() {
		return ismend;
	}
	public void setIsmend(Integer ismend) {
		this.ismend = ismend;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
}
