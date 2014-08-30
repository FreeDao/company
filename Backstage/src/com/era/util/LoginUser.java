package com.era.util;

import java.sql.Timestamp;

public class LoginUser {

	//公共属性
	private Integer id;
	private String userName;
	private String passWord;
	private String phone;
	private String qq;
	private String email;
	private String accountAddtime;	//账号添加时间
	private String infoAddTime;		//信息添加时间
	private Integer role;
	private String log;
	private String dim;
	private String cityId;
	private Integer areaId;
	private String address;
	private String villageId;
	
	//admin工作人员
	private String nickName;
	
	//城市管理员
	private String cityName;
	private String cityWord;
	
	//市场主
	private String bmsUserName;
	private String bmsIntroduction;
	private Integer marketInfoId;//市场主信息id
	
	//物业管理
	private String name;

	//商家
	private String titile;
	private String logo;
	private Integer productId;
	private Integer imgesId;
	private String brief;
	private String preferential;
	private Double level;
	private Integer typeId;
	private String type;
	private String recruitment;
	private Double price;
	private Integer sort;
	private String hotelType;
	private Integer shop;
	private Double otherPrice;
	private Integer postage;
	private String cityAdd;
	private Integer shopId;
	private Integer shopIdTwo;
	private Integer sellerInfoId;
	
	
	public Integer getMarketInfoId() {
		return marketInfoId;
	}
	public void setMarketInfoId(Integer marketInfoId) {
		this.marketInfoId = marketInfoId;
	}
	public Integer getSellerInfoId() {
		return sellerInfoId;
	}
	public void setSellerInfoId(Integer sellerInfoId) {
		this.sellerInfoId = sellerInfoId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityWord() {
		return cityWord;
	}
	public void setCityWord(String cityWord) {
		this.cityWord = cityWord;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public String getBmsUserName() {
		return bmsUserName;
	}
	public void setBmsUserName(String bmsUserName) {
		this.bmsUserName = bmsUserName;
	}
	public String getBmsIntroduction() {
		return bmsIntroduction;
	}
	public void setBmsIntroduction(String bmsIntroduction) {
		this.bmsIntroduction = bmsIntroduction;
	}
	public String getTitile() {
		return titile;
	}
	public void setTitile(String titile) {
		this.titile = titile;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getDim() {
		return dim;
	}
	public void setDim(String dim) {
		this.dim = dim;
	}
	public Integer getImgesId() {
		return imgesId;
	}
	public void setImgesId(Integer imgesId) {
		this.imgesId = imgesId;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getPreferential() {
		return preferential;
	}
	public void setPreferential(String preferential) {
		this.preferential = preferential;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLevel() {
		return level;
	}
	public void setLevel(Double level) {
		this.level = level;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRecruitment() {
		return recruitment;
	}
	public void setRecruitment(String recruitment) {
		this.recruitment = recruitment;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getHotelType() {
		return hotelType;
	}
	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
	public Integer getShop() {
		return shop;
	}
	public void setShop(Integer shop) {
		this.shop = shop;
	}
	public Double getOtherPrice() {
		return otherPrice;
	}
	public void setOtherPrice(Double otherPrice) {
		this.otherPrice = otherPrice;
	}
	public Integer getPostage() {
		return postage;
	}
	public void setPostage(Integer postage) {
		this.postage = postage;
	}
	public String getCityAdd() {
		return cityAdd;
	}
	public void setCityAdd(String cityAdd) {
		this.cityAdd = cityAdd;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getShopIdTwo() {
		return shopIdTwo;
	}
	public void setShopIdTwo(Integer shopIdTwo) {
		this.shopIdTwo = shopIdTwo;
	}
	public String getAccountAddtime() {
		return accountAddtime;
	}
	public void setAccountAddtime(String accountAddtime) {
		this.accountAddtime = accountAddtime;
	}
	public String getInfoAddTime() {
		return infoAddTime;
	}
	public void setInfoAddTime(String infoAddTime) {
		this.infoAddTime = infoAddTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	
}
