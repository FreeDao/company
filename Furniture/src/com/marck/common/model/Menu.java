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
@Table(name="menu")
public class Menu {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String name;
	@Column
	private String url;
	@Column
	private Integer listId;
	@Column
	private Integer level;
	@Column
	private Integer pid;
	@Column
	private String icon;
	@Column
	private Integer sort;
	@Transient
	private List<Menu> childs;
	
	public Integer getSort() {
		return null == sort?0:sort;
	
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public List<Menu> getChilds() {
		return childs;//null == childs?"":childs;
	
	}
	public void setChilds(List<Menu> childs) {
		this.childs = childs;
	}
	public String getIcon() {
		return null == icon?"":icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getId() {
		return null == id?0:id;
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
	public String getUrl() {
		return null == url?"":url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getListId() {
		return null == listId?0:listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	public Integer getLevel() {
		return null == level?0:level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getPid() {
		return null == pid?0:pid;
	
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
}
