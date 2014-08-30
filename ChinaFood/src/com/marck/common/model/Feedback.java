package com.marck.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String content;
	@Column
	private String email;
	@Column
	private String addTime;
	
	public Integer getId() {
		return null == id ? 0 : id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return null == content ? "" : content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEmail() {
		return null == email ? "" : email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddTime() {
		return null == addTime ? "" : addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
}
