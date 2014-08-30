package com.gre.bg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gre.common.HDB;
import com.gre.common.PageUtil;
import com.gre.common.model.UpdateInfo;
import com.gre.common.model.User;

@Component("bgUserService")
public class UserService {

	@Autowired
	private HDB hdb;

	/**
	 * 根据账号密码查询user对象
	 * @param email
	 * @param passWord
	 * @return
	 */
	public User findUser(String userName,String passWord) {
		// TODO Auto-generated method stub
		String hql = "from User u where u.userName='"+userName+"' and u.passWord ='"+passWord+"'";
		return (User) hdb.findUniqueHql(hql);
	}

	/**
	 * 通过id查询对象
	 * @param class1
	 * @param id
	 * @return
	 */
	public Object findObjectById(Class clazz, Integer id) {
		// TODO Auto-generated method stub
		return hdb.find(clazz, id);
	}

	/**
	 * 通过对象类型查找所有list
	 * @param class1
	 * @param pageNow
	 * @param limit
	 * @return
	 */
	public PageUtil findAllList(Class clazz, Integer pageNow,
			Integer limit) {
		// TODO Auto-generated method stub
		return hdb.findAll(clazz, pageNow, limit);
	}

	/**
	 * 根据对象查询所有的list
	 * @param class1
	 * @return
	 */
	public List<?> findAllList(Class clazz) {
		// TODO Auto-generated method stub
		return hdb.findAll(clazz);
	}

	/**
	 * 根据版本号查询
	 * @param i
	 * @return
	 */
	public UpdateInfo findUpdateInfoByPlatForm(Integer platForm) {
		// TODO Auto-generated method stub
		String hql = "from UpdateInfo ui where ui.platForm=" + platForm;
		return (UpdateInfo) hdb.findUniqueHql(hql);
	}

	/**
	 * 保存对象
	 * @param ios
	 */
	public void saveOrUpdateObj(Object obj) {
		// TODO Auto-generated method stub
		hdb.saveOrUpdate(obj);
	}

	/**
	 * 根据用户查找出收藏列表
	 * @param userId
	 * @return
	 */
	public List<?> findCollectListByUserId(Class clazz,Integer userId) {
		// TODO Auto-generated method stub
		String hql = "from "+clazz.getName()+" t where t.userId = "+userId;
		return hdb.findHql(hql);
	}

	/**
	 * 删除对象
	 * @param up
	 */
	public void delObj(Object obj) {
		// TODO Auto-generated method stub
		hdb.delete(obj);
	}

	/**
	 * 根据id删除对象
	 * @param class1
	 * @param userId
	 */
	public void delObjById(Class clazz, Integer userId) {
		// TODO Auto-generated method stub
		hdb.delete(clazz, userId);
	}

	/**
	 * 通过id查找对象
	 * @param class1
	 * @param userId
	 * @return
	 */
	public Object findObjById(Class clazz, Integer userId) {
		// TODO Auto-generated method stub
		return hdb.find(clazz, userId);
	}
}
