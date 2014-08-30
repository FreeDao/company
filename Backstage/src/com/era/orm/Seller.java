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
	private Integer shop;
	private Double otherPrice;
	private Integer postage;
	private Integer shopId;
	private Integer shopIdTwo;
	private Integer userId;
	private Integer userRole;
	private String name;
	private String endTime;
	
	
	private String sellerImgs;//商家图片
    private String productImgs;//产品服务图片
    private String collectId;//收藏id
    private String distance;//距离

	// Constructors

	/** default constructor */
	public Seller() {
	}

	/** full constructor */
	public Seller(String titile, String logo, String phone, Integer productId,
			String log, String dim, Integer imgesId, String brief,
			String preferential, Timestamp addtime, String address,
			Double level, Integer cityId, Integer typeId, Integer areaId,
			String type, String recruitment, Double price, Integer sort,
			String hotelType, Integer shop, Double otherPrice, Integer postage,
			String cityAdd, Integer shopId, Integer shopIdTwo) {
		this.titile = titile;
		this.logo = logo;
		this.phone = phone;
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
		this.shop = shop;
		this.otherPrice = otherPrice;
		this.postage = postage;
		this.shopId = shopId;
		this.shopIdTwo = shopIdTwo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitile() {
		return this.titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLog() {
		return this.log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getDim() {
		return this.dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}

	public Integer getImgesId() {
		return this.imgesId;
	}

	public void setImgesId(Integer imgesId) {
		this.imgesId = imgesId;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getPreferential() {
		return this.preferential;
	}

	public void setPreferential(String preferential) {
		this.preferential = preferential;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLevel() {
		return this.level;
	}

	public void setLevel(Double level) {
		this.level = level;
	}


	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getRecruitment() {
		return this.recruitment;
	}

	public void setRecruitment(String recruitment) {
		this.recruitment = recruitment;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}


	public Integer getShop() {
		return this.shop;
	}

	public void setShop(Integer shop) {
		this.shop = shop;
	}

	public Double getOtherPrice() {
		return this.otherPrice;
	}

	public void setOtherPrice(Double otherPrice) {
		this.otherPrice = otherPrice;
	}

	public Integer getPostage() {
		return this.postage;
	}

	public void setPostage(Integer postage) {
		this.postage = postage;
	}


	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getShopIdTwo() {
		return this.shopIdTwo;
	}

	public void setShopIdTwo(Integer shopIdTwo) {
		this.shopIdTwo = shopIdTwo;
	}

	public String getSellerImgs() {
		return sellerImgs;
	}

	public void setSellerImgs(String sellerImgs) {
		this.sellerImgs = sellerImgs;
	}

	public String getProductImgs() {
		return productImgs;
	}

	public void setProductImgs(String productImgs) {
		this.productImgs = productImgs;
	}

	public String getCollectId() {
		return collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public Integer getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

}