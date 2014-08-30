package com.era.serviceImpl;

import com.era.dao.BaseDAO;
import com.era.orm.Backmessage;
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
}
