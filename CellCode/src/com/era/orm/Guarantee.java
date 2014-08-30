package com.era.orm;

/**
 * Guarantee entity. @author MyEclipse Persistence Tools
 */

public class Guarantee implements java.io.Serializable {

	// Fields

	private Integer id;
	private String bife;
	private String address;
	private String iphone;
	private String addtime;
	private Integer isNo;
	private String outAddtime;
	private Integer villageId;
	private Integer userId;
	private Integer status;
	private Integer feedback;
	private Integer completeId;
	private String completeTime;

	// Constructors

	/** default constructor */
	public Guarantee() {
	}

	/** full constructor */
	public Guarantee(String bife, String address, String iphone,
			String addtime, Integer isNo, String outAddtime, Integer villageId) {
		this.bife = bife;
		this.address = address;
		this.iphone = iphone;
		this.addtime = addtime;
		this.isNo = isNo;
		this.outAddtime = outAddtime;
		this.villageId = villageId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public String getCompleteTime() {
		return null == completeTime ? "" : completeTime;
	}

	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	public Integer getCompleteId() {
		return null == completeId ? 0 : completeId;
	}

	public void setCompleteId(Integer completeId) {
		this.completeId = completeId;
	}

	public Integer getUserId() {
		return null == userId ? 0 : userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return null == status ? 0 : status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFeedback() {
		return null == feedback ? 0 : feedback;
	}

	public void setFeedback(Integer feedback) {
		this.feedback = feedback;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBife() {
		return this.bife;
	}

	public void setBife(String bife) {
		this.bife = bife;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIphone() {
		return this.iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getIsNo() {
		return this.isNo;
	}

	public void setIsNo(Integer isNo) {
		this.isNo = isNo;
	}

	public String getOutAddtime() {
		return this.outAddtime;
	}

	public void setOutAddtime(String outAddtime) {
		this.outAddtime = outAddtime;
	}

	public Integer getVillageId() {
		return this.villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

}