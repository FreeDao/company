package com.marck.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="version")
public class Version {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String platForm;
	@Column
	private String version;
	@Column
	private String url;
	@Column
	private Integer isForced;
	@Column
	private String content;
	
	public Integer getId() {
		return null == id ? 0 : id;
	
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlatForm() {
		return null == platForm ? "" : platForm;
	
	}
	public void setPlatForm(String platForm) {
		this.platForm = platForm;
	}
	public String getVersion() {
		return null == version ? "" : version;
	
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUrl() {
		return null == url ? "" : url;
	
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIsForced() {
		return null == isForced ? 0 : isForced;
	
	}
	public void setIsForced(Integer isForced) {
		this.isForced = isForced;
	}
	public String getContent() {
		return null == content ? "" : content;
	
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
