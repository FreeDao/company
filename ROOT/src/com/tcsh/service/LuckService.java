package com.tcsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcsh.common.PageUtil;
import com.tcsh.dao.CDB;
import com.tcsh.model.cell.Lotterydraw;

@Component("luckService")
public class LuckService {

	@Autowired
	private CDB rdb;

	/**
	 * 查询中奖信息
	 * @param phone 
	 * @param num 
	 * @param pageNow
	 * @param limit
	 * @return
	 */
	public PageUtil findLuckList(String num, String phone, Integer pageNow, Integer limit) {
		// TODO Auto-generated method stub
		//String hql = "from Lotterydraw l where 1 = 1 ";
		String hql = "from Lotterydraw l where l.isWin <> 0 and l.isReceiving = 0 and l.addtime >= '2014-07-05' ";
		if( null != num && !"".equals(num)){
			hql += " and l.draw like '%"+num+"%'";
		}
		if( null != phone && !"".equals(phone)){
			hql += " and l.iphone like '%"+phone+"%'";
		}
		return rdb.findHql(hql, pageNow, limit);
	}

	/**
	 * 设置信息为已发放
	 * @param parameter
	 */
	public void send(String id) {
		// TODO Auto-generated method stub
		Lotterydraw l = (Lotterydraw) rdb.find(Lotterydraw.class, Integer.parseInt(id));
		l.setIsReceiving(1);
		rdb.saveOrUpdate(l);
	}

	public void add(String num, String time, String phone, String win) {
		// TODO Auto-generated method stub
		Lotterydraw l = new Lotterydraw();
		l.setDesignated(3);
		l.setDraw(num);
		l.setAddtime(time);
		l.setIphone(phone);
		l.setIsWin(Integer.parseInt(win));
		l.setIsReceiving(0);
		l.setSurplus(0);
		rdb.saveOrUpdate(l);
	}
	
	
}
