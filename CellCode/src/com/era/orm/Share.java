package com.era.orm;

/**
 * Share entity. @author MyEclipse Persistence Tools
 */

public class Share implements java.io.Serializable {

	// Fields

	private Integer id;
	private String addtime;
	private String bife;
	private Integer praise;
	private Integer userId;
	private Integer villageId;
	private String images;
	private Integer typeId;
	private Integer report;
	private String title;
	
	private String userName;
	private String userImage;
	private Integer number;
	private Integer userSex;
	private Integer isPraise;

	// Constructors

	/** default constructor */
	public Share() {
	}

	/** full constructor */
	public Share(String addtime, String bife, Integer praise, Integer userId,
			Integer villageId, String images, Integer typeId) {
		this.addtime = addtime;
		this.bife = bife;
		this.praise = praise;
		this.userId = userId;
		this.villageId = villageId;
		this.images = images;
		this.typeId = typeId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}


	public Integer getIsPraise() {
		return null == isPraise ? 0 : isPraise;
	}

	public void setIsPraise(Integer isPraise) {
		this.isPraise = isPraise;
	}

	public Integer getUserSex() {
		return null == userSex ? 0 : userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getTitle() {
		return null == title ? "" : title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getReport() {
		return null == report ? 0 : report;
	}

	public void setReport(Integer report) {
		this.report = report;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getBife() {
		return this.bife;
	}

	public void setBife(String bife) {
		this.bife = bife;
	}

	public Integer getPraise() {
		return this.praise;
	}

	public void setPraise(Integer praise) {
		this.praise = praise;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getVillageId() {
		return this.villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public String getImages() {
		return this.images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}