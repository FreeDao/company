package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Area;
import com.era.orm.BusmarSetManager;
import com.era.orm.CustomType;
import com.era.orm.Market;
import com.era.service.MarketService;
import com.era.util.BaseUtils;

public class MarketServiceImpl implements MarketService
{
	private BaseDAO dao;
	
	@Override
	public List<Object> selMarket(String type,int cityId, int pageNo, int pageSize) 
	{
		String hql="select ket.id,ket.type,city.cityName,ket.sort from Market ket,City city where 1 = 1  and ket.cityId = city.id";
		if(type == null || type.equals("") )
		{
			
		}
		else
		{
			hql+=" and type like '%"+type+"%'";
		}
		if(cityId == 0)
		{
			
		}
		else
		{
			hql += " and  ket.cityId = "+cityId;
		}
		List<Object> list = dao.query(hql,pageNo,pageSize);
		return list;
	}

	@Override
	public int numberMarket(String type,int cityId)
	{
		String hql="select count(*) from Market ket,City city where 1 = 1  and ket.cityId = city.id";
		if(type == null || type.equals("") )
		{
			
		}
		else
		{
			hql+=" and type like '%"+type+"%'";
		}
		if(cityId == 0)
		{
			
		}
		else
		{
			hql += " and  ket.cityId = "+cityId;
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
	public int numberSort(int sort,int cityId) 
	{
		List<Market> list = dao.query("from Market where sort = "+sort+" and cityId="+cityId);
		return list.size();
	}
	

	@Override
	public Market marketId(int id) {
		Market user = new Market();
		user = (Market) dao.loadById(Market.class,id);
		return user;
	}
	

	@Override
	public Market serviceSort(String sort) 
	{
		Market model = (Market) dao.loadObject("from Market where sort = "+sort);
		return model;
	}

	
	@Override
	public List<Market> allMarket()
	{
		List<Market> list = dao.query("from Market");
		return list;
	}
	@Override
	public List<Market> selSellerMarket(Integer city)
	{
		List<Market> list = dao.query("from Market where cityId = "+city);
		return list;
	}
	
	@Override
	public int numSort(int id, int num) 
	{
		int ide = dao.countQuery("from Market where ");
		return ide;
	}
	
	@Override
	public boolean updateSort(Market market) 
	{
		boolean flag = false;
		try {
			dao.update("UPDATE Market SET sort = "+market.getSort()+" WHERE id ="+market.getId());
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
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
	public int numberMarketUser(String username) {
		// TODO Auto-generated method stub
		String hql="";
		if(username == null || username.equals(""))
		{
			hql= "select count(*) from Market m,BusmarSetManager bm,City city where bm.bmsmId = m.id and city.id = m.cityId";
		}else{
			hql= "select count(*) from Market m,BusmarSetManager bm,City city  where bm.bmsmId = m.id and bm.userName like '%"+username+"%' and city.id = m.cityId";
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public List<Object> selMarketUser(String username, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String hql="";
		if(username == null || username.equals(""))
		{
				hql="select bm,m.type from Market m,BusmarSetManager bm,City city  where bm.bmsmId = m.id and city.id = m.cityId ";
		}
		else
		{
				hql="select bm,m.type from Market m,BusmarSetManager bm,City city  where bm.bmsmId = m.id and bm.userName like '%"+username+"%' and city.id = m.cityId";
		}
		List<Object> list = dao.query(hql,pageNo,pageSize);
		return list;
	}

	@Override
	public boolean isHasUsername(String username) {
		// TODO Auto-generated method stub
		boolean isHas = false;
		String hql = "from BusmarSetManager bm where bm.userName = '"+username+"'";
		List<BusmarSetManager> bms = dao.query(hql);
		if(bms.size() > 0){
			isHas = true;
		}else{
			isHas = false;
		}
		return isHas;
	}

	@Override
	public boolean addBusmarSetManager(BusmarSetManager bm) {
		// TODO Auto-generated method stub
		boolean isSave = false;
		try {
			dao.saveOrUpdate(bm);
			isSave = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isSave;
	}

	@Override
	public boolean delMarketUser(int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			dao.delById(BusmarSetManager.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean hasType(String typeId) {
		// TODO Auto-generated method stub
		boolean isHas = false;
		String hql = "from BusmarSetManager bm where bm.bmsmId = '"+typeId+"'";
		List<BusmarSetManager> bms = dao.query(hql);
		if(bms.size() > 0){
			isHas = true;
		}else{
			isHas = false;
		}
		return isHas;
	}

	@Override
	public List<Area> selSellerArea(Integer id) {
		// TODO Auto-generated method stub
		List<Area> list = dao.query("from Area where cityId = "+id);
		return list;
	}

	@Override
	public int numberMarketArea(String areaName) {
		// TODO Auto-generated method stub
		String hql = "from Area a,City c where a.cityId = c.id ";
		if( null != areaName && !"".equals(areaName)){
			hql += " and a.areaName like '%"+areaName+"%'";
		}
		return dao.query(hql).size();
	}

	@Override
	public List<Object> selMarketArea(String areaName,int pagenum, int i) {
		// TODO Auto-generated method stub
		String hql = "from Area a,City c where a.cityId = c.id ";
		if( null != areaName && !"".equals(areaName)){
			hql += " and a.areaName like '%"+areaName+"%'";
		}
		return  dao.query(hql,pagenum,i);
	}

	@Override
	public int numberMarketCustomType(String typeName) {
		// TODO Auto-generated method stub
		String hql = "from CustomType ct,Market m,City c where m.id = ct.marketId and c.id = m.cityId ";
		if( null != typeName && !"".equals(typeName)){
			hql += " and ct.name like '%"+typeName+"%'";
		}
		return dao.query(hql).size();
	}

	@Override
	public List<Object> selMarketCustomType(String typeName,int pagenum, int i) {
		// TODO Auto-generated method stub
		String hql = "from CustomType ct,Market m,City c where m.id = ct.marketId and c.id = m.cityId ";
		if( null != typeName && !"".equals(typeName)){
			hql += " and ct.name like '%"+typeName+"%'";
		}
		return dao.query(hql,pagenum,i);
	}

	@Override
	public void saveOrUpdateMarketArea(String id, String cityId, String areaName) {
		// TODO Auto-generated method stub
		Area a;
		if( null != id && !"".equals(id)){
			a = (Area) dao.loadById(Area.class, Integer.parseInt(id));
		}else{
			a = new Area();
			a.setAddTime(BaseUtils.getNowDate("yyyy-MM-dd"));
		}
		a.setCityId(Integer.parseInt(cityId));
		a.setAreaName(areaName);
		dao.saveOrUpdate(a);
		
	}

	@Override
	public boolean delMarketArea(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from Seller s where s.areaId ="+id;
		int temp = dao.query(hql).size();
		if(temp > 0){
			return false;
		}
		dao.delById(Area.class, id);
		return true;
	}

	@Override
	public void saveOrUpdateMarketCustomType(String id, String marketId,
			String name) {
		// TODO Auto-generated method stub
		CustomType ct;
		if( null != id && !"".equals(id)){
			ct = (CustomType) dao.loadById(CustomType.class, Integer.parseInt(id));
		}else{
			ct = new CustomType();
			ct.setAddTime(BaseUtils.getNowString("yyyy-MM-dd"));
		}
		ct.setMarketId(Integer.parseInt(marketId));
		ct.setName(name);
		dao.saveOrUpdate(ct);
	}

	@Override
	public boolean delMarketCustomType(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from Seller s where s.customTypeId ="+id;
		int temp = dao.query(hql).size();
		if(temp > 0){
			return false;
		}
		dao.delById(CustomType.class, id);
		return true;
	}

	@Override
	public Area selAreaById(int id) {
		// TODO Auto-generated method stub
		return (Area) dao.loadById(Area.class, id);
	}

	@Override
	public CustomType selCustomById(int id) {
		// TODO Auto-generated method stub
		return (CustomType) dao.loadById(CustomType.class, id);
	}

	@Override
	public List<CustomType> selCustomType(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from CustomType ct where ct.marketId ="+id;
		return dao.query(hql);
	}

}
