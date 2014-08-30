package com.era.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Area;
import com.era.orm.CustomType;
import com.era.service.AreaService;
import com.era.util.BaseUtils;

public class AreaServiceImpl implements AreaService {
	
	private String hql = "";
	private BaseDAO dao;
	
	@Override
	public List<Area> getAreaById(int cityId)
	{
		List<Area> listArea = dao.query("from Area where cityId = "+cityId);
		return listArea;
	}
	
	/**
	 * 查询所有区域当中最后一次操作时间
	 */
	@Override
	public String getAreaDate() {
		hql = "select addTime from Area order by addtime DESC";
		
		String addTime = (String)dao.loadObject(hql);
		
		return BaseUtils.getNowStringDateTime(addTime);
	}

	/**
	 * TODO 计算两个时间差
	 */
	public boolean compareTwoDate(String oldDate) {
		boolean flag = false;
		if("1".equals(oldDate)){
			flag = true;
		}else{
			hql = "select UNIX_TIMESTAMP('"+getAreaDate()+"')-UNIX_TIMESTAMP('"+oldDate+"') as timecha";
			Integer timeCha = Integer.parseInt(dao.querySQL(hql).get(0)+"");
			if(timeCha > 0){//有时间差，有更新
				flag = true;
			}else{//没有时间差，没更新
				flag = false;
			}
		}
		return flag;
	}
	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<CustomType> getCustomById(Integer marketId) {
		// TODO Auto-generated method stub
		List<CustomType> listCustom = dao.query("from CustomType where marketId = "+marketId);
		return listCustom;
	}
}
