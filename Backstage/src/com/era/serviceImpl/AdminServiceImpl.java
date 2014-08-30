package com.era.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Admin;
import com.era.orm.Busmarset;
import com.era.orm.Busmarsetmanager;
import com.era.orm.City;
import com.era.orm.CityUser;
import com.era.orm.Seller;
import com.era.orm.Sellermanager;
import com.era.orm.Village;
import com.era.orm.VillageManager;
import com.era.service.AdminService;
import com.era.util.LoginUser;

public class AdminServiceImpl implements AdminService
{
	private BaseDAO dao;
	
	@Override
	public LoginUser adminLogin(String userName, String passWord,Integer type)
	{
		LoginUser lu  = new LoginUser();
		if(type == 1){
			Admin user = (Admin) dao.loadObject("from Admin where adminName ='"+userName+"' and password = '"+passWord+"'");
			if(null == user){
				return null;
			}
			lu.setId(user.getId());
			lu.setUserName(user.getAdminName());
			lu.setPhone(user.getPhone());
			lu.setQq(user.getQq());
			lu.setEmail(user.getEmail());
			lu.setAccountAddtime(user.getAddtime());
			lu.setRole(type);
			lu.setNickName(user.getNickName());
		}else if(type == 2){
			CityUser user = (CityUser) dao.loadObject("from CityUser where userName ='"+userName+"' and password = '"+passWord+"'");
			if(null == user){
				return null;
			}
			City city = (City) dao.loadObject("from City where id ="+user.getCityId());
			lu.setId(user.getId());
			lu.setUserName(user.getUserName());
			lu.setAccountAddtime(user.getAddTime());
			lu.setCityName(city.getCityName());
			lu.setCityWord(city.getCityWord());
			lu.setCityId(city.getId()+"");
			lu.setRole(type);
		}else if(type == 3){
			Busmarsetmanager user = (Busmarsetmanager) dao.loadObject("from Busmarsetmanager where userName ='"+userName+"' and userPwd = '"+passWord+"'");
			if(null == user){
				return null;
			}
			Busmarset busmarset = (Busmarset) dao.loadObject("from Busmarset where id ="+user.getBmsmId());
			lu.setId(user.getId());
			lu.setUserName(user.getUserName());
			lu.setPhone(user.getTelePhone());
			lu.setQq(user.getQq());
			lu.setEmail(user.getEmail());
			lu.setAccountAddtime(user.getAddTime());
			lu.setInfoAddTime(busmarset.getAddTime());
			lu.setRole(type);
			lu.setBmsUserName(busmarset.getBmsUserName());
			lu.setBmsIntroduction(busmarset.getBmsIntroduction());
			lu.setVillageId(busmarset.getVillageId()+"");
			lu.setMarketInfoId(busmarset.getId());
		}else if(type == 4){
			Sellermanager user = (Sellermanager) dao.loadObject("from Sellermanager where userName ='"+userName+"' and userPwd = '"+passWord+"'");
			if(null == user){
				return null;
			}
			Seller seller = (Seller) dao.loadObject("from Seller where id ="+user.getSellerId());
			lu.setId(user.getId());
			lu.setUserName(user.getUserName());
			lu.setPhone(user.getTelePhone());
			lu.setQq(user.getQq());
			lu.setEmail(user.getEmail());
			lu.setAccountAddtime(user.getAddTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			lu.setInfoAddTime(sdf.format(seller.getAddtime()));
			lu.setRole(type);
			lu.setTitile(seller.getTitile());
			lu.setLogo(seller.getLogo());
			lu.setProductId(seller.getVillageId());
			lu.setLog(seller.getLog());
			lu.setDim(seller.getDim());
			lu.setImgesId(seller.getImgesId());
			lu.setBrief(seller.getBrief());
			lu.setPreferential(seller.getPreferential());
			lu.setAddress(seller.getAddress());
			lu.setLevel(seller.getLevel());
			lu.setTypeId(seller.getTypeId());
			lu.setRecruitment(seller.getRecruitment());
			lu.setPrice(seller.getPrice());
			lu.setSort(seller.getSort());
			lu.setShop(seller.getShop());
			lu.setOtherPrice(seller.getOtherPrice());
			lu.setPostage(seller.getPostage());
			lu.setShopId(seller.getShopId());
			lu.setShopIdTwo(seller.getShopIdTwo());
			lu.setSellerInfoId(seller.getId());
		}else if(type == 5){
			VillageManager user = (VillageManager) dao.loadObject("from VillageManager where userName ='"+userName+"' and passWord = '"+passWord+"'");
			if(null == user){
				return null;
			}
			Village v = (Village) dao.loadById(Village.class, user.getVillageId());
			lu.setId(user.getId());
			lu.setUserName(user.getUserName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			lu.setAccountAddtime(sdf.format(user.getAddTime()));
			lu.setInfoAddTime(v.getAddtime());
			lu.setRole(type);
			lu.setVillageId(v.getId()+"");
			lu.setName(v.getName());
			lu.setAddress(v.getAddress());
			lu.setLog(v.getLog());
			lu.setDim(v.getDim());
			lu.setCityId(v.getCityId()+"");
			lu.setAreaId(v.getAreaId());
		}
		return lu;
	}

	@Override
	public List<Admin> selAdminLogin(String userName) {
		List<Admin> list = dao.query("from Admin where adminName ='"+userName+"'");
		return list;
	}

	@Override
	public int numberAdmin(String city)
	{
		String hql = "select count(*) from Admin a,City c where a.cityId = c.id";
		if(city == null || city.equals(""))
		{
			
		}
		else
		{
			hql+=" and c.cityName like '%"+city+"%'";
		}
		int number = dao.countQuery(hql);
		return number;
	}
	@Override
	public List<Object> selAdmin(String city,String pageNo, String pageSize)
	{
		String hql = "select a.adminName,a.phone,a.qq,a.nickName,c.cityName,a.addtime,a.id from Admin a,City c where a.cityId = c.id";
		if(city == null || city.equals(""))
		{
			
		}
		else
		{
			hql+=" and c.cityName like '%"+city+"%'";
		}
		List<Object> list = dao.query(hql,Integer.valueOf(pageNo),Integer.valueOf(pageSize));
		return list;
	}
	
	@Override
	public boolean delAdmin(int id)
	{
		boolean flag = false;
		try {
			dao.delById(Admin.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public Admin selIdAdmin(int id)
	{
		Admin user = new Admin();
		user = (Admin) dao.loadById(Admin.class,id);
		return user;
	}
	
	@Override
	public boolean addAdmin(Admin admin)
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(admin);
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
	public int getTxlTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

}
