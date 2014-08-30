package com.marck.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="img")
public class Img implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String brief;
	@Column
	private String url;
	@Column
	private Integer pid;
	@Column
	private Integer menuId;
	@Column
	private String addTime;
	@Transient
	private String fileId;
	
	public String getFileId() {
		return null == fileId ? "" : fileId;
	
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getAddTime() {
		return null == addTime ? "" : addTime;
	
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getId() {
		return null == id ? 0 : id;
	
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrief() {
		return null == brief ? "" : brief;
	
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getUrl() {
		return null == url ? "" : url;
	
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPid() {
		return null == pid ? 0 : pid;
	
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getMenuId() {
		return null == menuId ? 0 : menuId;
	
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	

}
