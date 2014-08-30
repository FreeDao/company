package com.marck.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="systemagrs")
public class SystemAgrs {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private Integer model;
	@Column
	private String type ;
	@Column
	private String typeName ;
	
	public String getTypeName() {
		return null == typeName ? "" : typeName;
	
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getType() {
		return null == type ? "" : type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getId() {
		return null == id ? 0 : id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getModel() {
		return null == model ? 0 : model;
	}
	public void setModel(Integer model) {
		this.model = model;
	}
	
	
}
