package com.marck.bg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marck.common.PageUtil;
import com.marck.common.dao.HDB;
import com.marck.common.model.Menu;

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
	public void addOrUpdateMenu(Menu menu) {
		// TODO Auto-generated method stub
		hdb.saveOrUpdate(menu);
	}

	/**
	 * 删除菜单
	 * @param menu
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void delMenu(Menu menu) {
		// TODO Auto-generated method stub
		hdb.delete(menu);
	}
	
	
}
