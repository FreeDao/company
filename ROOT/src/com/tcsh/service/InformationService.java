package com.tcsh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcsh.common.PageUtil;
import com.tcsh.dao.HDB;
import com.tcsh.model.local.Img;
import com.tcsh.model.local.Information;
import com.tcsh.model.local.Top;


@Component("informationService")
public class InformationService {

	@Autowired
	private HDB hdb;

	/**
	 * 根据对象查询所有list
	 * @param class1
	 * @return
	 */
	public PageUtil findAllList(Class clazz,Integer pageNow,Integer limit) {
		// TODO Auto-generated method stub
		return hdb.findAll(clazz, pageNow, limit);
	}

	/**
	 * 根据id删除对象
	 * @param class1
	 * @param newsId
	 */
	public void delObjById(Class clazz, Integer id) {
		// TODO Auto-generated method stub
		hdb.delete(clazz, id);
	}

	/**
	 * 保存对象
	 * @param n
	 */
	public void saveOrUpdateObj(Object obj) {
		// TODO Auto-generated method stub
		hdb.saveOrUpdate(obj);
	}

	/**
	 * 根据id查询对象
	 * @param parseInt
	 * @return 
	 */
	public Object findObjById(Class clazz,Integer id) {
		// TODO Auto-generated method stub
		return hdb.find(clazz, id);
	}

	/**
	 * 根据pid查询img对象list
	 * @param id
	 * @return
	 */
	public List<Img> findAllImgByPid(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from Img i where i.parentId = "+id;
		return (List<Img>) hdb.findHql(hql);
	}

	/**
	 * 删除对象
	 * @param i
	 */
	public void delObj(Object obj) {
		// TODO Auto-generated method stub
		hdb.delete(obj);
	}

	/**
	 * 查询information所有数据
	 * @param class1
	 * @param pageNow
	 * @param limit
	 * @return
	 */
	public PageUtil findInformationList(Integer pageNow, Integer limit) {
		// TODO Auto-generated method stub
		String sql = "select i.*,(SELECT count(*) from top t where t.parentId = i.id and t.type = 0) isTop from information i ORDER BY isTop desc,i.addTime desc";
		return hdb.findSql(sql, pageNow, limit);
	}

	
	public List<Top> findTop(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from Top t where t.parentId = "+id+" and t.type = 0";
		return (List<Top>) hdb.findHql(hql);
	}

}
