package com.marck.bg.action;


import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.bg.service.InfoService;
import com.marck.common.BaseAction;
import com.marck.common.CommonUtil;
import com.marck.common.PageUtil;
import com.marck.common.model.Info;
import com.marck.common.model.Menu;
import com.marck.common.model.User;

@Component("infoAction")
@Scope("prototype")
public class InfoAction extends BaseAction {

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private String queryValue;
	
	private Info info;
	
	@Autowired
	private InfoService infoService;

	private File img;
	private String imgContentType;
	private String imgFileName;
	
	/**
	 * 查询资讯
	 * @return
	 */
	public String selInfoList(){
		try{
			String resUrl = request.getRequestURI();
			Integer menuId = CommonUtil.validParams(request.getParameter("menuId")) ? (Integer)request.getSession().getAttribute("menuId"): Integer.parseInt(request.getParameter("menuId"));
			request.getSession().setAttribute("url", resUrl.substring(resUrl.indexOf("/",1)+1)+"?");
			request.getSession().setAttribute("menuId", menuId);
			
			pu = infoService.getInfoList(menuId,queryValue,pageNow,limit);
			
			return "selInfoList";
		}catch (Exception e) {
			// TODO: handle exception
			log.error("查询资讯列表出错！",e);
		}
		return ERROR;
	}

	/**
	 * 跳转到菜单添加修改页面
	 * @return
	 */
	public String goAddOrUpdateInfo(){
		try {
			if( CommonUtil.validParams(info) ){
				request.setAttribute("title", "添加资讯");
			}else{
				info = infoService.getInfo(info);
				request.setAttribute("title", "修改资讯");
			}
			/*List<Menu> menus = infoService.getAllMenuList();
			List<com.marck.common.model.List> list = systemService.getAllList();
			request.setAttribute("list", list);
			request.setAttribute("menus", menus);*/
			return "goAddOrUpdateInfo";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添砖到资讯添加修改页面出错！",e);
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改资讯
	 * @return
	 */
	public String addOrUpdateInfo(){
		try {
			
			String filepath=request.getRealPath("/"+uploadFolder);
			Integer menuId = (Integer) request.getSession().getAttribute("menuId");
			
			if(infoService.addOrUpdateInfo(info,menuId,img,imgContentType,imgFileName,filepath,projectUrl,uploadFolder)){
				info = null;
				request.setAttribute("msg", "添加成功，是否继续添加？");
			}else{
				request.setAttribute("msg", "修改成功，是否继续修改？");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添加或者修改资讯出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return goAddOrUpdateInfo();
	}
	
	/**
	 * 删除资讯
	 * @return
	 */
	public String delInfo(){
		try {
			infoService.delInfo(info);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除资讯出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return selInfoList();
	}
	
	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
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

	public String getQueryValue() {
		return queryValue;
	
	}

	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}

	public File getImg() {
		return img;
	
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgContentType() {
		return imgContentType;
	
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	public String getImgFileName() {
		return imgFileName;
	
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	
	
}
