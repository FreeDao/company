package com.marck.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="comment")
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String content;
	@Column
	private String addTime;
	@Column
	private Integer userId;
	@Column
	private Integer menuId;
	@Column
	private Integer pid;
	@Transient
	private String nick;
	
	public Comment(){
		super();
	}
	
	public Comment(Integer id, String content, String addTime, Integer userId,
			Integer menuId,Integer pid, String nick) {
		super();
		this.id = id;
		this.content = content;
		this.addTime = addTime;
		this.userId = userId;
		this.menuId = menuId;
		this.pid = pid;
		this.nick = nick;
	}
	

	public Integer getPid() {
		return null == pid ? 0 : pid;
	
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getNick() {
		return null == nick ? "" : nick;
	
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
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
	public String getAddTime() {
		return null == addTime ? "" : addTime;
	
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getUserId() {
		return null == userId ? 0 : userId;
	
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMenuId() {
		return null == menuId ? 0 : menuId;
	
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	
	
}
