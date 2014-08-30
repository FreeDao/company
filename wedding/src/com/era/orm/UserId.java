package com.era.orm;

/**
 * UserId entity. @author MyEclipse Persistence Tools
 */

public class UserId implements java.io.Serializable {

	// Fields

	private String userName;
	private Integer id;
	private String passWord;
	private String phone;
	private String qq;

	// Constructors

	/** default constructor */
	public UserId() {
	}

	/** full constructor */
	public UserId(String userName, Integer id, String passWord, String phone,
			String qq) {
		this.userName = userName;
		this.id = id;
		this.passWord = passWord;
		this.phone = phone;
		this.qq = qq;
	}

	// Property accessors

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserId))
			return false;
		UserId castOther = (UserId) other;

		return ((this.getUserName() == castOther.getUserName()) || (this
				.getUserName() != null && castOther.getUserName() != null && this
				.getUserName().equals(castOther.getUserName())))
				&& ((this.getId() == castOther.getId()) || (this.getId() != null
						&& castOther.getId() != null && this.getId().equals(
						castOther.getId())))
				&& ((this.getPassWord() == castOther.getPassWord()) || (this
						.getPassWord() != null
						&& castOther.getPassWord() != null && this
						.getPassWord().equals(castOther.getPassWord())))
				&& ((this.getPhone() == castOther.getPhone()) || (this
						.getPhone() != null && castOther.getPhone() != null && this
						.getPhone().equals(castOther.getPhone())))
				&& ((this.getQq() == castOther.getQq()) || (this.getQq() != null
						&& castOther.getQq() != null && this.getQq().equals(
						castOther.getQq())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserName() == null ? 0 : this.getUserName().hashCode());
		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getPassWord() == null ? 0 : this.getPassWord().hashCode());
		result = 37 * result
				+ (getPhone() == null ? 0 : this.getPhone().hashCode());
		result = 37 * result + (getQq() == null ? 0 : this.getQq().hashCode());
		return result;
	}

}