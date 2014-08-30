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
	
	private String userName;
	private String userImage;
	private int number;

	// Constructors

	/** default constructor */
	public Share() {
	}

	/** full constructor */
	public Share(String addtime, String bife, Integer praise, Integer userId,
			Integer villageId, String images) {
		this.addtime = addtime;
		this.bife = bife;
		this.praise = praise;
		this.userId = userId;
		this.villageId = villageId;
		this.images = images;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
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