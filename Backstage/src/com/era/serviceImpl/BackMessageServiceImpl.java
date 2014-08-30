package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Backmessage;
import com.era.orm.Comment;
import com.era.service.BackMessageService;

public class BackMessageServiceImpl implements BackMessageService {
	private BaseDAO dao;

	@Override
	public boolean putBackMessage(Backmessage backmessage) {
		boolean flag = false;
		try{
			dao.save(backmessage);
			flag = true;
		}catch (Exception e) {
			e.printStackTrace();
			flag = false;
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
	public List<Backmessage> selPutBackMessage(String page, String rows) {
		String hql = "from Backmessage  order by addTime desc";
		List<Backmessage> list = dao.query(hql,Integer.valueOf(page),Integer.valueOf(rows));
		return list;
	}

	@Override
	public int numberPutBackMessage() {
		String hql ="select count(*) from Backmessage";
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public boolean delPutBackMessage(int parseInt) {
		boolean flag = false;
		try {
			dao.delById(Backmessage.class, parseInt);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
		
	}
}
