package com.marck.common.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="shop")
public class Shop {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private Integer userId;
	@Column
	private Integer commodityId;
	@Column
	private Integer num;
	@Column
	private Integer menuId;
	@Column
	private String addTime;
	@Column
	private Integer status;
	@Transient
	private String name;
	@Transient
	private String logo;
	@Transient
	private Double price;
	@Transient
	private String brief;
	@Transient
	private String tel;
	@Transient
	private String types;
	@Transient
	private String ids;
	@Transient
	private List<Type> typeList;
	
	public String getLogo() {
		return null == logo ? "" : logo;
	
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getStatus() {
		return null == status ? 0 : status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIds() {
		return null == ids ? "" : ids;
	
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getTypes() {
		return null == types ? "" : types;
	
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public List<Type> getTypeList() {
		return typeList;
	
	}

	public void setTypeList(List<Type> typeList) {
		this.typeList = typeList;
	}

	public String getName() {
		return null == name ? "" : name;
	
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return null == price ? 0 : price;
	
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getBrief() {
		return null == brief ? "" : brief;
	
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getTel() {
		return null == tel ? "" : tel;
	
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getId() {
		return null == id ? 0 : id;
	
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return null == userId ? 0 : userId;
	
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCommodityId() {
		return null == commodityId ? 0 : commodityId;
	
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public Integer getNum() {
		return null == num ? 0 : num;
	
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getMenuId() {
		return null == menuId ? 0 : menuId;
	
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getAddTime() {
		return null == addTime ? "" : addTime;
	
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
	
}
