package com.era.orm;

/**
 * Winning entity. @author MyEclipse Persistence Tools
 */

public class Winning implements java.io.Serializable {

	// Fields

	private Integer id;
	private String iphone;
	private String comment;
	private String time;
	private String whether;
	private Integer overdue;

	// Constructors

	/** default constructor */
	public Winning() {
	}

	/** full constructor */
	public Winning(String iphone, String comment, String time, String whether,
			Integer overdue) {
		this.iphone = iphone;
		this.comment = comment;
		this.time = time;
		this.whether = whether;
		this.overdue = overdue;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIphone() {
		return this.iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWhether() {
		return this.whether;
	}

	public void setWhether(String whether) {
		this.whether = whether;
	}

	public Integer getOverdue() {
		return this.overdue;
	}

	public void setOverdue(Integer overdue) {
		this.overdue = overdue;
	}

}