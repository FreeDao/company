package com.gre.bg.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.bg.service.PharmacyService;
import com.gre.common.BaseAction;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.Pharmacy;

@Component("pharmacyAction")
@Scope("prototype")
public class PharmacyAction extends BaseAction{

	@Autowired
	private PharmacyService pharmacyService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private Pharmacy pharmacy;
	
	/**
	 * 查询所有药房动态信息
	 * @return
	 */
	public String index(){
		try {
			pu = pharmacyService.findAllList(Pharmacy.class,pageNow,limit);
			pu.setUrl("bg/pharmacy!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}

	/**
	 * 删除指定药房动态信息
	 * @return
	 */
	public String delPharmacy(){
		try {
			pharmacyService.delObjById(Pharmacy.class,pharmacy.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("PharmacyAction中方法delPharmacy出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != pharmacy ){
				pharmacy = (Pharmacy) pharmacyService.findObjById(Pharmacy.class,pharmacy.getId());
			}
			return "addOrEdit";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改信息
	 * @return
	 */
	public String addOrEdit(){
		try {
			if( null != pharmacy.getId() && !"".equals(pharmacy.getId()) ){
				Pharmacy c = (Pharmacy) pharmacyService.findObjById(Pharmacy.class, pharmacy.getId());
				pharmacy.setAddTime(c.getAddTime());
			}else{
				pharmacy.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			}
			pharmacyService.saveOrUpdateObj(pharmacy);
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("PharmacyAction中方法addOrEdit出错");
			return index();
		}
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public PageUtil getPu() {
		return pu;
	}

	public void setPu(PageUtil pu) {
		this.pu = pu;
	}

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
}
