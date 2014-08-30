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
@Table(name="userorder")
public class UserOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String code;
	@Column
	private Integer userId;
	@Column
	private String addTime;
	@Column
	private Integer status;
	@Column
	private String express;
	@Column
	private String brief;
	@Column
	private Double total;
	@Column
	private Integer addressId;
	@Transient
	private String addressName;
	@Transient
	private String tel;
	@Transient
	private String name;
	@Transient
	private List<Shop> shopList;
	@Transient
	private String shops;
	@Transient
	private String ids;
	@Transient
	private Integer menuId;
	@Transient
	private Integer commodityId;
	@Transient
	private Integer num;
	@Transient
	private String types;
	
	public String getAddressName() {
		return null == addressName ? "" : addressName;
	
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getTel() {
		return null == tel ? "" : tel;
	
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getName() {
		return null == name ? "" : name;
	
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypes() {
		return null == types ? "" : types;
	
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public Integer getMenuId() {
		return null == menuId ? 0 : menuId;
	
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
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
	public String getIds() {
		return null == ids ? "" : ids;
	
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getShops() {
		return null == shops ? "" : shops;
	
	}
	public void setShops(String shops) {
		this.shops = shops;
	}
	public List<Shop> getShopList() {
		return shopList;
	}
	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	
	public String getExpress() {
		return null == express ? "" : express;
	
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public String getBrief() {
		return null == brief ? "" : brief;
	
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public Double getTotal() {
		return null == total ? 0 : total;
	
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getAddressId() {
		return null == addressId ? 0 : addressId;
	
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public Integer getId() {
		return null == id ? 0 : id;
	
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return null == code ? "" : code;
	
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getUserId() {
		return null == userId ? 0 : userId;
	
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAddTime() {
		return null == addTime ? "" : addTime;
	
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getStatus() {
		return null == status ? 0 : status;
	
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	
}
