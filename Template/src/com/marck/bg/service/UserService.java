package com.marck.bg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marck.common.CommonUtil;
import com.marck.common.dao.HDB;
import com.marck.common.model.Menu;
import com.marck.common.model.SystemAgrs;
import com.marck.common.model.User;

@Component("userService")
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
/*@Transactional(readOnly = false,propagation = Propagation.REQUIRED)*/
public class UserService {

	@Autowired
	private HDB hdb;
	
	/**
	 * 检查用户是否存在
	 * @param user
	 * @return
	 */
	public User getUser(User user){
		String hql = "from User u where u.phone='"+user.getPhone()+"' and u.passWord='"+CommonUtil.Md5(user.getPassWord())+"'";
		List<User> list = (List<User>) hdb.findHql(hql);
		if(list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 获取菜单树
	 * @param user
	 * @return
	 */
	public List<Menu> getTree(User user) {
		// TODO Auto-generated method stub
		List<Menu> tree = new ArrayList<Menu>();
		String hql = "from Menu m where m.level=1 order by m.sort asc";
		if(user.getRole() == 0){
			tree = (List<Menu>) hdb.findHql(hql);
			setChildren(tree);
		}else{
			
		}
		return tree;
	}
	
	/**
	 * 递归设置子节点
	 * @param tree
	 */
	private void setChildren(List<Menu> tree){
		for(Menu m : tree){
			String hql = "from Menu m where m.pid="+m.getId();
			List<Menu> childs = (List<Menu>) hdb.findHql(hql);
			if(childs.size() > 0){
				m.setChilds(childs);
				setChildren(childs);	
			}
		}
	}

	/**
	 * 获取系统配置参数
	 * @return
	 */
	public SystemAgrs getSystemAgrs() {
		// TODO Auto-generated method stub
		return (SystemAgrs) hdb.findUniqueHql("from SystemAgrs");
	}

	/**
	 * 更新密码
	 * @param u
	 * @param user
	 */
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePassword(User u, User user) {
		// TODO Auto-generated method stub
		u.setPassWord(CommonUtil.Md5(user.getPassWord()));
		hdb.saveOrUpdate(u);
	}
}
