package com.marck.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="shoptype")
public class ShopType {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private Integer shopId;
	@Column
	private Integer typeId;
	
	public Integer getId() {
		return null == id ? 0 : id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getShopId() {
		return null == shopId ? 0 : shopId;
	
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getTypeId() {
		return null == typeId ? 0 : typeId;
	
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	
}
