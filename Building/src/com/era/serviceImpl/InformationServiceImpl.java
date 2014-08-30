package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Information;
import com.era.service.InformationService;

public class InformationServiceImpl implements InformationService
{
	private BaseDAO dao;

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao)
	{
		this.dao = dao;
	}
	@Override
	public List<Information> allInformat(String pageNo, String pageNum)
	{
		List<Information> list = null;
		try {
			if(pageNo == null ||pageNo.equals(""))
			{
				list = dao.query("from Information order by addtime desc");
			}
			else
			{
				list = dao.query("from Information order by addtime desc",Integer.valueOf(pageNo),Integer.valueOf(pageNum));
			}
		} catch (Exception e) {
			
		}
		return list;
	}
}
