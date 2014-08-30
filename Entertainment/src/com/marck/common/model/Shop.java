package com.marck.common.model;

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
	private Integer sizeId;
	@Column
	private Integer colorId;
	@Column
	private String addTime;
	@Transient
	private String name;
	@Transient
	private Integer price;
	@Transient
	private String brief;
	@Transient
	private String tel;
	@Transient
	private String size;
	@Transient
	private String color;
	
	public Shop(){
		super();
	}

	public Shop(Integer id, Integer userId, Integer commodityId, Integer num, Integer menuId, Integer sizeId, Integer colorId,
			String addTime, String name, Integer price, String brief, String tel,String size,String color) {
		super();
		this.id = id;
		this.userId = userId;
		this.commodityId = commodityId;
		this.num = num;
		this.menuId = menuId;
		this.sizeId = sizeId;
		this.colorId = colorId;
		this.addTime = addTime;
		this.name = name;
		this.price = price;
		this.brief = brief;
		this.tel = tel;
		this.size = size;
		this.color = color;
	}


	public String getSize() {
		return null == size ? "" : size;
	
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return null == color ? "" : color;
	
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return null == name ? "" : name;
	
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getPrice() {
		return null == price ? 0 : price;
	
	}



	public void setPrice(Integer price) {
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
	public Integer getSizeId() {
		return null == sizeId ? 0 : sizeId;
	
	}
	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}
	public Integer getColorId() {
		return null == colorId ? 0 : colorId;
	
	}
	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}
	public String getAddTime() {
		return null == addTime ? "" : addTime;
	
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
	
}
