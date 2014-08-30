package com.marck.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="order")
public class Order {

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
	private Integer total;
	@Column
	private Integer addressId;
	
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
	public Integer getTotal() {
		return null == total ? 0 : total;
	
	}
	public void setTotal(Integer total) {
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
