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
		
		Checkversion ck = new Checkversion();
		
		Checkversion ckn = (Checkversion)dao.loadObject(hql);
		
		if(ckn != null){//说明当前传递的版本号存在，说明没有更新
			ck = null;
		}else{//如果不存在的话，说明有更新了
			hql = "from Checkversion where appType = "+appType+" and isUse = 1";
			ck = (Checkversion)dao.loadObject(hql);
		}
		
		
//		if("1".equals(agoDate)){
//			 ck = (Checkversion)dao.loadObject(hql);
//		}else{
//			/*
//			 * 根据当前传递进来的时间，到数据库当中去对比是否有时间更新
//			 * 检查更新时间，比较两个时间，有就返回，没就返回null
//			 */
//			String hql_newTime = "select addTime from Checkversion where appType = "+appType+" order by addTime DESC";
//			String newTime_str = (String)dao.loadObject(hql_newTime);
//			
//			String hql_cha = "select UNIX_TIMESTAMP('"+newTime_str+"')-UNIX_TIMESTAMP('"+agoDate+"') as timecha";
//			Integer timeCha = Integer.parseInt(dao.querySQL(hql_cha).get(0)+"");
//			
//			if(timeCha > 0){//有更新
//				ck = (Checkversion)dao.loadObject(hql);
//			}else{//无更新
//				ck = null;
//			}
//		}
		Checkversion cks = new Checkversion();
		if(ck!=null){
			cks.setId(ck.getId());
			cks.setVersionNum(ck.getVersionNum());
			cks.setAddTime(BaseUtils.getNowStringDateTime(ck.getAddTime()));
			cks.setAppUrl(ck.getAppUrl());
			cks.setAppType(ck.getAppType());
			cks.setIsUse(ck.getIsUse());
		}else{
			cks = null;
		}
		
		return cks;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}
}
