package com.era.orm;

/**
 * LotteryDraw entity. @author MyEclipse Persistence Tools
 */

public class VillageAd implements java.io.Serializable {

	// Fields

	private Integer id;
	private String url;
	private Integer villageId;
	private String logo;
	private Integer isOnclick;
	
	public Integer getId() {
		return null == id ? 0 : id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return null == url ? "" : url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getVillageId() {
		return null == villageId ? 0 : villageId;
	}
	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}
	public String getLogo() {
		return null == logo ? "" : logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Integer getIsOnclick() {
		return null == isOnclick ? 0 : isOnclick;
	}
	public void setIsOnclick(Integer isOnclick) {
		this.isOnclick = isOnclick;
	}


}