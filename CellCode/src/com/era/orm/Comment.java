package com.era.orm;

import java.util.ArrayList;
import java.util.List;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields

	private Integer id;
	private String conent;
	private String addtime;
	private Integer sellerId;
	private String user;
	private Double level;
	private String impress;
	private Integer type;
	private Integer pid;
	private Integer receiver;
	
	private String nickName;
	private String userName;
	private String receiverName;
	private List<Comment> cs = new ArrayList<Comment>();
	
	// Constructors

	/** default constructor */
	public Comment() {
		
	}

	/** full constructor */
	public Comment(Integer id,String conent, String addtime, Integer sellerId,
			String user, Double level, Integer type,Integer pid,Integer receiver,String userName,String receiverName) {
		this.id = id;
		this.conent = conent;
		this.addtime = addtime;
		this.sellerId = sellerId;
		this.user = user;
		this.level = level;
		this.type = type;
		this.pid = pid;
		this.receiver = receiver;
		this.userName = userName;
		this.receiverName = receiverName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public List<Comment> getCs() {
		return cs;
	}

	public void setCs(List<Comment> cs) {
		this.cs = cs;
	}

	public Integer getPid() {
		return null == pid ? 0 : pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getReceiver() {
		return null == receiver ? 0 : receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	public String getUserName() {
		return null == userName ? "" : userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReceiverName() {
		return null == receiverName ? "" : receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConent() {
		return this.conent;
	}

	public void setConent(String conent) {
		this.conent = conent;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Double getLevel() {
		return this.level;
	}

	public void setLevel(Double level) {
		this.level = level;
	}

	public String getImpress() {
		return null == this.impress ? "" : this.impress;
	}

	public void setImpress(String impress) {
		this.impress = impress;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getNickName() {
		return  null == nickName ? "" : nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}