package com.era.orm;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String addtime;
	private Integer friendId;
	private Integer userId;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** full constructor */
	public Message(String title, String addtime, Integer friendId,
			Integer userId) {
		this.title = title;
		this.addtime = addtime;
		this.friendId = friendId;
		this.userId = userId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getFriendId() {
		return this.friendId;
	}

	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}