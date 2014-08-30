package com.era.orm;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Seller entity. @author MyEclipse Persistence Tools
 */

public class Seller implements java.io.Serializable {

	// Fields

	private Integer id;
	private String titile;
	private String logo;
	private String phone;
	private Integer villageId;
	private String log;
	private String dim;
	private Integer imgesId;
	private String brief;
	private String preferential;
	private Date addtime;
	private String address;
	private Double level;
	private Integer typeId;
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
	private Integer userId;
	private Integer userRole;
	private String endTime;
	private String name;//产品服务介绍
	
    private String sellerImgs;//商家图片
    private String productImgs;//产品服务图片
    private String collectId;//收藏id
    private String distance;//距离
    private String villageName;//小区名字

	// Constructors

	/** default constructor */
	public Seller() {
	}

	/** full constructor */
	public Seller(String titile, String logo, String phone, Integer villageId,
			String log, String dim, Integer imgesId, String brief,
			String preferential, Date addtime, String address,
			Double level, Integer cityId, Integer typeId, Integer areaId,
			String type, String recruitment, Double price, Integer sort,
			String hotelType, Integer shop, Double otherPrice, Integer postage,
			String cityAdd, Integer shopId, Integer shopIdTwo) {
		this.titile = titile;
		this.logo = logo;
		this.phone = phone;
		this.villageId = villageId;
		this.log = log;
		this.dim = dim;
		this.imgesId = imgesId;
		this.brief = brief;
		this.preferential = preferential;
		this.addtime = addtime;
		this.address = address;
		this.level = level;
		this.typeId = typeId;
		this.recruitment = recruitment;
		this.price = price;
		this.sort = sort;
		this.hotelType = hotelType;
		this.shop = shop;
		this.otherPrice = otherPrice;
		this.postage = postage;
		this.cityAdd = cityAdd;
		this.shopId = shopId;
		this.shopIdTwo = shopIdTwo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public String getVillageName() {
		return null == villageName ? "" : villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public Integer getUserId() {
		return null == userId ? 0 : userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserRole() {
		return null == userRole ? 0 : userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

	public String getEndTime() {
		return null == endTime ? "" : endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTitile() {
		return null == titile ? "" : titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}

	public String getLogo() {
		return null == logo ? "" : logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPhone() {
		return null == phone ? "" : phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getVillageId() {
		return null == villageId ? 0 : villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public String getLog() {
		return null == log ? "" : log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getDim() {
		return null == dim ? "" : dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}

	public Integer getImgesId() {
		return null == imgesId ? 0 : imgesId;
	}

	public void setImgesId(Integer imgesId) {
		this.imgesId = imgesId;
	}

	public String getBrief() {
		return null == brief ? "" : brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getPreferential() {
		return null == preferential ? "" : preferential;
	}

	public void setPreferential(String preferential) {
		this.preferential = preferential;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getAddress() {
		return null == address ? "" : address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLevel() {
		return null == level ? 0 : level;
	}

	public void setLevel(Double level) {
		this.level = level;
	}


	public Integer getTypeId() {
		return null == typeId ? 0 : typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getRecruitment() {
		return null == recruitment ? "" : recruitment;
	}

	public void setRecruitment(String recruitment) {
		this.recruitment = recruitment;
	}

	public Double getPrice() {
		return null == price ? 0 : price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getSort() {
		return null == sort ? 0 : sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getHotelType() {
		return null == hotelType ? "" : hotelType;
	}

	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}

	public Integer getShop() {
		return null == shop ? 0 : shop;
	}

	public void setShop(Integer shop) {
		this.shop = shop;
	}

	public Double getOtherPrice() {
		return null == otherPrice ? 0 : otherPrice;
	}

	public void setOtherPrice(Double otherPrice) {
		this.otherPrice = otherPrice;
	}

	public Integer getPostage() {
		return null == postage ? 0 : postage;
	}

	public void setPostage(Integer postage) {
		this.postage = postage;
	}

	public String getCityAdd() {
		return null == cityAdd ? "" : cityAdd;
	}

	public void setCityAdd(String cityAdd) {
		this.cityAdd = cityAdd;
	}

	public Integer getShopId() {
		return null == shopId ? 0 : shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getShopIdTwo() {
		return null == shopIdTwo ? 0 : shopIdTwo;
	}

	public void setShopIdTwo(Integer shopIdTwo) {
		this.shopIdTwo = shopIdTwo;
	}

	public String getName() {
		return null == name ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSellerImgs() {
		return null == sellerImgs ? "" : sellerImgs;
	}

	public void setSellerImgs(String sellerImgs) {
		this.sellerImgs = sellerImgs;
	}

	public String getProductImgs() {
		return null == productImgs ? "" : productImgs;
	}

	public void setProductImgs(String productImgs) {
		this.productImgs = productImgs;
	}

	public String getCollectId() {
		return null == collectId ? "" : collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	public String getDistance() {
		return null == distance ? "" : distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}