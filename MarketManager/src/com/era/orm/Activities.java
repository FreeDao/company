package com.era.orm;

/**
 * Activities entity. @author MyEclipse Persistence Tools
 */

public class Activities implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String comment;
	private String logo;
	private String addtime;
	private Integer sort;
	private String lotteryAddtime;
	private String bife;
	private String prize;
	private String rules;
	private Integer lucky;
	private String large;

	// Constructors

	/** default constructor */
	public Activities() {
	}

	/** full constructor */
	public Activities(String title, String comment, String logo,
			String addtime, Integer sort, String lotteryAddtime, String bife,
			String prize, String rules, Integer lucky, String large) {
		this.title = title;
		this.comment = comment;
		this.logo = logo;
		this.addtime = addtime;
		this.sort = sort;
		this.lotteryAddtime = lotteryAddtime;
		this.bife = bife;
		this.prize = prize;
		this.rules = rules;
		this.lucky = lucky;
		this.large = large;
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

	public String getLotteryAddtime() {
		return this.lotteryAddtime;
	}

	public void setLotteryAddtime(String lotteryAddtime) {
		this.lotteryAddtime = lotteryAddtime;
	}

	public String getBife() {
		return this.bife;
	}

	public void setBife(String bife) {
		this.bife = bife;
	}

	public String getPrize() {
		return this.prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}

	public String getRules() {
		return this.rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public Integer getLucky() {
		return this.lucky;
	}

	public void setLucky(Integer lucky) {
		this.lucky = lucky;
	}

	public String getLarge() {
		return this.large;
	}

	public void setLarge(String large) {
		this.large = large;
	}

}