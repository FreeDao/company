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