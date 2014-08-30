package com.era.orm;

import java.util.List;

/**
 * Collect entity. @author MyEclipse Persistence Tools
 */

public class Collect implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private String addtime;
	private String comment;
	private Integer supplyId;
	private Integer ishave;
	private Integer informationId;
	private Integer relevanceId;
	
	private List<SupplyAndDemand> list_supply;
	private List<Information> listInformation;
	private String email;

	// Constructors

	/** default constructor */
	public Collect() {
	}

	/** full constructor */
	public Collect(Integer userId, String addtime, String comment,
			Integer supplyId, Integer ishave, Integer informationId,
			Integer relevanceId) {
		this.userId = userId;
		this.addtime = addtime;
		this.comment = comment;
		this.supplyId = supplyId;
		this.ishave = ishave;
		this.informationId = informationId;
		this.relevanceId = relevanceId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getSupplyId() {
		return this.supplyId;
	}

	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}

	public Integer getIshave() {
		return this.ishave;
	}

	public void setIshave(Integer ishave) {
		this.ishave = ishave;
	}

	public Integer getInformationId() {
		return this.informationId;
	}

	public void setInformationId(Integer informationId) {
		this.informationId = informationId;
	}

	public Integer getRelevanceId() {
		return this.relevanceId;
	}

	public void setRelevanceId(Integer relevanceId) {
		this.relevanceId = relevanceId;
	}

	public List<SupplyAndDemand> getList_supply() {
		return list_supply;
	}

	public void setList_supply(List<SupplyAndDemand> list_supply) {
		this.list_supply = list_supply;
	}

	public List<Information> getListInformation() {
		return listInformation;
	}

	public void setListInformation(List<Information> listInformation) {
		this.listInformation = listInformation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}