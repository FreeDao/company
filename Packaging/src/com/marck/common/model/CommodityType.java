package com.marck.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="commodityType")
public class CommodityType {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private Integer commodityId;
	@Column
	private Integer typeId;
	
	public Integer getId() {
		return null == id ? 0 : id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCommodityId() {
		return null == commodityId ? 0 : commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public Integer getTypeId() {
		return null == typeId ? 0 : typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
}
