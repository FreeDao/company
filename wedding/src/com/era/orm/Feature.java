package com.era.orm;

/**
 * Feature entity. @author MyEclipse Persistence Tools
 */

public class Feature implements java.io.Serializable {

	// Fields

	private Integer id;
	private String featureName;
	private String address;
	private String dim;
	private String lot;
	private Integer price;
	private String time;
	private Integer leven;
	private String bife;
	private String humanity;
	private String obligate;
	private String etcaeteras;
	private String logo;
	private Integer areaId;
	private Integer groom;
	
	private String image;

	// Constructors

	/** default constructor */
	public Feature() {
	}

	/** full constructor */
	public Feature(String featureName, String address, String dim, String lot,
			Integer price, String time, Integer leven, String bife,
			String humanity, String obligate, String etcaeteras, String logo,
			Integer areaId, Integer groom) {
		this.featureName = featureName;
		this.address = address;
		this.dim = dim;
		this.lot = lot;
		this.price = price;
		this.time = time;
		this.leven = leven;
		this.bife = bife;
		this.humanity = humanity;
		this.obligate = obligate;
		this.etcaeteras = etcaeteras;
		this.logo = logo;
		this.areaId = areaId;
		this.groom = groom;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFeatureName() {
		return this.featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDim() {
		return this.dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}

	public String getLot() {
		return this.lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getLeven() {
		return this.leven;
	}

	public void setLeven(Integer leven) {
		this.leven = leven;
	}

	public String getBife() {
		return this.bife;
	}

	public void setBife(String bife) {
		this.bife = bife;
	}

	public String getHumanity() {
		return this.humanity;
	}

	public void setHumanity(String humanity) {
		this.humanity = humanity;
	}

	public String getObligate() {
		return this.obligate;
	}

	public void setObligate(String obligate) {
		this.obligate = obligate;
	}

	public String getEtcaeteras() {
		return this.etcaeteras;
	}

	public void setEtcaeteras(String etcaeteras) {
		this.etcaeteras = etcaeteras;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getGroom() {
		return this.groom;
	}

	public void setGroom(Integer groom) {
		this.groom = groom;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}