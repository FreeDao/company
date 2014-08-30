package com.era.orm;

/**
 * Info entity. @author MyEclipse Persistence Tools
 */

public class Info implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String comment;
	private String logo;
	private String addtime;
	private Integer type;
	private String more;

	// Constructors

	/** default constructor */
	public Info() {
	}

	/** full constructor */
	public Info(String title, String comment, String logo, String addtime,
			Integer type, String more) {
		this.title = title;
		this.comment = comment;
		this.logo = logo;
		this.addtime = addtime;
		this.type = type;
		this.more = more;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMore() {
		return this.more;
	}

	public void setMore(String more) {
		this.more = more;
	}

}