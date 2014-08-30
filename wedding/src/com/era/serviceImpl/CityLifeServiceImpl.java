package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Seller;
import com.era.service.CityLifeService;

public class CityLifeServiceImpl implements CityLifeService 
{
	private BaseDAO memberDao;
	
	@Override
	public List<Seller> selSeller(String dim, String lat, String type,
			String pageNo,String pageNum) 
	{
		List<Seller> list = null;
		String hql = "";
		try {
			if(type == null || type.equals(""))
			{
				
			}
			else
			{
				if(Integer.valueOf(type) == 0)
				{
					//酒店
					hql = "from Seller where typeId = 19";
				}
				else if(Integer.valueOf(type) == 1)
				{
					//美食
					hql = "from Seller where typeId = 20";
				}
				else if(Integer.valueOf(type) == 2)
				{
					//旅行社
					hql = "from Seller where typeId = 328";
				}
				
				hql+=" order by ACOS(SIN(("+lat+" * 3.1415) / 180 ) *SIN((dim * 3.1415) / 180 ) + COS(("+lat+" * 3.1415) / 180 ) * COS(( dim * 3.1415) / 180 ) *COS(("+dim+" * 3.1415) / 180 - (log * 3.1415) / 180 ) ) * 6380 asc";
				if(pageNum == null || pageNum.equals(""))
				{
					list = memberDao.query(hql,Integer.valueOf(pageNo),10);
				}
				else
				{
					list = memberDao.query(hql,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public BaseDAO getMemberDao() {
		return memberDao;
	}

	public void setMemberDao(BaseDAO memberDao) {
		this.memberDao = memberDao;
	}

}
