package com.gre.common;


import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Component("hdb")
public class HDB {

	private Session session;
	@Autowired
	private SessionFactory sessionFactory;

	private void init() {
		session = sessionFactory.getCurrentSession();
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
	
	public PageUtil findAll(Class clazz,Integer pageNow,Integer limit){
		PageUtil pu = null ;
		if(null == pageNow){
			pageNow = 1;
		}
		try {
			init();
			Query query = session.createQuery("from " + clazz.getName());
			if( null == limit ){
				pu = new PageUtil(query.list().size(), pageNow);
			}else{
				pu = new PageUtil(query.list().size(), pageNow,limit);
			}
			query.setFirstResult((pageNow-1)*PageUtil.limit).setMaxResults(PageUtil.limit);
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
	
	public PageUtil findHql(String hql,Integer pageNow,Integer limit){
		PageUtil pu = null ;
		if(null == pageNow){
			pageNow = 1;
		}
		try {
			init();
			Query query = session.createQuery(hql);
			if( null == limit ){
				pu = new PageUtil(query.list().size(), pageNow);
			}else{
				pu = new PageUtil(query.list().size(), pageNow,limit);
			}
			query.setFirstResult((pageNow-1)*PageUtil.limit).setMaxResults(PageUtil.limit);
			pu.setData(query.list());
		} catch (HibernateException e) {
			e.printStackTrace();;
		} finally {
		}
		return pu;
	}
	
	public PageUtil findSql(String sql,Integer pageNow,Integer limit){
		PageUtil pu = null ;
		if(null == pageNow){
			pageNow = 1;
		}
		try {
			init();
			Query query = session.createSQLQuery(sql);
			if( null == limit ){
				pu = new PageUtil(query.list().size(), pageNow);
			}else{
				pu = new PageUtil(query.list().size(), pageNow,limit);
			}
			query.setFirstResult((pageNow-1)*PageUtil.limit).setMaxResults(PageUtil.limit);
			pu.setData(query.list());
		} catch (HibernateException e) {
			e.printStackTrace();;
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
