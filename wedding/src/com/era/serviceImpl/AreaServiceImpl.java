package com.era.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Area;
import com.era.service.AreaService;

public class AreaServiceImpl implements AreaService 
{
	private BaseDAO dao;

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Area> areaAll(String pageNo,String pageNum) 
	{
		List<Object> list = null;
		List list_seller = new ArrayList();
		if(pageNum == null || pageNum.equals(""))
		{
			list = dao.searchBySql("select * from Area as p order by (select count(*) from Feature where areaId =p.id) desc",Integer.valueOf(pageNo),10);
		}
		else
		{
			list = dao.searchBySql("select * from Area as p order by (select count(*) from Feature where areaId =p.id) desc",Integer.valueOf(pageNo),Integer.valueOf(pageNum));
		}
		for(int i=0;i<list.size();i++)
		{
			Object[] object = (Object[]) list.get(i);
			int number = dao.countQuery("select count(*) from Feature where areaId = "+Integer.parseInt(object[0].toString()));
				Area areaOne = new Area();
				areaOne.setAreaName(object[1].toString());
				areaOne.setAddtime(object[2].toString());
				if(object[3] == null || object[3].equals(""))
				{
					areaOne.setIcon("");
				}
				else
				{
					areaOne.setIcon(object[3].toString());
				}
				areaOne.setId(Integer.parseInt(object[0].toString()));
				areaOne.setNumber(number);
				list_seller.add(areaOne);
		}
		return list_seller;
	}

	@Override
	public Integer numArea() 
	{
		int number = dao.countQuery("select count(*) from Area");
		return number;
	}
}
