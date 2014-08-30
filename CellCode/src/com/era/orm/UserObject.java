package com.era.orm;

/**
 * City entity. @author MyEclipse Persistence Tools
 */

public class UserObject implements java.io.Serializable {

	// Fields

	private Integer id;
	private String addtime;
	private Integer oid;
	private Integer type;
	private Integer userId;

	// Constructors

	/** default constructor */
	public UserObject() {
	}

	public String getAddtime() {
		return null == addtime ? "" : addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getOid() {
		return null == oid ? 0 : oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getType() {
		return null == type ? 0 : type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUserId() {
		return null == userId ? 0 : userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}