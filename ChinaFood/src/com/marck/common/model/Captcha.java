package com.marck.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="captcha")
public class Captcha {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String captcha;
	@Column
	private String captchaTime;
	@Column
	private String phone;
	@Column
	private Integer type;
	
	public Integer getType() {
		return null == type ? 0 : type;
	
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getId() {
		return null == id ? 0 : id;
	
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCaptcha() {
		return null == captcha ? "" : captcha;
	
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getCaptchaTime() {
		return null == captchaTime ? "" : captchaTime;
	
	}
	public void setCaptchaTime(String captchaTime) {
		this.captchaTime = captchaTime;
	}
	public String getPhone() {
		return null == phone ? "" : phone;
	
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
