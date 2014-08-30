package com.era.serviceImpl;

import java.util.List;
import java.util.Map;

import com.era.dao.BaseDAO;
import com.era.orm.Guarantee;
import com.era.orm.User;
import com.era.orm.VillageAd;
import com.era.service.VillageService;
import com.era.util.DateUtil;

public class VillageServiceImpl implements VillageService{

	private BaseDAO dao;
	
	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void selVillageAd(String villageId, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hql = "from VillageAd v where v.villageId="+Integer.parseInt(villageId);
		List<VillageAd> vas = dao.query(hql, 1, 3);
		if(vas.size() == 0){
			VillageAd villageAd = new VillageAd();
			villageAd.setIsOnclick(0);
			villageAd.setLogo("http://www.tcshenghuo.org:8806/CellCode/images/zs1.png"/*"www.tcshenghuo.org:8806/CellCode/images/zs.png"*/);
			villageAd.setUrl("");
			villageAd.setVillageId(Integer.parseInt(villageId));
			vas.add(villageAd);
			VillageAd villageAd1 = new VillageAd();
			villageAd1.setIsOnclick(0);
			villageAd1.setLogo("http://www.tcshenghuo.org:8806/CellCode/images/zs2.png"/*"www.tcshenghuo.org:8806/CellCode/images/zs.png"*/);
			villageAd1.setUrl("");
			villageAd1.setVillageId(Integer.parseInt(villageId));
			vas.add(villageAd1);
			VillageAd villageAd2 = new VillageAd();
			villageAd2.setIsOnclick(0);
			villageAd2.setLogo("http://www.tcshenghuo.org:8806/CellCode/images/zs3.png"/*"www.tcshenghuo.org:8806/CellCode/images/zs.png"*/);
			villageAd2.setUrl("");
			villageAd2.setVillageId(Integer.parseInt(villageId));
			vas.add(villageAd2);
		}
		map.put("list", vas);
		map.put("responseCode", "0");
		map.put("msg", "查询成功");
	}
	
	@Override
	public void selGuarantee(String userId, Integer pageNo, Integer pageNum, Map<String, Object> map) {
		// TODO Auto-generated method stub
		if( null == pageNo){
			pageNo = 1;
		}
		if( null == pageNum){
			pageNum = 15;
		}
		
		String hql = "from Guarantee g where 1=1 ";
		User user = (User) dao.loadById(User.class, Integer.parseInt(userId));
		if( user.getSellIsNo() == 3 ){
			hql += " and g.villageId="+user.getVillageId();
		}else{
			hql += " and g.userId="+user.getId();
		}
		
		List<Guarantee> guarantees = dao.query(hql, pageNo, pageNum);
		map.put("code", 0);
		map.put("msg", "查询成功");
		map.put("list", guarantees);
	}

	@Override
	public void completeGuarantee(String userId, String guaranteeId,
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		Guarantee guarantee = (Guarantee) dao.loadById(Guarantee.class, Integer.parseInt(guaranteeId));
		guarantee.setStatus(1);
		guarantee.setCompleteTime(DateUtil.getNowString("yyyy-MM-dd HH:mm:ss"));
		guarantee.setCompleteId(Integer.parseInt(userId));
		dao.saveOrUpdate(guarantee);
		map.put("code", 0);
		map.put("msg", "提交成功");
	}

	@Override
	public void feedbackGuarantee(String guaranteeId, String type,
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		Guarantee guarantee = (Guarantee) dao.loadById(Guarantee.class, Integer.parseInt(guaranteeId));
		guarantee.setFeedback(Integer.parseInt(type));
		dao.saveOrUpdate(guarantee);
		map.put("code", 0);
		map.put("msg", "评价成功");
	}


}
