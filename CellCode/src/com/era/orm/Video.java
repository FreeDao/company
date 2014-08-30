package com.era.orm;

/**
 * Video entity. @author MyEclipse Persistence Tools
 */

public class Video implements java.io.Serializable {

	// Fields

	private Integer id;
	private String logo;
	private String title;
	private String address;
	private String iphone;
	private Integer price;
	private String bife;
	private String traffic;
	private String addtime;
	private String layout;
	private String info;
	private String quarters;
	private String facilities;
	private Integer type;
	private Integer villageId;
	private String log;
	private String dim;
	private Integer personal;
	private String images;
	private String userName;
	private Float area;
	private String floor;

	// Constructors

	/** default constructor */
	public Video() {
	}

	/** full constructor */
	public Video(String logo, String title, String address, String iphone,
			Integer price, String bife, String traffic, String addtime,
			String layout, String info, String quarters, String facilities,
			Integer type, Integer villageId, String log, String dim,
			Integer personal, String images, String userName) {
		this.logo = logo;
		this.title = title;
		this.address = address;
		this.iphone = iphone;
		this.price = price;
		this.bife = bife;
		this.traffic = traffic;
		this.addtime = addtime;
		this.layout = layout;
		this.info = info;
		this.quarters = quarters;
		this.facilities = facilities;
		this.type = type;
		this.villageId = villageId;
		this.log = log;
		this.dim = dim;
		this.personal = personal;
		this.images = images;
		this.userName = userName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Float getArea() {
		return null == area ? 0 : area;
	}

	public void setArea(Float area) {
		this.area = area;
	}

	public String getFloor() {
		return null == floor ? "" : floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getBife() {
		return this.bife;
	}

	public void setBife(String bife) {
		this.bife = bife;
	}

	public String getTraffic() {
		return this.traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getLayout() {
		return this.layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getQuarters() {
		return this.quarters;
	}

	public void setQuarters(String quarters) {
		this.quarters = quarters;
	}

	public String getFacilities() {
		return this.facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getVillageId() {
		return this.villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
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

	public Integer getPersonal() {
		return this.personal;
	}

	public void setPersonal(Integer personal) {
		this.personal = personal;
	}

	public String getImages() {
		return this.images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}