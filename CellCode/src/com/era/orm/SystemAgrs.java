package com.era.orm;

/**
 * Activities entity. @author MyEclipse Persistence Tools
 */

public class SystemAgrs implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tel;

	public Integer getId() {
		return null == id ? 0 : id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTel() {
		return null == tel ? "" : tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}