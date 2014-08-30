package com.era.util;

public class BusMarketQuery {

	private Integer id;
	private String bmsUserName;
	private String telephone;
	private String bmsIntroduction;
	private Integer villageId;
	
	private Integer bmsmId;
	private String qq;
	private String addTime;
	private String email;
	private String userName;
	private String userPwd;
	
	private String villageName;
	
	public BusMarketQuery(Integer id, String bmsUserName, String telephone,
			String bmsIntroduction, Integer villageId, Integer bmsmId,
			String qq, String email, String userName, String userPwd,String addTime,String villageName) {
		super();
		this.id = id;
		this.bmsUserName = bmsUserName;
		this.telephone = telephone;
		this.bmsIntroduction = bmsIntroduction;
		this.villageId = villageId;
		this.bmsmId = bmsmId;
		this.qq = qq;
		this.email = email;
		this.userName = userName;
		this.userPwd = userPwd;
		this.addTime = addTime;
		this.villageName = villageName;
	}
	
	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBmsUserName() {
		return bmsUserName;
	}
	public void setBmsUserName(String bmsUserName) {
		this.bmsUserName = bmsUserName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getBmsIntroduction() {
		return bmsIntroduction;
	}
	public void setBmsIntroduction(String bmsIntroduction) {
		this.bmsIntroduction = bmsIntroduction;
	}
	public Integer getVillageId() {
		return villageId;
	}
	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}
	public Integer getBmsmId() {
		return bmsmId;
	}
	public void setBmsmId(Integer bmsmId) {
		this.bmsmId = bmsmId;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
}
