package com.marck.bg.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.bg.service.SystemService;
import com.marck.common.BaseAction;
import com.marck.common.CommonUtil;
import com.marck.common.PageUtil;
import com.marck.common.model.Menu;

@Component("systemAction")
@Scope("prototype")
public class SystemAction extends BaseAction{

	@Autowired
	private SystemService systemService;
	
	private PageUtil pu = new PageUtil();
	private Integer pageNow;
	private Integer limit;
	
	private String queryValue;
	
	
	
	private Menu menu;
	
	/**
	 * 查询菜单列表
	 * @return
	 */
	public String selMenuList(){
		try {
			pu = systemService.getMenuList(queryValue,pageNow,limit);
			String url = request.getRequestURI();
			request.getSession().setAttribute("url", url.substring(url.indexOf("/",1)+1)+"?");
			return "selMenuList";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询菜单列表出错！",e);
		}
		return ERROR;
	}

	/**
	 * 跳转到菜单添加修改页面
	 * @return
	 */
	public String goAddOrUpdateMenu(){
		try {
			if( null == menu ){
				request.setAttribute("title", "添加菜单");
			}else{
				menu = systemService.getMenu(menu);
				request.setAttribute("title", "修改菜单");
			}
			List<Menu> menus = systemService.getAllMenuList();
			List<com.marck.common.model.List> list = systemService.getAllList();
			request.setAttribute("list", list);
			request.setAttribute("menus", menus);
			return "goAddOrUpdateMenu";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添砖到菜单添加修改页面出错！",e);
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改菜单
	 * @return
	 */
	public String addOrUpdateMenu(){
		try {
			systemService.addOrUpdateMenu(menu);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添加或者修改菜单出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return selMenuList();
	}
	
	/**
	 * 删除菜单
	 * @return
	 */
	public String delMenu(){
		try {
			systemService.delMenu(menu);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除菜单出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return selMenuList();
	}
	
	/**
	 * 查询列表类型
	 * @return
	 */
	public String selListType(){
		try {
			pu = systemService.getListType(pageNow,limit);
			pu.setUrl("bg/system!selListType");
			return "selListType";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询列表类型错误！", e);
		}
		return ERROR;
	}
	
	
	
	public Integer getPageNow() {
		return pageNow;//null == pageNow?"":pageNow;
	
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getLimit() {
		return limit;//null == limit?"":limit;
	
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public PageUtil getPu() {
		return pu;//null == pu?"":pu;
	
	}

	public void setPu(PageUtil pu) {
		this.pu = pu;
	}

	public String getQueryValue() {
		return queryValue;//null == queryValue?"":queryValue;
	
	}

	public void setQueryValue(String queryValue) {
		this.queryValue = CommonUtil.changeIos8859ToUtf8(queryValue);
	}

	public Menu getMenu() {
		return menu;//null == menu?"":menu;
	
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
