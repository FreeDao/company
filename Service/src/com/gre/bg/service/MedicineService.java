package com.gre.bg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gre.common.HDB;
import com.gre.common.PageUtil;
import com.gre.common.model.Img;

@Component("bgMedicineService")
public class MedicineService {

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
	public void delObj(Img i) {
		// TODO Auto-generated method stub
		hdb.delete(i);
	}
}
