package com.gre.in.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gre.common.HDB;
import com.gre.common.PageUtil;
import com.gre.common.model.FeedBack;
import com.gre.common.model.Img;
import com.gre.common.model.UpdateInfo;

@Component("queryService")
public class QueryService {

	@Autowired
	private HDB hdb;

	/**
	 * 查询所有的新闻信息
	 * @param pageNow
	 * @param limit
	 * @return
	 */
	public PageUtil findAllList(Class clazz,int pageNow, Integer limit) {
		// TODO Auto-generated method stub
		return hdb.findAll(clazz,pageNow,limit);
	}

	/**
	 * 根据指定id查找对象
	 * @param newsId
	 */
	public Object findObjectById(Class clazz,int id) {
		// TODO Auto-generated method stub
		return hdb.find(clazz, id);
	}

	/**
	 * 查询产品根据对应的类型查询
	 * @param pageNow
	 * @param limit
	 * @param typeId
	 * @return
	 */
	public PageUtil findAllProduct(int pageNow, Integer limit, String typeId) {
		// TODO Auto-generated method stub
		String hql = "from Product p where 1 = 1";
		if(null != typeId && !"".equals(typeId)){
			hql += " and p.type = "+typeId;
		}
		return hdb.findHql(hql, pageNow, limit);
	}

	
	public List<?> findAllListByObject(Class clazz) {
		// TODO Auto-generated method stub
		return hdb.findAll(clazz);
	}

	/**
	 * 查询图片list
	 * @param parseInt
	 * @return
	 */
	public List<Img> findImgList(int id,Integer type) {
		// TODO Auto-generated method stub
		String hql = "from Img i where i.parentId = "+ id + " and i.type = "+type;
		return (List<Img>) hdb.findHql(hql);
	}

	/**
	 * 保存或更新对象
	 * @param obj
	 */
	public void saveOrUpdateObject(Object obj) {
		// TODO Auto-generated method stub
		hdb.saveOrUpdate(obj);
	}

	/**
	 * 查询版本信息
	 * @return
	 */
	public UpdateInfo findUpdateInfo(Integer platForm) {
		// TODO Auto-generated method stub
		String hql = "from UpdateInfo ui where ui.platForm="+platForm;
		return (UpdateInfo) hdb.findUniqueHql(hql);
	}

	/**
	 * 查询更新信息
	 * @param version
	 * @return
	 */
	public UpdateInfo findUpdateInfo(String version,Integer platForm) {
		// TODO Auto-generated method stub
		String hql = "from UpdateInfo ui where ui.version ='"+version+"' and ui.platForm="+platForm;
		return (UpdateInfo) hdb.findUniqueHql(hql);
	}
	
}
