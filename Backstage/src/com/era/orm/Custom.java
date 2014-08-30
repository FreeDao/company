package com.era.orm;

/**
 * Custom entity. @author MyEclipse Persistence Tools
 */

public class Custom implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String iphone;
	private String addtime;
	private Integer villageId;

	// Constructors

	/** default constructor */
	public Custom() {
	}

	/** full constructor */
	public Custom(String name, String iphone, String addtime, Integer villageId) {
		this.name = name;
		this.iphone = iphone;
		this.addtime = addtime;
		this.villageId = villageId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getVillageId() {
		return this.villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

}