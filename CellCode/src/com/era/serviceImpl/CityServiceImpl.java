package com.era.serviceImpl;

import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;

import com.era.dao.BaseDAO;
import com.era.orm.Advertisement;
import com.era.orm.City;
import com.era.orm.Village;
import com.era.service.CityService;
import com.era.util.BaseUtils;

public class CityServiceImpl implements CityService {
	private String hql = "";
	private BaseDAO dao;

	/**
	 * 查询所有城市
	 */
	@Override
	public List<City> getCityAll() {
		hql = "from City";
		List<City> list = dao.query(hql);
		return list;
	}

	/**
	 * 查询单个城市
	 */
	@Override
	public City getCity(String cityName, int cityId) {
		if (cityName != null) {
			hql = "from City where cityName='" + cityName + "'";
		} else if (cityId > 0) {
			hql = "from City where id=" + cityId;
		}
		City city = (City) dao.loadObject(hql);
		return city;
	}
	/**
	 * 查询所有城市当中最后一次操作时间
	 */
	@Override
	public String getCityDate() {
		hql = "select addTime from City order by addtime DESC";
		
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
			hql = "select UNIX_TIMESTAMP('"+getCityDate()+"')-UNIX_TIMESTAMP('"+oldDate+"') as timecha";
			Integer timeCha = Integer.parseInt(dao.querySQL(hql).get(0)+"");
			if(timeCha > 0){
				flag = true;
			}else{
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<City> selCity(String city, int pageNo, int pageSize) {
		if (city == null || city.equals("")) {
			hql = "from City";
		} else {
			hql = "from City where cityName='" + city + "'";
		}
		List<City> list = dao.query(hql);
		return list;
	}
	
	@Override
	public int numberCity(String city) {
		if (city == null || city.equals("")) {
			hql = "select count(*) from City";
		} else {
			hql = "select count(*) from City where cityName='%" + city + "%'";
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public boolean delCity(int id) {
		boolean flag = false;
		try {
			dao.delById(City.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addCity(City city) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(city);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public List<Village> selVillage(String keyword,String cityId,String log,String dim, String pageNo, String pageNum)
	{
		hql = "from Village where cityId = "+cityId;
		
		if( null != keyword && !"".equals(keyword)){
			keyword = BaseUtils.changeIos8859ToUtf8(keyword);
			String[] temp = PinyinHelper.toHanyuPinyinStringArray(keyword.charAt(0));
			if( null != temp ){
				hql += " and name like '%"+keyword+"%'";
			}else{
				hql += " and py like '";
				for (int i = 0; i < keyword.length(); i++) {
					hql += keyword.substring(i, i+1)+"%";
				}
				hql += "'";
			}
		}
		hql += " order by ACOS(SIN(("+dim+" * 3.1415) / 180 ) *SIN((dim * 3.1415) / 180 ) + COS(("+dim+" * 3.1415) / 180 ) * COS(( dim * 3.1415) / 180 ) *COS(("+log+" * 3.1415) / 180 - (log * 3.1415) / 180 ) ) * 6380 asc";
		List<Village> list ;
		try {
			list = dao.query(hql,Integer.parseInt(pageNo),Integer.parseInt(pageNum));
		} catch (Exception e) {
			// TODO: handle exception
			list = dao.query(hql);
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
	public List<Advertisement> findAdvertisement(int id) {
		// TODO Auto-generated method stub
		List<Advertisement> as = dao.listAll("Advertisement");
		return as;
	}

	
}
