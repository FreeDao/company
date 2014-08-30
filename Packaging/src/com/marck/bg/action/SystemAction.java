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
import com.marck.common.model.SystemAgrs;
import com.marck.common.model.Type;

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
	private SystemAgrs sa;
	private Type type;
	
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
			if( CommonUtil.validParams(menu) ){
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
			if(systemService.addOrUpdateMenu(menu)){
				menu = null;
				request.setAttribute("msg", "添加成功，是否继续添加？");
			}else{
				request.setAttribute("msg", "修改成功，是否继续修改？");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添加或者修改菜单出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return goAddOrUpdateMenu();
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
	
	/**
	 * 模版初始化页面
	 * @return
	 */
	public String systemInit(){
		try {
			sa = systemService.selSystemAgrs();
			return "systemInit";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("跳转初始化页面错误！", e);
		}
		return ERROR;
	}
	
	/**
	 * 模版初始化保存
	 * @return
	 */
	public String systemInitSave(){
		try {
			systemService.saveSystemAgrs(sa);
			request.setAttribute("msg", "保存成功!");
			return systemInit();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("初始化信息错误！", e);
		}
		return ERROR;
	}
	
	/**
	 * 查询类型列表
	 * @return
	 */
	public String selTypeList(){
		try {
			pu = systemService.getTypeList(queryValue,pageNow,limit);
			String url = request.getRequestURI();
			request.getSession().setAttribute("url", url.substring(url.indexOf("/",1)+1)+"?");
			return "selTypeList";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询类型列表出错！",e);
		}
		return ERROR;
	}
	
	/**
	 * 跳转到类型添加修改添加修改页面
	 * @return
	 */
	public String goAddOrUpdateType(){
		try {
			if( CommonUtil.validParams(type) ){
				request.setAttribute("title", "添加类型");
			}else{
				type = systemService.getType(type);
				request.setAttribute("title", "修改类型");
			}
			List<Type> types = systemService.getTypes(1);
			request.setAttribute("types", types);
			return "goAddOrUpdateType";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添砖到类型添加修改页面出错！",e);
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改类型
	 * @return
	 */
	public String addOrUpdateType(){
		try {
			if(systemService.addOrUpdateType(type)){
				type = null;
				request.setAttribute("msg", "添加成功，是否继续添加？");
			}else{
				request.setAttribute("msg", "修改成功，是否继续修改？");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添加或者修改类型出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return goAddOrUpdateType();
	}
	
	/**
	 * 删除类型
	 * @return
	 */
	public String delType(){
		try {
			systemService.delType(type);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除类型出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return selTypeList();
	}
	
	public SystemAgrs getSa() {
		return sa;
	
	}

	public void setSa(SystemAgrs sa) {
		this.sa = sa;
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

	public Type getType() {
		return type;
	
	}

	public void setType(Type type) {
		this.type = type;
	}

}
