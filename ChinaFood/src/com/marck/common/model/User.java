package com.marck.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String phone;
	@Column
	private String passWord;
	@Column
	private String openId;
	@Column
	private Integer age;
	@Column
	private Integer sex;
	@Column
	private String email;
	@Column
	private String birthday;
	@Column
	private String address;
	@Column
	private String nick;
	@Column
	private String avatar;
	@Column
	private Integer role;//1管理员，2一般用户
	@Column
	private String name;
	@Column
	private String addTime;
	@Transient
	private String captcha;
	
	public String getAddTime() {
		return null == addTime ? "" : addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getName() {
		return null == name ? "" : name;
	
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCaptcha() {
		return null == captcha?"":captcha;
	
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public Integer getId() {
		return null == id ? 0 : id;
	
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return null == phone ? "" : phone;
	
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassWord() {
		return null == passWord ? "" : passWord;
	
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getOpenId() {
		return null == openId ? "" : openId;
	
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getAge() {
		return null == age ? 0 : age;
	
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getSex() {
		return null == sex ? 0 : sex;
	
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return null == email ? "" : email;
	
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getBirthday() {
		return null == birthday?"":birthday;
	
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return null == address ? "" : address;
	
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNick() {
		return null == nick ? "" : nick;
	
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getAvatar() {
		return null == avatar ? "" : avatar;
	
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getRole() {
		return null == role ? 0 : role;
	
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	
}
