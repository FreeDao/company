package com.era.orm;

import java.util.List;

/**
 * Issue entity. @author MyEclipse Persistence Tools
 */

public class Issue implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String addtime;
	private Integer current;
	
	private List<LotteryDraw> listDraw;

	// Constructors

	/** default constructor */
	public Issue() {
	}

	/** full constructor */
	public Issue(String title, String addtime, Integer current) {
		this.title = title;
		this.addtime = addtime;
		this.current = current;
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

	public Integer getCurrent() {
		return this.current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public List<LotteryDraw> getListDraw() {
		return listDraw;
	}

	public void setListDraw(List<LotteryDraw> listDraw) {
		this.listDraw = listDraw;
	}

}