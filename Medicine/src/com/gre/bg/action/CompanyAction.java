package com.gre.bg.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.bg.service.CompanyService;
import com.gre.common.BaseAction;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.Company;

@Component("companyAction")
@Scope("prototype")
public class CompanyAction extends BaseAction{

	@Autowired
	private CompanyService companyService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private Company company;
	
	/**
	 * 查询所有企业信息
	 * @return
	 */
	public String index(){
		try {
			pu = companyService.findAllList(Company.class,pageNow,limit);
			pu.setUrl("bg/company!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}

	/**
	 * 删除指定企业信息
	 * @return
	 */
	public String delCompany(){
		try {
			companyService.delObjById(Company.class,company.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("CompanyAction中方法delCompany出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != company ){
				company = (Company) companyService.findObjById(Company.class,company.getId());
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
			if( null != company.getId() && !"".equals(company.getId()) ){
				Company c = (Company) companyService.findObjById(Company.class, company.getId());
				company.setAddTime(c.getAddTime());
			}else{
				company.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			}
			companyService.saveOrUpdateObj(company);
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("CompanyAction中方法addOrEdit出错");
			return index();
		}
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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
