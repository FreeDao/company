package com.era.serviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Busmarset;
import com.era.orm.Busmarsetmanager;
import com.era.orm.Guarantee;
import com.era.orm.Market;
import com.era.orm.Seller;
import com.era.orm.User;
import com.era.orm.Village;
import com.era.orm.VillageManager;
import com.era.service.MarketService;
import com.era.util.BaseUtils;
import com.era.util.BusMarketQuery;
import com.era.util.DateUtil;
import com.era.util.LoginUser;
import com.era.util.PiaoJuTong;

public class MarketServiceImpl implements MarketService {
	private BaseDAO dao;
	private String hql = "";

	@Override
	public List<Object> selMarket(String type, int pageNo, int pageSize) {
		String hql = "";
		if (type == null || type.equals("")) {
			hql = "select ket.id,ket.type,city.cityName from Market ket,City city where ket.cityId = city.id order by ket.sort desc";
		} else {
			hql = "select ket.id,ket.type,city.cityName from Market ket,City city where ket.cityId = city.id and type like '%"
					+ type + "%' order by ket.sort desc";

		}
		List<Object> list = dao.query(hql);
		return list;
	}
	
	/**
	 * 根据市场类型id进行查询对应的入驻商家信息
	 * @param typeId
	 * @return
	 */
	@Override
	public Busmarset getBusMarketSet(int typeId) {
		String hql = "from Busmarset where marketId = "+typeId;
		Busmarset bs = (Busmarset)dao.loadObject(hql);
		
		Busmarset b = new Busmarset();
		b.setId(bs.getId());
		b.setBmsUserName(bs.getBmsUserName());
		b.setTelephone(bs.getTelephone());
		b.setBmsIntroduction(bs.getBmsIntroduction());
		b.setAddTime(BaseUtils.getNowStringDateTime(bs.getAddTime()));
		b.setVillageId(bs.getVillageId());
		return b;
	}

