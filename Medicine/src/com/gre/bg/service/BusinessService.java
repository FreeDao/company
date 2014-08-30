package com.gre.bg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gre.common.HDB;
import com.gre.common.PageUtil;
import com.gre.common.model.Type;

@Component("businessService")
public class BusinessService {

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
	 * 根据hql查询
	 * @param pageNow
	 * @param limit
	 * @return
	 */
	public PageUtil findBusinessAllList(Integer pageNow, Integer limit) {
		// TODO Auto-generated method stub
		String hql = "from Business b,Type t where t.id=b.type";
		return hdb.findHql(hql, pageNow, limit);
	}

	/**
	 * 查询所有对象
	 * @param class1
	 * @return
	 */
	public List<?> findAllList(Class clazz) {
		// TODO Auto-generated method stub
		return hdb.findAll(clazz);
	}

}
