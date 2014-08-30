package com.marck.bg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marck.common.CommonUtil;
import com.marck.common.PageUtil;
import com.marck.common.dao.HDB;
import com.marck.common.model.Menu;
import com.marck.common.model.SystemAgrs;
import com.marck.common.model.Type;

@Component("systemService")
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class SystemService {
	
	@Autowired
	private HDB hdb;

	/**
	 * 查询菜单列表
	 * @param queryValue 
	 * @param queryCondition 
	 * @return
	 */
	public PageUtil getMenuList(String queryValue, Integer pageNow,Integer limit) {
		// TODO Auto-generated method stub
		String hql = "select m,(select t.name from Menu t where t.id=m.pid ) from Menu m where 1=1 ";
		if( null != queryValue && !"".equals(queryValue) ){
			/*if("pname".equals(queryCondition)){
				String temp_hql = "from Menu m where m.name like '%"+queryValue+"%'";
				List<Menu> list = (List<Menu>) hdb.findHql(temp_hql);
				if(list.size() > 0){
					hql +=" and m.pid = "+list.get(0).getId();
				}
			}else{
				
			}*/
			hql += " and m.id like '%"+queryValue+"%' or m.url like '%"+queryValue+"%' or m.name like '%"+queryValue+"%' or m.level like '%"+queryValue+"%' or m.sort like '%"+queryValue+"%' ";
		}
		return hdb.findHql(hql, pageNow, limit);
	}

	/**
	 * 查询列表类型
	 * @param pageNow
	 * @param limit
	 * @return
	 */
	public PageUtil getListType(Integer pageNow, Integer limit) {
		// TODO Auto-generated method stub
		String hql = "from List l";
		return hdb.findHql(hql, pageNow, limit);
	}

	/**
	 * 查询所有菜单
	 * @return
	 */
	public List<Menu> getAllMenuList() {
		// TODO Auto-generated method stub
		return (List<Menu>) hdb.findAll(Menu.class);
	}

	/**
	 * 根据ID查询制定菜单
	 * @param menu
	 * @return
	 */
	public Menu getMenu(Menu menu) {
		// TODO Auto-generated method stub
		return (Menu) hdb.find(Menu.class, menu.getId());
	}

	/**
	 * 获取所有的列表类型
	 * @return
	 */
	public List<com.marck.common.model.List> getAllList() {
		// TODO Auto-generated method stub
		return (List<com.marck.common.model.List>) hdb.findAll(com.marck.common.model.List.class);
	}

	/**
	 * 添加或更新菜单
	 * @param menu
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public boolean addOrUpdateMenu(Menu menu) {
		// TODO Auto-generated method stub
		boolean isNew = false;
		if(CommonUtil.validParams(menu.getId())){
			isNew = true;
		}
		hdb.saveOrUpdate(menu);
		if(!CommonUtil.validParams(menu.getListId())){
			com.marck.common.model.List l = (com.marck.common.model.List) hdb.find(com.marck.common.model.List.class, menu.getListId());
			menu.setUrl(l.getUrl()+"?menuId="+menu.getId());
			hdb.saveOrUpdate(menu);
		}
		return isNew;
	}

	/**
	 * 删除菜单
	 * @param menu
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void delMenu(Menu menu) {
		// TODO Auto-generated method stub
		String hql = "from Menu m where m.pid="+menu.getId();
		List<Menu> menus = (List<Menu>) hdb.findHql(hql);
		for(Menu m : menus){
			hdb.delete(m);
		}
		hdb.delete(menu);
	}

	/**
	 * 查询系统参数
	 * @return
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public SystemAgrs selSystemAgrs() {
		// TODO Auto-generated method stub
		SystemAgrs sa = (SystemAgrs) hdb.findUniqueHql("from SystemAgrs");
		if( null == sa ){
			sa = new SystemAgrs();
			sa.setModel(1);
			hdb.saveOrUpdate(sa);
		}
		return sa;
	}

	/**
	 * 保存系统参数
	 * @param sa
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void saveSystemAgrs(SystemAgrs sa) {
		// TODO Auto-generated method stub
		hdb.saveOrUpdate(sa);
	}

	/**
	 * 查询类型列表
	 * @param queryValue
	 * @param pageNow
	 * @param limit
	 * @return
	 */
	public PageUtil getTypeList(String queryValue, Integer pageNow,
			Integer limit) {
		// TODO Auto-generated method stub
		String hql = "select t,(select tt.name from Type tt where tt.id=t.pid ) from Type t where 1=1 ";
		if( null != queryValue && !"".equals(queryValue) ){
			hql += " and t.id like '%"+queryValue+"%' or t.name like '%"+queryValue+"%' or t.level like '%"+queryValue+"%'";
			
			if(queryValue.equals("基本")){
				hql += " or t.type = 1";
			}
			if(queryValue.equals("尺码")){
				hql += " or t.type = 2";	
			}
			if(queryValue.equals("颜色")){
				hql += " or t.type = 3";
			}
			
		}
		
		return hdb.findHql(hql, pageNow, limit);
	}

	/**
	 * 获取类型
	 * @param type
	 * @return
	 */
	public Type getType(Type type) {
		// TODO Auto-generated method stub
		return (Type) hdb.find(Type.class, type.getId());
	}

	/**
	 * 获取类型
	 * @param i
	 * @return
	 */
	public List<Type> getTypes(Integer i) {
		// TODO Auto-generated method stub
		return (List<Type>) hdb.findHql("from Type t where t.type = "+i);
	}

	/**
	 * 添加货修改类型
	 * @param type
	 * @return
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public boolean addOrUpdateType(Type type) {
		// TODO Auto-generated method stub
		boolean isNew = false;
		if(CommonUtil.validParams(type.getId())){
			isNew = true;
		}
		if(type.getType() != 1){
			type.setLevel(1);
			type.setPid(0);
		}
		
		if(type.getLevel() == 1){
			type.setPid(0);
		}
		
		hdb.saveOrUpdate(type);
		return isNew;
	}

	/**
	 * 
	 * @param type
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void delType(Type type) {
		// TODO Auto-generated method stub
		String hql = "from Type t where t.pid="+type.getId();
		List<Type> types = (List<Type>) hdb.findHql(hql);
		for(Type t : types){
			hdb.delete(t);
		}
		hdb.delete(type);
	}
	
	
}
