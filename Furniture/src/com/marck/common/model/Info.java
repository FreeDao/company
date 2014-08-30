package com.marck.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
