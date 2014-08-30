package com.era.serviceImpl;

import com.era.dao.BaseDAO;
import com.era.orm.Seller;
import com.era.orm.User;
import com.era.orm.UserFeedBack;
import com.era.service.UserService;

public class UserServiceImpl implements UserService 
{
	private BaseDAO dao;
	
	@Override
	public User login(String email, String passWord)
	{
		User user = null;
		User userNo = new User();
		try {
			
			String hql = "";
			hql = "from User where email = '"+email+"' and passWord='" + passWord + "'";
			user = (User) dao.loadObject(hql);
			if(user == null || user.equals(""))
			{
				
			}
			else
			{
				hql = "from Seller where userId = "+user.getId();
				Seller sellerList = (Seller) dao.loadObject(hql);
				if(sellerList == null || sellerList.equals(""))
				{
					userNo.setAddress("");
					userNo.setSellerid(0);
					userNo.setCityId(0);
					userNo.setTypeId(0);
					userNo.setContent("");
					userNo.setSellerName("");
					userNo.setSellerOwner("");
					userNo.setPhone("");
					userNo.setEmailSeller("");
					userNo.setImgUrl("");
					userNo.setUserId(0);
					userNo.setPassWord(user.getPassWord());
					userNo.setEmail(user.getEmail());
					userNo.setId(user.getId());
				}
				else
				{
					userNo.setAddress(sellerList.getAddress());
					userNo.setSellerid(sellerList.getId());
					userNo.setCityId(sellerList.getCityId());
					userNo.setTypeId(sellerList.getTypeId());
					userNo.setContent(sellerList.getContent());
					userNo.setSellerName(sellerList.getSellerName());
					userNo.setSellerOwner(sellerList.getSellerOwner());
					userNo.setPhone(sellerList.getPhone());
					userNo.setEmailSeller(sellerList.getEmail());
					userNo.setImgUrl(sellerList.getImgUrl());
					userNo.setUserId(sellerList.getUserId());
					userNo.setPassWord(user.getPassWord());
					userNo.setEmail(user.getEmail());
					userNo.setId(user.getId());
				}
			}
		} catch (Exception e) {
			return null;
		}
		if(user == null || user.equals(""))
		{
			return user;
		}
		return userNo;
	}
	
	@Override
	public Seller selSeller(int id) 
	{
		Seller seller = null;
		try {
			String hql = "";
			hql = "from Seller where userId = "+id;
			seller = (Seller) dao.loadObject(hql);
		} catch (Exception e) {
			return null;
		}
		return seller;
	}

	public User loginQq(String qq,String pwd)
	{
		String hql = "";
		hql = "from User where qq = '"+qq+"' and password = '"+pwd+"'";
		User user = (User) dao.loadObject(hql);
		return user;
	}
	
	public User isHave(String email)
	{
		String hql = "";
		hql = "from User where email = '"+email+"'";
		User user = (User) dao.loadObject(hql);
		return user;
	}
	
	public boolean isHaveQq(String qq)
	{
		boolean flag = false;
		String hql = "";
		hql = "from User where qq = '"+qq+"'";
		User user = (User) dao.loadObject(hql);
		if(user!=null)
		{
			flag = true;
		}else
		{
			flag = false;
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
			flag = false;
		}
		return flag;
	}
	
	@Override
	public User selUserId(int id) 
	{
		User user = (User) dao.loadById(User.class,id);
		return user;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public boolean addUserFeedBack(UserFeedBack back) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(back);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

}
