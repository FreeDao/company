package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Lotterydraw;
import com.era.orm.Luck;
import com.era.orm.User;
import com.era.orm.Winning;
import com.era.service.UserService;

public class UserServiceImpl implements UserService {
	private BaseDAO dao;
	private String hql = "";

	@Override
	public int numberUser(String id, String userName) {
		String hql = "";
		if (id == null || id.equals("")) {
			if (userName == null || userName.equals("")) {
				hql = "select count(*) from User";
			} else {
				hql = "select count(*) from User where nickName='" + userName
						+ "'";
			}

		} else {
			if (userName == null || userName.equals("")) {
				hql = "select count(*) from User where id="
						+ Integer.valueOf(id);
			} else {
				hql = "select count(*) from User where id="
						+ Integer.valueOf(id) + " and nickName='" + userName
						+ "'";
			}
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public List<User> selUser(String id, String userName, int pageNo,
			int pageSize) {
		String hql = "";
		if (id == null || id.equals("")) {
			if (userName == null || userName.equals("")) {
				hql = "from User";
			} else {
				hql = "from User where nickName='" + userName + "'";
			}

		} else {
			if (userName == null || userName.equals("")) {
				hql = "from User where id=" + Integer.valueOf(id);
			} else {
				hql = "from User where id=" + Integer.valueOf(id)
						+ " and nickName='" + userName + "'";
			}
		}
		List<User> list = dao.query(hql, pageNo, pageSize);
		return list;
	}

	@Override
	public boolean delUser(int id) {
		boolean flag = false;
		try {
			dao.delById(User.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addUser(User user) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(user);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * TODO 用户登录
	 */
	@Override
	public User loginUserInfo(String userName, String userPwd) {
		hql = "from User where (nickName = '" + userName + "' or email = '"+userName+"') and passWord='" + userPwd + "'";
		User user = (User) dao.loadObject(hql);
		return user;
	}

	/**
	 * TODO 判断用户名是否已经存在
	 * 
	 * @return
	 */
	@Override
	public boolean isUser(String userName) {
		boolean flag = false;
		hql = "from User where nickName = '" + userName + "'";
		Object object = dao.loadObject(hql);
		if (object != null) {
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	
	/**
	 * TODO 判断用户名是否已经存在
	 * 
	 * @return
	 */
	@Override
	public boolean isEmail(String email) {
		boolean flag = false;
		hql = "from User where email = '" + email + "'";
		Object object = dao.loadObject(hql);
		if (object != null) {
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	
	/**
	 * TODO 客户端用户注册实现接口
	 */
	@Override
	public User registerUserInfo(User user) {
		boolean flag = false;
//		hql="insert into User values(NULL,'李四','123456',1,'张三','我是李四',21,123,1,'http://',null,null,null,'lisi@163.com')"
		try {
			dao.saveOrUpdate(user);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		User userInfo = null;
		if(flag){
			userInfo = loginUserInfo(user.getNickName(), user.getPassWord());
		}
		return userInfo;
	}
	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Luck> selListLotterdraw(String num) {
		hql = "from  Luck where luckOne = "+Integer.valueOf(num);
		List<Luck> list = dao.query(hql);
		return list;
	}

	@Override
	public boolean addWin(Winning win) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(win);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int sanNumberThree() {
		int i = dao.countBySql("select count(*) from Winning where natNum = 3");
		return i;
	}

	@Override
	public int sanNumberTwo() {
		int i = dao.countBySql("select count(*) from Winning where natNum = 2");
		return i;
	}

	@Override
	public int sanNumberOne() {
		int i = dao.countBySql("select count(*) from Winning where natNum = 1");
		return i;
	}
}
