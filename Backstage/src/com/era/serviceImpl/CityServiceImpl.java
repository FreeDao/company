package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.City;
import com.era.orm.CityUser;
import com.era.orm.Custom;
import com.era.orm.Village;
import com.era.service.CityService;
import com.era.util.BaseUtils;
import com.era.util.DateUtil;
import com.era.util.LoginUser;

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
		List<City> list = dao.query(hql,pageNo,pageSize);
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
	public List<Village> selVillage(String parameter)
	{
		hql = "from Village where cityId = "+parameter;
		List<Village> list = dao.query(hql);
		return list;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Village> selVillageOr(String parameter, Integer valueOf,
			Integer valueOf2) {
		String hql="from Village where 1 = 1";
		if(parameter == null || parameter.equals(""))
		{
			
		}
		else
		{
			valueOf = 1;
			valueOf2 = 15;
			hql+=" and name like '%"+parameter+"%'";
		}
		List<Village> list = dao.query(hql,valueOf,valueOf2);
		return list;
	}

	@Override
	public int numberVillageOr(String parameter)
	{
		if (parameter == null || parameter.equals(""))
		{
			hql = "select count(*) from Village";
		} else {
			hql = "select count(*) from Village where name='%" + parameter + "%'";
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public boolean delVillageOr(int parseInt) {
		boolean flag = false;
		try {
			dao.delById(Village.class, parseInt);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addVillage(Village vill) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(vill);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Village selVillageById(String id) {
		// TODO Auto-generated method stub
		return (Village) dao.loadById(Village.class, Integer.parseInt(id));
	}

	@Override
	public void addOrUpdateCity(LoginUser lu,String id, String name, String iphone,
			String villageId) {
		// TODO Auto-generated method stub
		Custom c ;
		if( null == id || "".equals(id) ){
			c = new Custom();
		}else{
			c = (Custom) dao.loadById(Custom.class, Integer.parseInt(id));
		}
		c.setName(name);
		c.setIphone(iphone);
		c.setAddtime(DateUtil.getNowString("yyyy-MM-dd"));
		c.setVillageId(Integer.parseInt(villageId));
		dao.saveOrUpdate(c);
	}

	@Override
	public void delObj(Class<?> clzz,Integer parseInt) {
		// TODO Auto-generated method stub
		try {
			dao.delById(clzz, parseInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CityUser> selCityUser(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		String hql ="select new CityUser(cu.id,cu.userName,cu.passWord,cu.cityId,cu.addTime,c.cityName,c.cityWord) from City c,CityUser cu where c.id = cu.cityId";
		return dao.query(hql,page,rows);
	}

	@Override
	public int numberCityUser() {
		// TODO Auto-generated method stub
		String hql ="select new CityUser(cu.id,cu.userName,cu.passWord,cu.cityId,cu.addTime,c.cityName,c.cityWord) from City c,CityUser cu where c.id = cu.cityId";
		return dao.query(hql).size();
	}

	@Override
	public String addOrUpdateCityUser(String id, String userName,
			String passWord, String cityId) {
		// TODO Auto-generated method stub
		String hql = "from CityUser cu where cu.userName='"+userName+"'";
		if(dao.query(hql).size() > 0){
			return "该用户名已存在！";
		}
		CityUser c = new CityUser();
		if(id != null && !"".equals(id)){
			c = (CityUser) dao.loadById(CityUser.class, Integer.parseInt(id));
		}else{
			c.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			c.setUserName(userName);
			c.setPassWord(passWord);
		}
		c.setCityId(Integer.parseInt(cityId));
		dao.saveOrUpdate(c);
		return null;
	}

	@Override
	public String findVillageById(String id) {
		// TODO Auto-generated method stub
		Village v = (Village) dao.loadById(Village.class, Integer.parseInt(id));
		if( null != v ){
			return "该编码已经存在！";
		}
		return null; 
	}

	@Override
	public String findCityById(String id) {
		// TODO Auto-generated method stub
		City c = (City) dao.loadById(City.class, Integer.parseInt(id));
		if( null != c ){
			return "该编码已经存在！";
		}
		return null;
	}
}
