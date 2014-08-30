package com.era.orm;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Activities entity. @author MyEclipse Persistence Tools
 */

public class Activities implements java.io.Serializable {

	// Fields

	private Integer id;
	private String dim;
	private String log;
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
	private Integer personal;
	private Integer rate;
	private Integer rootQode;
	private String address;
	private String iphone;
	private Integer sellId;
	private String price;
	private Integer num;
	
	private String win;
	private Integer isReceiving;
	private Integer isPast;
	private String draw;
	
	/*private Integer winId;
	private String whether;
	private Integer overdue;*/
	// Constructors

	/** default constructor */
	public Activities() {
	}

	// Property accessors

	public Activities(Integer id, String dim, String log, String title,
			String comment, String logo, String addtime, Integer sort,
			String lotteryAddtime, String bife, String prize, String rules,
			Integer lucky, String large, Integer personal, Integer rate,
			Integer rootQode, String address, String iphone, Integer sellId,
			String price, Integer num, Integer isWin, Integer isReceiving,String draw) {
		super();
		this.id = id;
		this.dim = dim;
		this.log = log;
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
		this.personal = personal;
		this.rate = rate;
		this.rootQode = rootQode;
		this.address = address;
		this.iphone = iphone;
		this.sellId = sellId;
		this.price = price;
		this.num = num;
		switch (isWin) {
		case 1:
			this.win = "5元红包";
			break;
		case 2:
			this.win = "10元红包";
			break;
		case 3:
			this.win = "50元红包";
			break;
		case 4:
			this.win = "100元红包";
			break;
		default:
			this.win = "未知";
			break;
		}
		this.isReceiving = isReceiving;
		
		Calendar calendar = Calendar.getInstance();
		String now = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		
		if(now.compareTo(lotteryAddtime) < 0  ){
			this.isPast = 0;
		}else{
			this.isPast = 1;
		}
		
		this.draw = draw;
	}


	public String getDraw() {
		return null == draw ? "" : draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public Integer getId() {
		return this.id;
	}

	public String getWin() {
		return null == win ? "" : win;
	}

	public void setWin(String win) {
		this.win = win;
	}

	public Integer getIsReceiving() {
		return null == isReceiving ? 0 : isReceiving;
	}

	public void setIsReceiving(Integer isReceiving) {
		this.isReceiving = isReceiving;
	}

	public Integer getIsPast() {
		return null == isPast ? 0 : isPast;
	}

	public void setIsPast(Integer isPast) {
		this.isPast = isPast;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDim() {
		return this.dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}

	public String getLog() {
		return this.log;
	}

	public void setLog(String log) {
		this.log = log;
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

	public Integer getPersonal() {
		return this.personal;
	}

	public void setPersonal(Integer personal) {
		this.personal = personal;
	}

	public Integer getRate() {
		return this.rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getRootQode() {
		return this.rootQode;
	}

	public void setRootQode(Integer rootQode) {
		this.rootQode = rootQode;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIphone() {
		return this.iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

	public Integer getSellId() {
		return this.sellId;
	}

	public void setSellId(Integer sellId) {
		this.sellId = sellId;
	}

	public String getPrice() {
		return null == price ? "" : price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getNum() {
		return null == num ? 0 : num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}


}