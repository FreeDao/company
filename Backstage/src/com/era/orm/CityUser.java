package com.era.orm;

/**
 * City entity. @author MyEclipse Persistence Tools
 */

public class CityUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userName;
	private String passWord;
	private Integer cityId;
	private String addTime;

	private String cityName;
	private String cityWord;
	
	// Constructors

	/** default constructor */
	
	public CityUser() {
	}

	public CityUser(Integer id, String userName, String passWord,
			Integer cityId, String addTime, String cityName, String cityWord) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.cityId = cityId;
		this.addTime = addTime;
		this.cityName = cityName;
		this.cityWord = cityWord;
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

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
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

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
}