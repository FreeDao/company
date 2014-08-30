package com.tcsh.model.local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="common")
public class Common {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String swdb;
	@Column
	private String cbgb;
	@Column
	private String ywjl;
	@Column
	private String xsgcs;
	@Column
	private String swjl;
	@Column
	private String pxjs;
	@Column
	private String gsjj;
	@Column
	private String cp;
	@Column
	private String cpjj;
	@Column
	private String cpjz;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSwdb() {
		return swdb;
	}
	public void setSwdb(String swdb) {
		this.swdb = swdb;
	}
	public String getCbgb() {
		return cbgb;
	}
	public void setCbgb(String cbgb) {
		this.cbgb = cbgb;
	}
	public String getYwjl() {
		return ywjl;
	}
	public void setYwjl(String ywjl) {
		this.ywjl = ywjl;
	}
	public String getXsgcs() {
		return xsgcs;
	}
	public void setXsgcs(String xsgcs) {
		this.xsgcs = xsgcs;
	}
	public String getSwjl() {
		return swjl;
	}
	public void setSwjl(String swjl) {
		this.swjl = swjl;
	}
	public String getPxjs() {
		return pxjs;
	}
	public void setPxjs(String pxjs) {
		this.pxjs = pxjs;
	}
	public String getGsjj() {
		return gsjj;
	}
	public void setGsjj(String gsjj) {
		this.gsjj = gsjj;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getCpjj() {
		return cpjj;
	}
	public void setCpjj(String cpjj) {
		this.cpjj = cpjj;
	}
	public String getCpjz() {
		return cpjz;
	}
	public void setCpjz(String cpjz) {
		this.cpjz = cpjz;
	}
	
}
