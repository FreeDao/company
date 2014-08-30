package com.era.orm;

/**
 * Information entity. @author MyEclipse Persistence Tools
 */

public class Information implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String comment;
	private String logo;
	private String addtime;
	private Integer sort;
	private String review;

	// Constructors

	/** default constructor */
	public Information() {
	}

	/** full constructor */
	public Information(String title, String comment, String logo,
			String addtime, Integer sort, String review) {
		this.title = title;
		this.comment = comment;
		this.logo = logo;
		this.addtime = addtime;
		this.sort = sort;
		this.review = review;
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

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getReview() {
		return this.review;
	}

	public void setReview(String review) {
		this.review = review;
	}

}