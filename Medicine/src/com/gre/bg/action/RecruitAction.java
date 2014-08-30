package com.gre.bg.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.bg.service.RecruitService;
import com.gre.common.BaseAction;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.Recruit;

@Component("recruitAction")
@Scope("prototype")
public class RecruitAction extends BaseAction{

	@Autowired
	private RecruitService recruitService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private Recruit recruit;
	
	/**
	 * 查询所有商业信息
	 * @return
	 */
	public String index(){
		try {
			pu = recruitService.findAllList(Recruit.class,pageNow,limit);
			pu.setUrl("bg/recruit!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}

	/**
	 * 删除指定商业信息
	 * @return
	 */
	public String delRecruit(){
		try {
			recruitService.delObjById(Recruit.class,recruit.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("RecruitAction中方法delRecruit出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != recruit ){
				recruit = (Recruit) recruitService.findObjById(Recruit.class,recruit.getId());
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
			if( null != recruit.getId() && !"".equals(recruit.getId()) ){
				Recruit i = (Recruit) recruitService.findObjById(Recruit.class, recruit.getId());
				recruit.setAddTime(i.getAddTime());
			}else{
				recruit.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			}
			recruitService.saveOrUpdateObj(recruit);
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("RecruitAction中方法addOrEdit出错");
			return index();
		}
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

	public Recruit getRecruit() {
		return recruit;
	}

	public void setRecruit(Recruit recruit) {
		this.recruit = recruit;
	}
	
}
