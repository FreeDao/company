package com.era.orm;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nickName;
	private String passWord;
	private Integer sex;
	private String name;
	private Integer integral;
	private String addtime;
	private String type;
	private String imgUrl;
	private String phone;
	private String email;
	private Integer sellIsNo;
	private Integer villageId;
	private String log;
	private String dim;
	private String sellerPassWord;
	private Integer householder;
	private String doorplate;
	private Integer number;
	private Integer manId;
	private String endTime;
	private Integer isFreeze; 
	private Integer sellerId;
	
	private String villageName;
	private Integer info;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String nickName, String passWord, Integer sex, String name,
			Integer integral, String addtime, String type, String imgUrl,
			String phone, String email, Integer sellIsNo, Integer villageId,
			String log, String dim, String sellerPassWord, Integer householder,
			String doorplate, Integer number, Integer manId) {
		this.nickName = nickName;
		this.passWord = passWord;
		this.sex = sex;
		this.name = name;
		this.integral = integral;
		this.addtime = addtime;
		this.type = type;
		this.imgUrl = imgUrl;
		this.phone = phone;
		this.email = email;
		this.sellIsNo = sellIsNo;
		this.villageId = villageId;
		this.log = log;
		this.dim = dim;
		this.sellerPassWord = sellerPassWord;
		this.householder = householder;
		this.doorplate = doorplate;
		this.number = number;
		this.manId = manId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getIsFreeze() {
		return null == isFreeze ? 0 : isFreeze;
	}

	public void setIsFreeze(Integer isFreeze) {
		this.isFreeze = isFreeze;
	}

	public String getEndTime() {
		return null == endTime ? "" : endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getIntegral() {
		return null == integral?0:integral;
	}

	public String getNickName() {
		return null == nickName ? "" : nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassWord() {
		return null == passWord ? "" : passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getSex() {
		return null == sex ? 0 : sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getName() {
		return null == name ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddtime() {
		return null == addtime ? "" : addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getType() {
		return null == type ? "" : type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImgUrl() {
		return null == imgUrl ? "" : imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPhone() {
		return null == phone ? "" : phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return null == email ? "" : email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSellIsNo() {
		return null == sellIsNo ? 0 : sellIsNo;
	}

	public void setSellIsNo(Integer sellIsNo) {
		this.sellIsNo = sellIsNo;
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

	public String getSellerPassWord() {
		return null == sellerPassWord ? "" : sellerPassWord;
	}

	public void setSellerPassWord(String sellerPassWord) {
		this.sellerPassWord = sellerPassWord;
	}

	public Integer getHouseholder() {
		return null == householder ? 0 : householder;
	}

	public void setHouseholder(Integer householder) {
		this.householder = householder;
	}

	public String getDoorplate() {
		return null == doorplate ? "" : doorplate;
	}

	public void setDoorplate(String doorplate) {
		this.doorplate = doorplate;
	}

	public Integer getNumber() {
		return null == number ? 0 : number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getManId() {
		return null == manId ? 0 : manId;
	}

	public void setManId(Integer manId) {
		this.manId = manId;
	}

	public String getVillageName() {
		return null == villageName ? "" : villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public Integer getInfo() {
		return null == info ? 0 : info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}


}