package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Busmarset;
import com.era.orm.Guarantee;
import com.era.orm.Market;
import com.era.service.MarketService;
import com.era.util.BaseUtils;

public class MarketServiceImpl implements MarketService {
	private BaseDAO dao;
	private String hql = "";

	@Override
	public List<Object> selMarket(String type) {
		String hql = "from Market m where m.typeEr="+Integer.parseInt(type);
		return dao.query(hql);
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
		b.setMarketId(bs.getMarketId());
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
		hql = "from Market where villageId=" + cityId+" and typeEr = "+type+" order by sort ASC";
		List<Market> list = null;
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

}
