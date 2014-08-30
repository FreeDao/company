package com.gre.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="updateinfo")
public class UpdateInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String version;
	@Column
	private String url;
	@Column
	private Integer isForced;
	@Column
	private String content;
	@Column
	private Integer platForm;
	
	public Integer getPlatForm() {
		return platForm;
	}
	public void setPlatForm(Integer platForm) {
		this.platForm = platForm;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIsForced() {
		return isForced;
	}
	public void setIsForced(Integer isForced) {
		this.isForced = isForced;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
