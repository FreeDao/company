package com.tcsh.model.cell;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * City entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="lotterydraw")
public class Lotterydraw implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String draw;
	@Column
	private Integer designated;
	@Column
	private String addtime;
	@Column
	private String iphone;
	@Column
	private Integer activitiesId;
	@Column
	private Integer official;
	@Column
	private Integer isWin;
	@Column
	private Integer isReceiving;
	@Column
	private Integer surplus;
	
	public Integer getActivitiesId() {
		return activitiesId;
	}
	public void setActivitiesId(Integer activitiesId) {
		this.activitiesId = activitiesId;
	}
	public Integer getOfficial() {
		return official;
	}
	public void setOfficial(Integer official) {
		this.official = official;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDraw() {
		return draw;
	}
	public void setDraw(String draw) {
		this.draw = draw;
	}
	public Integer getDesignated() {
		return designated;
	}
	public void setDesignated(Integer designated) {
		this.designated = designated;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getIphone() {
		return iphone;
	}
	public void setIphone(String iphone) {
		this.iphone = iphone;
	}
	public Integer getIsWin() {
		return isWin;
	}
	public void setIsWin(Integer isWin) {
		this.isWin = isWin;
	}
	public Integer getIsReceiving() {
		return isReceiving;
	}
	public void setIsReceiving(Integer isReceiving) {
		this.isReceiving = isReceiving;
	}
	public Integer getSurplus() {
		return surplus;
	}
	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}

	// Constructors


}