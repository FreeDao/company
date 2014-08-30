package com.era.serviceImpl;

import com.era.dao.BaseDAO;
import com.era.orm.Checkversion;
import com.era.service.CheckVersionService;
import com.era.util.BaseUtils;

public class CheckVersionServiceImpl implements CheckVersionService {

	private BaseDAO dao;
	
	@Override
	public Checkversion getNewVersion(Integer appType,String versionNum) {//加一个字段 是否启用
		String hql = "from Checkversion where appType = "+appType+" and versionNum='"+versionNum+"' and isUse = 1";

		Checkversion ckn = (Checkversion)dao.loadObject(hql);
		
		return ckn;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}
}
