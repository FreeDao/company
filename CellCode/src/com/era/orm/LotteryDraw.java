package com.era.orm;

/**
 * LotteryDraw entity. @author MyEclipse Persistence Tools
 */

public class LotteryDraw implements java.io.Serializable {

	// Fields

	private Integer id;
	private String draw;
	private Integer designated;
	private String addtime;
	private String iphone;
	private Integer activitiesId;
	private Integer official;
	private Integer isWin;
	private Integer isReceiving;
	private Integer surplus;
	private String win;

	// Constructors

	/** default constructor */
	public LotteryDraw() {
	}

	/** full constructor */
	public LotteryDraw(String draw, Integer designated, String addtime,
			String iphone, Integer activitiesId, Integer official) {
		this.draw = draw;
		this.designated = designated;
		this.addtime = addtime;
		this.iphone = iphone;
		this.activitiesId = activitiesId;
		this.official = official;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public String getWin() {
		
		switch (getIsWin()) {
		case 1:
			return "5元红包";
		case 2:
			return "10元红包";	
		case 3:
			return "50元红包";
		case 4:
			return "100元红包";
		default:
			return "未知";
		}
		
	}

	public void setWin(String win) {
		this.win = win;
	}

	public Integer getIsWin() {
		return null == isWin ? 0 : isWin;
	}

	public void setIsWin(Integer isWin) {
		this.isWin = isWin;
	}

	public Integer getIsReceiving() {
		return null == isReceiving ? 0 : isReceiving;
	}

	public void setIsReceiving(Integer isReceiving) {
		this.isReceiving = isReceiving;
	}

	public Integer getSurplus() {
		return null == surplus ? 0 : surplus;
	}

	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
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

	public Integer getActivitiesId() {
		return this.activitiesId;
	}

	public void setActivitiesId(Integer activitiesId) {
		this.activitiesId = activitiesId;
	}

	public Integer getOfficial() {
		return this.official;
	}

	public void setOfficial(Integer official) {
		this.official = official;
	}

}