package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Admin;
import com.era.orm.Area;
import com.era.orm.Feature;
import com.era.orm.Image;
import com.era.orm.User;
import com.era.service.AdminService;

public class AdminServiceImpl implements AdminService 
{
	private BaseDAO dao;

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public Admin login(String userName, String passWord) 
	{
		Admin user = null;
		try {
			String hql = "";
			hql = "from Admin where userName = '"+userName+"' and passWord='" + passWord + "'";
			user = (Admin) dao.loadObject(hql);
		} catch (Exception e) {
			return null;
		}
		return user;
	}

	@Override
	public User selUserId(int id) {
		User manager = (User) dao.loadById(User.class, id);
		return manager;
	}

	@Override
	public Admin selAdimId(int id) {
		Admin manager = (Admin) dao.loadById(Admin.class, id);
		return manager;
	}

	@Override
	public boolean updateUser(Admin admin) {
		boolean flag = false;
		try{
			dao.saveOrUpdate(admin);
			flag = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public Integer numUserAll(String name)
	{
		String hql = "";
		if(name == null || name.equals(""))
		{
			hql="select count(*) from user";
		}
		else
		{
			hql="select count(*) from user where userName = '"+name+"'";
		}
		int count = dao.countBySql(hql);
		return count;
	}

	@Override
	public List<User> selUserAll(String name, int pageNo, int pageSize) 
	{
		String hql = "";
		if(name == null || name.equals(""))
		{
			hql="from User";
		}
		else
		{
			hql="from User where userName = '"+name+"'";
		}
		List<User> list = dao.query(hql,pageNo, pageSize);
		return list;
	}

	@Override
	public Integer numfeatureAll(String name, String good) {
		String hql = "";
		if(name == null || name.equals(""))
		{
				hql="select count(*) from feature fea,area area where fea.groom = "+good+" and area.id = fea.areaId";
		}
		else
		{
			
				hql="select count(*) from feature fea,area area where fea.featureName = '"+name+"' and fea.groom = "+good+" and area.id = fea.areaId";
		}
		int count = dao.countBySql(hql);
		return count;
	}

	@Override
	public List<Object> selfeatureAll(String name, String good, int pageNo,
			int pageSize) {
		String hql = "";
		if(name == null || name.equals(""))
		{
				//推荐
				hql="from Feature fea,Area area where fea.groom = "+good+" and area.id = fea.areaId";
			
		}
		else
		{
				hql="from Feature fea,Area area where fea.featureName = '"+name+"' and fea.groom =  "+good+" and area.id = fea.areaId";
			
		}
		List<Object> list = dao.query(hql,pageNo, pageSize);
		return list;
	}
	@Override
	public boolean delUser(Integer id) {
		boolean flag = false;
		try {
			dao.delById(User.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateFeature(Feature feature) {
		boolean flag = false;
		try{
			dao.saveOrUpdate(feature);
			flag = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delFeature(Integer id) {
		boolean flag = false;
		try {
			dao.delById(Feature.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Area> selAreaAll() {
		List<Area> list = dao.query("from Area");
		return list;
	}

	@Override
	public Feature selFeatureId(Integer id)
	{
		Feature manager = (Feature) dao.loadById(Feature.class, id);
		return manager;
	}
	@Override
	public boolean updateImage(Image image) {
		boolean flag = false;
		try{
			dao.saveOrUpdate(image);
			flag = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Integer numImageAll(String id) {
		String hql = "";
	
				hql="select count(*) from image where type = 1 and compositeId="+id;

	
		int count = dao.countBySql(hql);
		return count;
	}

	@Override
	public List<Image> selImageAll(String id, int pageNo, int pageSize) {
		String hql = "";
		//推荐
		hql="from Image where type = 1 and compositeId="+id;
		List<Image> list = dao.query(hql,pageNo, pageSize);
		return list;
	}

	@Override
	public boolean delImage(int id) {
		boolean flag = false;
		try {
			dao.delById(Image.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Integer numAreaAll(String name) 
	{
		String hql = "";
		if(name == null || name.equals(""))
		{
			hql="select count(*) from area";
		}
		else
		{
			hql="select count(*) from area where areaName like '%"+name+"%'";
		}
		int count = dao.countBySql(hql);
		return count;
	}

	@Override
	public List<Area> selAreaAll(String name, int pageNo, int pageSize) {
		String hql = "";
		//推荐
		
		if(name == null || name.equals(""))
		{
			hql="from Area";
		}
		else
		{
			hql="from Area where areaName like '%"+name+"%'";
		}
		List<Area> list = dao.query(hql,pageNo, pageSize);
		return list;
	}

	@Override
	public boolean delArea(int id) {
		boolean flag = false;
		try {
			dao.delById(Area.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Area selAreaId(int id) {
		Area manager = (Area) dao.loadById(Area.class, id);
		return manager;
	}

	@Override
	public boolean updateArea(Area area) {
		boolean flag = false;
		try{
			dao.saveOrUpdate(area);
			flag = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
