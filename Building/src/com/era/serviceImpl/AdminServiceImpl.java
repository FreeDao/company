package com.era.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Admin;
import com.era.orm.Collect;
import com.era.orm.Image;
import com.era.orm.Information;
import com.era.orm.Regionallocations;
import com.era.orm.Seller;
import com.era.orm.SupplyAndDemand;
import com.era.orm.Type;
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
			hql="select count(*) from user where email like '%"+name+"%'";
		}
		int count = dao.countBySql(hql);
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<User> selUserAll(String name, int pageNo, int pageSize) 
	{
		String hql = "";
		if(name == null || name.equals(""))
		{
			hql="from User";
		}
		else
		{
			hql="from User where email like '%"+name+"%'";
		}
		List<User> list = dao.query(hql,pageNo, pageSize);
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

	/**
	 * 查询所有地区
	 */
	@SuppressWarnings("unchecked")
	public List<Object> selArea(String areaName,int pageNo, int pageSize) 
	{
		List<Object> list=null;
		if ("".equals(areaName) || areaName==null) 
		{
			String hql = "from Regionallocations a , City c where a.areaId = c.id";
			list=dao.query(hql, pageNo, pageSize);
		} else {
			String hql = "from Regionallocations  a , City c where a.areaId = c.id and areaRegion like '%"+ areaName + "%'" ;
			list=dao.query(hql, pageNo, pageSize);
		}
		return list;
	}
	
	/**
	 * 计算区域数量
	 */
	public int numberArea() 
	{
		String hql="select count(*) from Regionallocations";
		int number = dao.countQuery(hql);
		return number;
	}
	
	/**
	 * 判断区域是否存在
	 */
	public boolean isArea(String areaName)
	{
		boolean flag = false;
		String hql = "from Regionallocations where areaRegion = '" + areaName + "'";
		Object o = dao.loadObject(hql);
		if(o!=null)
		{
			flag = true;
		}else
		{
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 添加地区
	 */
	public boolean addArea(Regionallocations area) 
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(area);
			flag = true;
		} catch (Exception e) 
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;	
	}
	
	/**
	 * 删除地区
	 */
	public boolean delArea(int id)
	{
		boolean flag = false;
		try 
		{
			dao.delById(Regionallocations.class,id);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> selCity() 
	{
		List<Object> list=null;
		String hql = "from City";
		list=dao.query(hql);
		return list;
	}
	
	public int numberComment()
	{
		int number = dao.countQuery("select count(*) from Collect c");
		return number;
	}
	
	/**
	 * 查询所有收藏
	 */
	@SuppressWarnings("unchecked")
	public List<Collect> selComment(String id,int pageNo, int pageSize)
	{
		@SuppressWarnings("rawtypes")
		List list_comment = new ArrayList();
		List<Collect> list=null;
		String hql = "";
		if(id == null || id.equals(""))
		{
			hql="from Collect c";
		}
		else
		{
			hql="from Collect c where c.id like '%"+ id +"%'";
		}
		list = dao.query(hql,pageNo,pageSize);
		for(int i =0;i<list.size();i++)
		{
			Collect comment = new Collect();
			User user = (User)dao.loadById(User.class, list.get(i).getUserId());
			comment.setId(list.get(i).getId());
			comment.setAddtime(list.get(i).getAddtime());
			comment.setEmail(user.getEmail());
			comment.setSupplyId(list.get(i).getSupplyId());
			list_comment.add(comment);
		}
		return list_comment;
	}
	
	public boolean delComment(int id) 
	{
		boolean flag = false;
		try 
		{
			dao.delById(Collect.class,id);
			flag = true;
		} 
		catch (Exception e)
		{
			flag = false;
		}
		return flag;
	}
	
	public int numberInformation()
	{
		int number = dao.countQuery("select count(*) from Information");
		return number;
	}
	
	/**
	 * 查询所有资讯
	 */
	@SuppressWarnings("unchecked")
	public List<Information> selInformation(String title,int pageNo, int pageSize)
	{
		List<Information> list=null;
		if (title == null || title.equals("")) 
		{
			String hql = "from Information";
			list=dao.query(hql, pageNo, pageSize);
		} else {
			String hql = "from Information where title like '%"+ title + "%'" ;
			list=dao.query(hql, pageNo, pageSize);
		}
		return list;
	}
	
	public boolean delInformation(int id) 
	{
		boolean flag = false;
		try 
		{
			dao.delById(Information.class,id);
			flag = true;
		} 
		catch (Exception e)
		{
			flag = false;
		}
		return flag;
	}
	
	public boolean addInformation(Information information) 
	{
		boolean flag = false;
		try
		{
			dao.saveOrUpdate(information);
			flag = true;
		} 
		catch (Exception e) 
		{
			flag = false;
		}
		return flag;
	}
	
	public int numberType() 
	{
		String hql="select count(*) from Type";
		int number = dao.countQuery(hql);
		return number;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> selType() 
	{
		List<Object> list=null;
		String hql = "from Type";
		list=dao.query(hql);
		return list;
	}
	
	public boolean addSeller(Seller seller) 
	{
		boolean flag = false;
		try
		{
			dao.saveOrUpdate(seller);
			flag = true;
		} 
		catch (Exception e) 
		{
			flag = false;
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> selUser() 
	{
		List<Object> list=null;
		String hql = "from User";
		list=dao.query(hql);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> selSeller() {
		List<Object> list = dao.query("from Seller");
		return list;
	}
	
	public SupplyAndDemand selSupplyById(String id)
	{
		SupplyAndDemand supply = null;
		String hql = "";
		try 
		{
			if(id == null || id.equals(""))
			{
				return null;
			}else
			{
				hql = "from SupplyAndDemand where id = '"+id+"'";
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		supply = (SupplyAndDemand)dao.loadObject(hql);
		return supply;
	}
	
	public boolean delSupply(int id) {
		boolean flag = false;
		try {
			dao.delById(SupplyAndDemand.class, id);
			flag = true;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return flag;
	}
	
	public Integer numSupply(String title) 
	{
		String hql = "";
		if(title == null || title.equals(""))
		{
			hql="select count(*) from supplyanddemand";
		}
		else
		{
			hql="select count(*) from supplyanddemand where title like '%"+title+"%'";
		}
		int count = dao.countBySql(hql);
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public List<SupplyAndDemand> selSupply(String title, int pageNo, int pageSize) {
		List<SupplyAndDemand> list_supply = new ArrayList<SupplyAndDemand>();
		String hql = "";
		//推荐
		if(title==null || title.equals(""))
		{
			hql="from SupplyAndDemand";
		}
		else
		{
			hql = "from SupplyAndDemand where title like '%"+title+"%'";
		}
		List<SupplyAndDemand> list = dao.query(hql,pageNo, pageSize);
		for(int i = 0; i < list.size(); i++)
		{
			Seller seller = (Seller)dao.loadById(Seller.class, list.get(i).getSellerId()); 
			SupplyAndDemand supply = new SupplyAndDemand();
			supply.setId(list.get(i).getId());
			supply.setTitle(list.get(i).getTitle());
			supply.setContent(list.get(i).getContent());
			supply.setPrice(list.get(i).getPrice());
			supply.setAddress(list.get(i).getAddress());
			supply.setCityId(list.get(i).getCityId());
			supply.setTypeId(list.get(i).getTypeId());
			supply.setStatue(list.get(i).getStatue());
			supply.setIsfinish(list.get(i).getIsfinish());
			supply.setSellerId(list.get(i).getSellerId());
			supply.setLogo(list.get(i).getLogo());
			supply.setCorporate(seller.getSellerName());
			supply.setInfo(seller.getSellerOwner());
			supply.setPhone(seller.getPhone());
			supply.setQq("");
			supply.setEmail(seller.getEmail());
			supply.setUserId(list.get(i).getUserId());
			supply.setOrderNum(list.get(i).getOrderNum());
			supply.setType(list.get(i).getType());
			supply.setMatureTime(list.get(i).getMatureTime());
			supply.setReleaseTime(list.get(i).getReleaseTime());
			supply.setAddTime(list.get(i).getAddTime());
			supply.setBuyNum(list.get(i).getBuyNum());
			supply.setProductcontent(list.get(i).getProductcontent());
			list_supply.add(supply);
		}
		return list_supply;
	}
	
	@SuppressWarnings("unchecked")
	public List<Type> selType(String name,int pageNo, int pageSize) 
	{
		List<Type> list=null;
		String hql = "";
		if ("".equals(name) || name==null) 
		{
			hql = "from Type";
			
		} else {
			hql = "from Type where name like '%"+ name + "%'" ;
		}
		list=dao.query(hql, pageNo, pageSize);
		return list;
	}
	
	public boolean addType(Type type)
	{
		boolean flag = false;
		try
		{
			dao.saveOrUpdate(type);
			flag = true;
		} 
		catch (Exception e) 
		{
			flag = false;
		}
		return flag;
	}
	
	public boolean delType(int id) {
		boolean flag = false;
		try {
			dao.delById(Type.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
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
		hql="select count(*) from image";
		int count = dao.countBySql(hql);
		return count;
	}

	public Integer numSeller(String sellerName) 
	{
		String hql = "";
		if(sellerName == null || sellerName.equals(""))
		{
			hql="select count(*) from seller";
		}
		else
		{
			hql="select count(*) from seller where sellerName like '%"+sellerName+"%'";
		}
		int count = dao.countBySql(hql);
		return count;
	}
	
	@SuppressWarnings("rawtypes")
	public List selSeller(String sellerName, int pageNo, int pageSize) {
		String hql = "";
		if(sellerName==null || sellerName.equals(""))
		{
			hql="from Seller";
		}
		else
		{
			hql = "from Seller where sellerName like '%"+sellerName+"%'";
		}
		List list = dao.query(hql,pageNo, pageSize);
		return list;
	}
	
	public boolean delSeller(int id) {
		boolean flag = false;
		try {
			dao.delById(Seller.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public Seller selSellerById(String id)
	{
		Seller seller = null;
		String hql = "";
		try 
		{
			if(id == null || id.equals(""))
			{
				return null;
			}else
			{
				hql = "from Seller where id = '"+id+"'";
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		seller = (Seller)dao.loadObject(hql);
		return seller;
	}
	
	@SuppressWarnings("unchecked")
	public List<Image> selImageAll(String id, int pageNo, int pageSize) {
		String hql = "";
		if(id==null || id.equals(""))
		{
			hql="from Image";
		}
		else
		{
			hql = "from Image where id = '"+id+"'";
		}
		List<Image> list = dao.query(hql,pageNo, pageSize);
		return list;
	}

	public boolean delImage(int id) {
		boolean flag = false;
		try {
			dao.delById(Image.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public Integer numAreaAll(String name) 
	{
		String hql = "";
		if(name == null || name.equals(""))
		{
			hql="select count(*) from Area";
		}
		else
		{
			hql="select count(*) from Area where areaName like '%"+name+"%'";
		}
		int count = dao.countBySql(hql);
		return count;
	}

	public boolean addSupply(SupplyAndDemand supply) 
	{
		boolean flag = false;
		try
		{
			dao.saveOrUpdate(supply);
			flag = true;
		} 
		catch (Exception e) 
		{
			flag = false;
		}
		return flag;
	}
}
