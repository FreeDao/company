package com.era.orm;

/**
 * Lotterydraw entity. @author MyEclipse Persistence Tools
 */

public class Lotterydraw implements java.io.Serializable {

	// Fields

	private Integer id;
	private String draw;
	private Integer designated;
	private String addtime;
	private String iphone;

	// Constructors

	/** default constructor */
	public Lotterydraw() {
	}

	/** full constructor */
	public Lotterydraw(String draw, Integer designated, String addtime,
			String iphone) {
		this.draw = draw;
		this.designated = designated;
		this.addtime = addtime;
		this.iphone = iphone;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDraw() {
		return this.draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public Integer getDesignated() {
		return this.designated;
	}

	public void setDesignated(Integer designated) {
		this.designated = designated;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getIphone() {
		return this.iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

}