package com.gre.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gre.common.HDB;
import com.gre.common.PageUtil;

@Component("queryService")
public class QueryService {

	@Autowired
	private HDB hdb;

	/**
	 * 根据对象查询list
	 * @param class1
	 * @param pageNow
	 * @param limit
	 * @return
	 */
	public PageUtil findAllListByObject(Class clazz, int pageNow,
			Integer limit) {
		// TODO Auto-generated method stub
		return hdb.findAll(clazz, pageNow, limit);
	}

	/**
	 * 根据对象查询list
	 * @param class1
	 * @return
	 */
	public List<?> findAllListByObject(Class clazz) {
		// TODO Auto-generated method stub
		return hdb.findAll(clazz);
	}

	/**
	 * 查询所有招标信息
	 * @param type
	 * @param pageNow
	 * @param limit
	 * @return
	 */
	public PageUtil findInvitationList(String type, int pageNow, Integer limit) {
		// TODO Auto-generated method stub
		String hql = "from Invitation i where 1=1 ";
		if(null != type){
			hql += " and i.type ="+Integer.parseInt(type);
		}
		return hdb.findHql(hql, pageNow, limit);
	}

	/**
	 * 查询医药商机list
	 * @param class1
	 * @param pageNow
	 * @param limit
	 * @return
	 */
	public PageUtil findBusinessList(String type, int pageNow,
			Integer limit) {
		// TODO Auto-generated method stub
		String hql = "from Business b where 1=1 ";
		if(null != type){
			hql += " and b.type ="+Integer.parseInt(type);
		}
		return hdb.findHql(hql, pageNow, limit);
	}
	
	
}
