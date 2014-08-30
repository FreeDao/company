package com.marck.common.dao;


import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.marck.common.CommonUtil;
import com.marck.common.PageUtil;

@Component("hdb")
public class HDB {

	private Session session;
	@Autowired
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	private void init() {
		session = sessionFactory.getCurrentSession();
	}

	public void merge(Object obj) {
		try {
			init();
			session.merge(obj);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	public void saveOrUpdate(Object obj) {
		try {
			init();
			session.saveOrUpdate(obj);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	public void delete(Object obj) {
		try {
			init();
			session.delete(obj);
		} catch (HibernateException e) {
			e.printStackTrace();;
		} finally {
		}
	}
	
	public void delete(Class clazz,Integer id) {
		try {
			init();
			session.createQuery("delete from "+clazz.getName()+" where id="+id).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();;
		} finally {
		}
	}

	public Object find(Class clazz, Serializable id) {
		Object obj = null;
		try {
			init();
			obj = session.get(clazz, id);
			if (!Hibernate.isInitialized(clazz)) {
				Hibernate.initialize(clazz);
			}
		} catch (HibernateException e) {
			e.printStackTrace();;
		} finally {
		}
		return obj;
	}


	public List<?> findAll(Class clazz) {
		List<?> objects = null;
		try {
			init();
			Query query = session.createQuery("from " + clazz.getName());
			objects = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
		}
		return objects;
	}
	
	public List<?> findAllList(Class clazz,Object pageNow,Object limit){
		List<?> objects = null;
		Integer tempPageNow = 1,tempLimit = PageUtil.globalLimit;
		if(null != pageNow){
			if( pageNow instanceof String ){
				tempPageNow = Integer.parseInt((String) pageNow);
			}else{
				tempPageNow = (Integer) pageNow;
			}
		}
		if(null != limit){
			if( limit instanceof String ){
				tempLimit = Integer.parseInt((String) limit);
			}else{
				tempLimit = (Integer) limit;
			}
		}
		try {
			init();
			Query query = session.createQuery("from " + clazz.getName());
			query.setFirstResult((tempPageNow-1)*tempLimit).setMaxResults(tempLimit);
			objects = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();;
		} finally {
		}
		return objects;
	}
	
	public PageUtil findAll(Class clazz,Object pageNow,Object limit){
		PageUtil pu = new PageUtil() ;
		Integer tempPageNow = 1,tempLimit = PageUtil.globalLimit;
		if(null != pageNow){
			if( pageNow instanceof String ){
				tempPageNow = Integer.parseInt((String) pageNow);
			}else{
				tempPageNow = (Integer) pageNow;
			}
		}
		if(null != limit){
			if( limit instanceof String ){
				tempLimit = Integer.parseInt((String) limit);
			}else{
				tempLimit = (Integer) limit;
			}
		}
		try {
			init();
			Query query = session.createQuery("from " + clazz.getName());
			pu = new PageUtil(query.list().size(), tempPageNow,tempLimit);
			query.setFirstResult((tempPageNow-1)*tempLimit).setMaxResults(tempLimit);
			pu.setData(query.list());
		} catch (HibernateException e) {
			e.printStackTrace();;
		} finally {
		}
		return pu;
	}

	public Object findUniqueHql(String hql){
		try {
			init();
			Query query = session.createQuery(hql);
			return query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();;
		} finally {
		}
		return null;
	}
	
	public Object findUniqueSql(String sql){
		try {
			init();
			Query query = session.createSQLQuery(sql);
			return query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}
	
	public List<?> findHql(String hql){
		List<?> objects = null;
		try {
			init();
			Query query = session.createQuery(hql);
			objects = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();;
		} finally {
		}
		return objects;
	}
	
	public PageUtil findHql(String hql,Object pageNow,Object limit){
		PageUtil pu = new PageUtil() ;
		Integer tempPageNow = 1,tempLimit = PageUtil.globalLimit;;
		if(null != pageNow){
			if( pageNow instanceof String ){
				tempPageNow = Integer.parseInt((String) pageNow);
			}else{
				tempPageNow = (Integer) pageNow;
			}
		}
		if(null != limit){
			if( limit instanceof String ){
				tempLimit = Integer.parseInt((String) limit);
			}else{
				tempLimit = (Integer) limit;
			}
		}
		try {
			init();
			Query query = session.createQuery(hql);
			pu = new PageUtil(query.list().size(), tempPageNow,tempLimit);
			query.setFirstResult((tempPageNow-1)*tempLimit).setMaxResults(tempLimit);
			pu.setData(query.list());
		} catch (HibernateException e) {
			e.printStackTrace();;
		} finally {
		}
		return pu;
	}
	
	public PageUtil findSql(String sql,Object pageNow ,Object limit){
		PageUtil pu = new PageUtil() ;
		Integer tempPageNow = 1,tempLimit = PageUtil.globalLimit;;
		if(null != pageNow){
			if( pageNow instanceof String ){
				tempPageNow = Integer.parseInt((String) pageNow);
			}else{
				tempPageNow = (Integer) pageNow;
			}
		}
		if(null != limit){
			if( limit instanceof String ){
				tempLimit = Integer.parseInt((String) limit);
			}else{
				tempLimit = (Integer) limit;
			}
		}
		try {
			init();
			Query query = session.createSQLQuery(sql);
			pu = new PageUtil(query.list().size(), tempPageNow,tempLimit);
			query.setFirstResult((tempPageNow-1)*tempLimit).setMaxResults(tempLimit);
			pu.setData(query.list());
		} catch (HibernateException e) {
			e.printStackTrace();;
		} finally {
			pu.setLimit(10);
		}
		return pu;
	}
	
	public PageUtil findSql(String sql,Class<?> c,Object pageNow ,Object limit){
		PageUtil pu = new PageUtil() ;
		Integer tempPageNow = 1,tempLimit = PageUtil.globalLimit;;
		if(null != pageNow){
			if( pageNow instanceof String ){
				tempPageNow = Integer.parseInt((String) pageNow);
			}else{
				tempPageNow = (Integer) pageNow;
			}
		}
		if(null != limit){
			if( limit instanceof String ){
				tempLimit = Integer.parseInt((String) limit);
			}else{
				tempLimit = (Integer) limit;
			}
		}
		try {
			init();
			Query query = session.createSQLQuery(sql).addEntity(c);
			pu = new PageUtil(query.list().size(), tempPageNow,tempLimit);
			query.setFirstResult((tempPageNow-1)*tempLimit).setMaxResults(tempLimit);
			pu.setData(query.list());
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
		}
		return pu;
	}
	
	public List<?> findSql(String sql,Class<?> c){
		List<?> objects = null;
		try {
			init();
			Query query = session.createSQLQuery(sql).addEntity(c);
			objects = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
		}
		return objects;
	}
	
	public List<?> findSql(String sql){
		List<?> objects = null;
		try {
			init();
			Query query = session.createSQLQuery(sql);
			objects = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
		}
		return objects;
	}

	public void deleteSql(String sql) {
		// TODO Auto-generated method stub
		try {
			init();
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
		}
	}

}
