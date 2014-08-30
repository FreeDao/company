package com.marck.bg.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.marck.common.model.User;

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
	private User user;
	
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
			
			SystemAgrs systemAgrs = (SystemAgrs) request.getSession().getAttribute("systemAgrs");
			String typeTemp = systemAgrs.getType();
			String typeNameTemp = systemAgrs.getTypeName();
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			
			Map<String, Object> base = new HashMap<String, Object>();
			base.put("type", 1 );
			base.put("name","基本");
			list.add(base);
			
			if(!CommonUtil.validParams(typeTemp,typeNameTemp)){
				String[] temp = typeTemp.split(",");
				String[] nameTemp = typeNameTemp.split(",");
				for(int i = 0 ; i < temp.length ; i++){
					Map<String, Object> map = new HashMap<String, Object>(); 
					map.put("type",temp[i] );
					map.put("name",nameTemp[i] );
					list.add(map);
				}
			}
			
			pu = systemService.getTypeList(queryValue,list,pageNow,limit);
			String url = request.getRequestURI();
			request.getSession().setAttribute("url", url.substring(url.indexOf("/",1)+1)+"?");
			request.setAttribute("list", list);
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
			SystemAgrs systemAgrs = (SystemAgrs) request.getSession().getAttribute("systemAgrs");
			String typeTemp = systemAgrs.getType();
			String typeNameTemp = systemAgrs.getTypeName();
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			
			Map<String, Object> base = new HashMap<String, Object>();
			base.put("type", 1 );
			base.put("name","基本");
			list.add(base);
			
			if(!CommonUtil.validParams(typeTemp,typeNameTemp)){
				String[] temp = typeTemp.split(",");
				String[] nameTemp = typeNameTemp.split(",");
				for(int i = 0 ; i < temp.length ; i++){
					Map<String, Object> map = new HashMap<String, Object>(); 
					map.put("type",temp[i] );
					map.put("name",nameTemp[i] );
					list.add(map);
				}
			}
			
			if( CommonUtil.validParams(type) ){
				request.setAttribute("title", "添加类型");
			}else{
				type = systemService.getType(type);
				request.setAttribute("title", "修改类型");
			}
			List<Type> types = systemService.getTypes(1);
			request.setAttribute("types", types);
			request.setAttribute("list", list);
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

	/**
	 * 查询账户列表
	 * @return
	 */
	public String selAccountList(){
		try {
			pu = systemService.getAccountList(queryValue,pageNow,limit);
			String url = request.getRequestURI();
			request.getSession().setAttribute("url", url.substring(url.indexOf("/",1)+1)+"?");
			return "selAccountList";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询账户列表出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return ERROR;
	}
	
	/**
	 * 跳转添加修改账户页面
	 * @return
	 */
	public String goAddOrUpdateAccount(){
		try {
			if( CommonUtil.validParams(user) ){
				request.setAttribute("title", "添加账户");
			}else{
				user = systemService.getUser(user);
				request.setAttribute("title", "修改账户");
			}
			return "goAddOrUpdateAccount";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("跳转用户列表出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return ERROR;
	}
	
	/**
	 * 添加修改账户信息
	 * @return
	 */
	public String addOrUpdateAccount(){
		try {
			if(systemService.addOrUpdateUser(user)){
				user = null;
				request.setAttribute("msg", "添加成功，是否继续添加？");
			}else{
				request.setAttribute("msg", "修改成功，是否继续修改？");
			}
			return goAddOrUpdateAccount();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添加修改账户信息出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return ERROR;
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	public String delAccount(){
		try {
			systemService.delAccount(user);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除用户出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return selAccountList();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
