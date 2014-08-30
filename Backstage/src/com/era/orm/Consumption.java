package com.era.orm;

/**
 * Consumption entity. @author MyEclipse Persistence Tools
 */

public class Consumption implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer money;
	private Integer sellId;

	// Constructors

	/** default constructor */
	public Consumption() {
	}

	/** full constructor */
	public Consumption(Integer money, Integer sellId) {
		this.money = money;
		this.sellId = sellId;
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

}