package com.era.orm;

/**
 * Consumption entity. @author MyEclipse Persistence Tools
 */

public class Consumption implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer money;
	private Integer sellId;
	private String addtime;
	private Integer userId;
	private String userName;
	private String iphone;
	private String log;
	private Integer consumption;

	// Constructors

	/** default constructor */
	public Consumption() {
	}

	/** full constructor */
	public Consumption(Integer money, Integer sellId, String addtime,
			Integer userId, String userName, String iphone, String log,
			Integer consumption) {
		this.money = money;
		this.sellId = sellId;
		this.addtime = addtime;
		this.userId = userId;
		this.userName = userName;
		this.iphone = iphone;
		this.log = log;
		this.consumption = consumption;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMoney() {
		return this.money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getSellId() {
		return this.sellId;
	}

	public void setSellId(Integer sellId) {
		this.sellId = sellId;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIphone() {
		return this.iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

	public String getLog() {
		return this.log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Integer getConsumption() {
		return this.consumption;
	}

	public void setConsumption(Integer consumption) {
		this.consumption = consumption;
	}

}