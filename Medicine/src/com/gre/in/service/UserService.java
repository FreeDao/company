package com.gre.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gre.common.PageUtil;
import com.gre.common.HDB;
import com.gre.common.model.UpdateInfo;
import com.gre.common.model.User;
import com.gre.common.model.UserCollect;

@Component("userService")
public class UserService {

	@Autowired
	private HDB hdb;

	/**
	 * 通过主键userId查找对象
	 * @param c
	 * @param email
	 * @return
	 */
	public User findUser(int userId) {
		// TODO Auto-generated method stub
		return (User) hdb.find(User.class, userId);
	}
	
	/**
	 * 通过email查找对象
	 * @param c
	 * @param email
	 * @return
	 */
	public User findUser(String userName) {
		// TODO Auto-generated method stub
		String hql = "from User u where u.userName='"+userName+"'";
		List<?> list = hdb.findHql(hql);
		if(list.size() >= 1){
			return (User) list.get(0);
		}else{
			return null;
		}
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
	 *  通过账户密码查找对象
	 * @param email
	 * @param password
	 * @return
	 */
	public User findUser(String userName, String password) {
		// TODO Auto-generated method stub
		String hql = "from User u where u.userName = '"+userName+"' and u.passWord = '"+password+"'";
		List<User> list = (List<User>) hdb.findHql(hql);
		if(list.size() >= 1){
			return list.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 查询用户收藏列表
	 * @param userId
	 * @param type
	 * @param limit 
	 * @param pageNow 
	 * @return
	 */
	public PageUtil findCollectList(int userId, int type, int pageNow, Integer limit) {
		// TODO Auto-generated method stub
		String hql = "";
		if(0 == type){
			hql = "select b from User u,UserCollect uc,Business b where u.id = uc.userId and b.id = uc.collectId and uc.type = 0";
		}else if(1 == type){
			hql = "select r from User u,UserCollect uc,Recruit r where u.id = uc.userId and r.id = uc.collectId and uc.type = 1";
		}else{
			hql = "select j from User u,UserCollect uc,Job j where u.id = uc.userId and j.id = uc.collectId and uc.type = 2";
		}
		return hdb.findHql(hql, pageNow, limit);
	}

	/**
	 * 通过id查询对象
	 * @param class
	 * @param userId
	 * @return
	 */
	public Object findObjById(Class clazz, Integer id) {
		// TODO Auto-generated method stub
		return hdb.find(clazz, id);
	}

	/**
	 * 通过id查询对应的收藏记录
	 * @param userId
	 * @param collectId
	 * @param type
	 * @return
	 */
	public UserCollect findCollect(int userId, int collectId, int type) {
		// TODO Auto-generated method stub
		String hql = "from UserCollect uc where uc.userId = "+userId+" and uc.collectId = "+collectId+" and uc.type = "+type;
		return (UserCollect) hdb.findUniqueHql(hql);
	}

	/**
	 * 保存对象
	 * @param userCollect
	 */
	public void saveOrUpdateObj(Object obj) {
		// TODO Auto-generated method stub
		hdb.saveOrUpdate(obj);
		
	}

	/**
	 * 删除对象
	 * @param userCollect
	 */
	public void delObj(Object obj) {
		// TODO Auto-generated method stub
		hdb.delete(obj);
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
	
	/**
	 * 查询版本信息
	 * @return
	 */
	public UpdateInfo findUpdateInfo(Integer platForm) {
		// TODO Auto-generated method stub
		String hql = "from UpdateInfo ui where ui.platForm="+platForm;
		return (UpdateInfo) hdb.findUniqueHql(hql);
	}
	
}