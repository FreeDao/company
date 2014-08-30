package com.gre.bg.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.bg.service.LawService;
import com.gre.common.BaseAction;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.Law;

@Component("lawAction")
@Scope("prototype")
public class LawAction extends BaseAction{

	@Autowired
	private LawService lawService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private Law law;
	
	/**
	 * 查询所有法律法规信息
	 * @return
	 */
	public String index(){
		try {
			pu = lawService.findAllList(Law.class,pageNow,limit);
			pu.setUrl("bg/law!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}

	/**
	 * 删除指定法律法规信息
	 * @return
	 */
	public String delLaw(){
		try {
			lawService.delObjById(Law.class,law.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("LawAction中方法delLaw出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != law ){
				law = (Law) lawService.findObjById(Law.class,law.getId());
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
			if( null != law.getId() && !"".equals(law.getId()) ){
				Law c = (Law) lawService.findObjById(Law.class, law.getId());
				law.setAddTime(c.getAddTime());
			}else{
				law.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			}
			lawService.saveOrUpdateObj(law);
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("LawAction中方法addOrEdit出错");
			return index();
		}
	}

	public Law getLaw() {
		return law;
	}

	public void setLaw(Law law) {
		this.law = law;
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
