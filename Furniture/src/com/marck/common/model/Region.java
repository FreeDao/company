package com.marck.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="region")
public class Region {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer id;
	@Column
	private String name;
	@Column
	private Integer pid;
	@Column
	private Integer level;
	@Transient
	private Integer menuId;
	
	public Integer getLevel() {
		return null == level ? 0 : level;
	
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getMenuId() {
		return null == menuId ? 0 : menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getId() {
		return null == id ? 0 : id;
	
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return null == name ? "" : name;
	
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPid() {
		return null == pid ? 0 : pid;
	
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
