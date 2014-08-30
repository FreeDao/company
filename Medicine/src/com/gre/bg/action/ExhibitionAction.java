package com.gre.bg.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.bg.service.CompanyService;
import com.gre.bg.service.ExhibitionService;
import com.gre.common.BaseAction;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.Company;
import com.gre.common.model.Exhibition;

@Component("exhibitionAction")
@Scope("prototype")
public class ExhibitionAction extends BaseAction{

	@Autowired
	private ExhibitionService exhibitionService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private Exhibition exhibition;
	
	/**
	 * 查询所有商业信息
	 * @return
	 */
	public String index(){
		try {
			pu = exhibitionService.findAllList(Exhibition.class,pageNow,limit);
			pu.setUrl("bg/exhibition!index");
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
	public String delExhibition(){
		try {
			exhibitionService.delObjById(Exhibition.class,exhibition.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("ExhibitionAction中方法delExhibition出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != exhibition ){
				exhibition = (Exhibition) exhibitionService.findObjById(Exhibition.class,exhibition.getId());
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
			exhibitionService.saveOrUpdateObj(exhibition);
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("CompanyAction中方法addOrEdit出错");
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

	public Exhibition getExhibition() {
		return exhibition;
	}

	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
	}
	
}
