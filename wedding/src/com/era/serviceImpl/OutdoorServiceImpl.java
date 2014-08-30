package com.era.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Admin;
import com.era.orm.Outdoor;
import com.era.orm.User;
import com.era.service.OutdoorService;
import com.era.util.BaseUtils;

public class OutdoorServiceImpl implements OutdoorService 
{
	private BaseDAO dao;
	
	@Override
	public List<Outdoor> outdoorAll(String pageNo, String pageNum,String userId)
	{
		List list_seller = new ArrayList();
		List<Outdoor> list = null;
		String hql = "";
		try {
			hql = "from Outdoor where 1 = 1";
			
			if(userId == null || userId.equals(""))
			{
				
			}
			else
			{
				hql+=" and userId = "+userId;
			}
			hql+=" order by addtime desc";
			if(pageNum == null || pageNum.equals(""))
			{
				list = dao.query(hql,Integer.valueOf(pageNo),10);
			}
			else
			{
				list = dao.query(hql,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
			}
			for(int i=0;i<list.size();i++)
			{
				Outdoor outdoor = new Outdoor();
				outdoor.setId(list.get(i).getId());
				outdoor.setAddtime(list.get(i).getAddtime());
				outdoor.setIcon(list.get(i).getIcon());
				outdoor.setUserId(list.get(i).getUserId());
				outdoor.setUserName(list.get(i).getUserName());
				outdoor.setDes(list.get(i).getDes());
				String hql_sellerImgs = "select head from User where id = "+list.get(i).getUserId();
				String user = (String) dao.loadObject(hql_sellerImgs);
				if(user == null || user.equals(""))
				{
					outdoor.setUserImage("");
				}
				else
				{
					outdoor.setUserImage(user);
				}
				String user3 = (String) dao.loadObject("select userName from User where id = "+list.get(i).getUserId());
				if(user3 == null || user3.equals(""))
				{
					outdoor.setUserName("");
				}
				else
				{
					outdoor.setUserName(user3);
				}
				list_seller.add(outdoor);
			} 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return list_seller;
	}
	
	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

}
