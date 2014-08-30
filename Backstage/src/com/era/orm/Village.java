package com.era.orm;

/**
 * Village entity. @author MyEclipse Persistence Tools
 */

public class Village implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String address;
	private String log;
	private String dim;
	private Integer cityId;
	private String addtime;
	private Integer areaId;

	// Constructors

	/** default constructor */
	public Village() {
	}

	/** full constructor */
	public Village(String name, String address, String log, String dim,
			Integer cityId, String addtime, Integer areaId) {
		this.name = name;
		this.address = address;
		this.log = log;
		this.dim = dim;
		this.cityId = cityId;
		this.addtime = addtime;
		this.areaId = areaId;
	}


	public String getName() {
		return this.name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

}