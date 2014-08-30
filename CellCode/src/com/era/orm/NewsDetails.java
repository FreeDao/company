package com.era.orm;

import java.sql.Timestamp;

/**
 * NewsDetails entity. @author MyEclipse Persistence Tools
 */

public class NewsDetails implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer villageId;
	private String title;
	private Timestamp addtime;
	private String author;
	private Integer imagesId;
	private String conent;
	private Integer isHead;
	private String imgsList;

	// Constructors

	/** default constructor */
	public NewsDetails() {
	}

	/** full constructor */
	public NewsDetails(Integer villageId, String title, Timestamp addtime,
			String author, Integer imagesId, String conent, Integer isHead) {
		this.villageId = villageId;
		this.title = title;
		this.addtime = addtime;
		this.author = author;
		this.imagesId = imagesId;
		this.conent = conent;
		this.isHead = isHead;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVillageId() {
		return this.villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getImagesId() {
		return this.imagesId;
	}

	public void setImagesId(Integer imagesId) {
		this.imagesId = imagesId;
	}

	public String getConent() {
		return this.conent;
	}

	public void setConent(String conent) {
		this.conent = conent;
	}

	public Integer getIsHead() {
		return this.isHead;
	}

	public String getImgsList() {
		return imgsList;
	}

	public void setImgsList(String imgsList) {
		this.imgsList = imgsList;
	}

	public void setIsHead(Integer isHead) {
		this.isHead = isHead;
	}

}