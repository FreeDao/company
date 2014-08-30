package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Activities;
import com.era.orm.Product;
import com.era.orm.Recommend;
import com.era.orm.RecommendType;
import com.era.service.ProductService;

public class ProductServiceImpl implements ProductService{
	private BaseDAO dao;
	

	@Override
	public int numberProduct(String id)
	{
		String hql = "";
		if(id == null || id.equals(""))
		{
			hql = "select count(*) from Product";
		}
		else
		{
			hql = "select count(*) from Product where seller="+Integer.valueOf(id);
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public List<Product> selProductId(String id,int pageNo, int pageSize)
	{
		String hql = "";
		if(id == null || id.equals(""))
		{
			hql = "from Product";
		}
		else
		{
			hql = "from Product where seller="+Integer.valueOf(id);
		}
		List<Product> list = dao.query(hql,pageNo, pageSize);
		return list;
	}

	@Override
	public boolean delProduct(int id)
	{
		boolean flag = false;
		try {
			dao.delById(Product.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public boolean addProduct(Product pro)
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(pro);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Product selProductId(int id)
	{
		Product user = new Product();
		user = (Product) dao.loadById(Product.class,id);
		return user;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public int selRecommendNum(String name,int cityId) {
		String hql = "";
		if(cityId == 0)
		{
			if(name == null || name.equals(""))
			{
				hql = "select count(*) from Recommend";
			}
			else
			{
				hql = "select count(*) from Recommend where name like '%"+name+"%'";
			}
		}
		else
		{
			if(name == null || name.equals(""))
			{
				hql = "select count(*) from Recommend where cityId = "+cityId;
			}
			else
			{
				hql = "select count(*) from Recommend where name like '%"+name+"%' and cityId = "+cityId;
			}
		}
		
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public List<Recommend> selRecommend(String name,int cityId,int pageNo, int pageSize) {
		String hql = "";
		if(cityId == 0)
		{
			if(name == null || name.equals(""))
			{
				hql = "from Recommend";
			}
			else
			{
				hql = "from Recommend where name like '%"+name+"%'";
			}
		}
		else
		{
			if(name == null || name.equals(""))
			{
				hql = "from Recommend where cityId = "+cityId;
			}
			else
			{
				hql = "from Recommend where name like '%"+name+"%' and cityId = "+cityId;
			}
		}
		
		List<Recommend> list = dao.query(hql,pageNo, pageSize);
		return list;
	}

	@Override
	public boolean delRecommend(Integer valueOf) {
		boolean flag = false;
		try {
			dao.delById(Recommend.class, valueOf);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Recommend selRecommendId(String parameter) {
		Recommend user = new Recommend();
		user = (Recommend) dao.loadById(Recommend.class,Integer.valueOf(parameter));
		return user;
	}

	@Override
	public List<RecommendType> selRecommendType(int pageNo, int pageSize) {
		String hql = "";
		List<RecommendType> list = null;
		if(pageNo == 0 && pageSize==0)
		{
			list = dao.query("from RecommendType");
		}
		else
		{
			list = dao.query("from RecommendType",pageNo, pageSize);
		}
		return list;
	}

	@Override
	public boolean addRecommend(Recommend rec) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(rec);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int numberAct()
	{
		String hql = "";
		hql = "select count(*) from Activities";
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public List<Activities> selAct(int pagenum, int i) {
		String hql = "";
		List<Activities> list = dao.query("from Activities",pagenum, i);
		return list;
	}
}
