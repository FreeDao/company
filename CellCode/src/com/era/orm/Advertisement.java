package com.era.orm;

/**
 * Activities entity. @author MyEclipse Persistence Tools
 */

public class Advertisement implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String logo;
	private String content;
	private Integer villageId;
	private String url;
	
	public String getUrl() {
		return null == url ? "" : url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getId() {
		return null == id ? 0 : id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return null == title ? "" : title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return null == logo ? "" : logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getContent() {
		return null == content ? "" : content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getVillageId() {
		return null == villageId ? 0 : villageId;
	}
	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	
}