	@Override
	public int numberMarket(String type) {
		String hql = "";
		if (type == null || type.equals("")) {
			hql = "select count(*) from Market ket,City city where ket.cityId = city.id";
		} else {
			hql = "select count(*) from Market ket,City city where ket.cityId = city.id and type like '%"
					+ type + "%'";
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public boolean delMarket(int id) {
		boolean flag = false;
		try {
			dao.delById(Market.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addMarket(Market market) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(market);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Market> getMarketList(String cityId,String pageNo,String pageNum,String type)
	{
		hql = "from Market where 1=1";
		List<Market> list = null;
		if(cityId == null || cityId.equals(""))
		{
			
		}
		else
		{
			hql+=" and villageId="+cityId;
		}
		if(type == null || type.equals(""))
		{
			
		}
		else
		{
			hql+=" and typeEr="+type;
		}
		hql+=" order by sort ASC";
		if(pageNo == null || pageNo.equals("") || pageNum == null || pageNum.equals(""))
		{
			list = dao.query(hql);
		}
		else
		{
			list = dao.query(hql,Integer.valueOf(pageNo), Integer.valueOf(pageNum));
		}
		return list;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public int countMarket(int cityId) {
		hql = "select count(*) from Market where cityId=" + cityId;
		System.out.println(hql);
		int number=dao.countQuery(hql);
		return number;
	}

	@Override
	public List<Market> selLikeMark(String markName,String cityId, String pageNo,
			String pageNum) {
		List<Market> list= null;
		String hql = "from Market where cityId=" + cityId;
		if(markName == null || markName.equals(""))
		{
			
		}
		else
		{
			hql+=" and type like '%"+markName+"%'";
		}
		if(pageNo == null || pageNo.equals("") || pageNum == null || pageNum.equals(""))
		{
			 list = dao.query(hql);
		}
		else
		{
			 list = dao.query(hql,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
		}
		return list;
	}

	@Override
	public boolean addGuarantee(Guarantee tee) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(tee);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int numberMarekt(String parameter, String parameter2)
	{
		String hql="select count(*) from Market where 1 = 1";
		if(parameter == null || parameter.equals(""))
		{
			
		}
		else
		{
			hql+=" and villageId = "+parameter;
		}
		if(parameter2 == null || parameter2.equals(""))
		{
			
		}
		else
		{
			hql+=" and typeEr = "+parameter2;
		}
		int number=dao.countQuery(hql);
		return number;
	}

	@Override
	public boolean delMarekt(int parseInt) {
		boolean flag = false;
		try {
			dao.delById(Market.class, parseInt);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public void saveOrUpdateCommodity(LoginUser lu, String id, String title, String phone,
			String otherPrice, String price, String villageId, String shopId,
			String shopIdTwo, String brief,String level,String logo) {
		// TODO Auto-generated method stub
		Seller s;
		if(null == id || "".equals(id)){
			s = new Seller();
			s.setTitile(title);
			s.setPhone(phone);
			if( null != otherPrice){
				s.setOtherPrice(Double.parseDouble(otherPrice));
			}
			if(null != price){
				s.setPrice(Double.parseDouble(price));
			}
			s.setLevel(Double.parseDouble(level));
			if( null == villageId || "".equals(villageId)){
				Busmarset b = (Busmarset) dao.loadById(Busmarset.class, lu.getMarketInfoId());
				s.setVillageId(b.getVillageId());
			}else{
				s.setVillageId(Integer.parseInt(villageId));
			}
			s.setShop(1);
			s.setSort(0);
			s.setShopId(Integer.parseInt(shopId));
			s.setShopIdTwo(Integer.parseInt(shopIdTwo));
			s.setBrief(brief);
			s.setLogo(logo);
			s.setAddtime(DateUtil.getNowDate("yyyy-MM-dd"));
		}else{
			s = (Seller) dao.loadById(Seller.class, Integer.parseInt(id));
			s.setTitile(title);
			s.setPhone(phone);
			if( null != otherPrice){
				s.setOtherPrice(Double.parseDouble(otherPrice));
			}
			if(null != price){
				s.setPrice(Double.parseDouble(price));
			}
			s.setLevel(Double.parseDouble(level));
			if( null == villageId || "".equals(villageId)){
				Busmarset b = (Busmarset) dao.loadById(Busmarset.class, lu.getMarketInfoId());
				s.setVillageId(b.getVillageId());
			}else{
				s.setVillageId(Integer.parseInt(villageId));
			}
			s.setShop(1);
			s.setSort(0);
			s.setShopId(Integer.parseInt(shopId));
			s.setShopIdTwo(Integer.parseInt(shopIdTwo));
			s.setBrief(brief);
			if( null != logo){
				s.setLogo(logo);
			}
		}
		if(lu.getRole() == 3){
			s.setUserId(lu.getId());
		}
		dao.saveOrUpdate(s);
	}

	@Override
	public List<Guarantee> getGuaranteeList(String villageId, String page,
			String rows) {
		// TODO Auto-generated method stub
		String hql = "from Guarantee where 1=1 ";
		if(null != villageId && !"".equals(villageId) ){
			 		hql += " and villageId="+Integer.parseInt(villageId);
		}
		return dao.query(hql,Integer.valueOf(page),Integer.valueOf(rows));
	}

	@Override
	public int numberGuarantee(String villageId) {
		// TODO Auto-generated method stub
		String hql = "from Guarantee where 1=1 ";
		if(null != villageId && !"".equals(villageId) ){
			 		hql += " and villageId="+Integer.parseInt(villageId);
		}
		return dao.query(hql).size();
	}

	@Override
	public List<BusMarketQuery> getBusMarketList(LoginUser lu, String page, String rows) {
		// TODO Auto-generated method stub
		String hql = "select new com.era.util.BusMarketQuery(b.id,b.bmsUserName,b.telephone,b.bmsIntroduction,b.villageId,m.bmsmId,m.qq,m.email,m.userName,m.userPwd,b.addTime,(select v.name from Village v where v.id = b.villageId))  from Busmarset b,Busmarsetmanager m where m.bmsmId = b.id ";
		if(lu.getRole() == 2){
			hql += " and b.villageId in (select v.id from Village v where v.cityId ="+lu.getCityId()+")";
		}
		return dao.query(hql,Integer.parseInt(page),Integer.parseInt(rows));
	}

	@Override
	public Integer numberBusMarekt(LoginUser lu) {
		// TODO Auto-generated method stub
		String hql = "from Busmarset b where 1 = 1 ";
		if(lu.getRole() == 2){
			hql += " and b.villageId in (select v.id from Village v where v.cityId ="+lu.getCityId()+")";
		}
		return dao.query(hql).size();
	}

	@Override
	public boolean saveOrUpdateBusMarket(LoginUser lu, String id, String userName,
			String passWord, String bmsUserName, String telephone, String qq,
			String email, String cityId, String villageId) {
		// TODO Auto-generated method stub
		Busmarset b = new Busmarset();
		Busmarsetmanager bm = new Busmarsetmanager();
		User u = new User();
		if( null != id && !"".equals(id)){
			bm = (Busmarsetmanager) dao.loadById(Busmarsetmanager.class, Integer.parseInt(id));
			b = (Busmarset) dao.loadById(Busmarset.class, bm.getBmsmId());
			u = (User) dao.query("from User where manId="+bm.getId()).get(0);
		}else{
			String hql = "from Busmarsetmanager b where b.userName ='"+userName+"'";
			List<Object> user = dao.query(hql);
			if(user.size() != 0){
				return false;
			}
			b.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			bm.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			u.setAddtime(DateUtil.getNowString("yyyy-MM-dd"));
		}
		
		if(lu.getRole() == 2){
			b.setCityId(Integer.parseInt(lu.getCityId()));
		}else{
			b.setCityId(Integer.parseInt(cityId));
		}
		
		b.setBmsUserName(bmsUserName);
		b.setTelephone(telephone);
		b.setVillageId(Integer.parseInt(villageId));
		dao.saveOrUpdate(b);
		
		bm.setBmsmId(b.getId());
		bm.setEmail(email);
		bm.setQq(qq);
		bm.setTelePhone(telephone);
		bm.setUserName(userName);
		bm.setUserPwd(PiaoJuTong.Md5(passWord));
		dao.saveOrUpdate(bm);
		
		
		u.setEmail(bm.getUserName());
		u.setPassWord(PiaoJuTong.Md5(passWord));
		u.setManId(bm.getId());
		u.setNickName(b.getBmsUserName());
		//u.setImgUrl(b.getLogo());
		u.setVillageId(b.getVillageId());
		u.setSellIsNo(2);
		dao.saveOrUpdate(u);
		
		return true;
	}

	@Override
	public void delBusMarket(Integer id) {
		// TODO Auto-generated method stub
		Busmarsetmanager bm = (Busmarsetmanager) dao.loadById(Busmarsetmanager.class, id);
		List<User> us = dao.query("from User where manId="+bm.getId());
		for(User u : us){
			dao.delById(User.class, u.getId());
		}
		dao.delById(Busmarset.class, bm.getBmsmId());
		dao.delById(Busmarsetmanager.class, id);
	}

	@Override
	public List<VillageManager> getVillageManagerList(LoginUser lu,
			String page, String rows) {
		// TODO Auto-generated method stub
		String hql = "from VillageManager vm where 1=1 ";
		if(lu.getRole() == 2){
			hql += " and vm.villageId in (select v.id from Village v where v.cityId ="+lu.getCityId()+")";
		}
		return dao.query(hql, Integer.parseInt(page), Integer.parseInt(rows));
	}

	@Override
	public Integer numberVillageManager(LoginUser lu) {
		// TODO Auto-generated method stub
		String hql = "from VillageManager vm where 1=1 ";
		if(lu.getRole() == 2){
			hql += " and vm.villageId in (select v.id from Village v where v.cityId ="+lu.getCityId()+")";
		}
		return dao.query(hql).size();
	}

	@Override
	public boolean saveOrUpdateVillageManager(LoginUser lu, String id,
			String userName, String passWord, String villageId) {
		// TODO Auto-generated method stub
		VillageManager vm = new VillageManager();
		if( null != id && !"".equals(id)){
			vm = (VillageManager) dao.loadById(VillageManager.class, Integer.parseInt(id));
		}else{
			String hql = "from VillageManager vm where vm.userName ='"+userName+"'";
			List<Object> user = dao.query(hql);
			if(user.size() != 0){
				return false;
			}
		}
		vm.setPassWord(PiaoJuTong.Md5(passWord));
		vm.setUserName(userName);
		vm.setVillageId(Integer.parseInt(villageId));
		vm.setAddTime(DateUtil.getNowDate("yyyy-MM-dd"));
		
		dao.saveOrUpdate(vm);
		return true;
	}

	@Override
	public void delVillageManager(Integer id) {
		// TODO Auto-generated method stub
		dao.delById(VillageManager.class, id);
	}

}
