package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Images;
import com.era.orm.Product;
import com.era.orm.Seller;
import com.era.service.ProductService;

public class ProductServiceImpl implements ProductService
{
	private BaseDAO dao;
	
	@Override
	public int numProduct(String name, int sellerid) 
	{
		String hql = "";
		if(name == null || name.equals(""))
		{
			hql="select count(*) from product pro,seller sel where sel.id = pro.seller and sel.typeId = "+sellerid;
		}
		else
		{
			hql="select count(*) from product pro,seller sel where sel.id = pro.seller and pro.name like '%"+name+"%' and sel.typeId = "+sellerid;
		}
		int count = dao.countBySql(hql);
		return count;
	}

	@Override
	public List<Object> selProduct(String name, int sellerid, int pageNo,
			int pageSize)
			{
		String hql = "";
		if(name == null || name.equals(""))
		{
			hql="from Product pro,Seller sel where  sel.id = pro.seller and sel.typeId = "+sellerid;
		}
		else
		{
			hql="from Product pro,Seller sel where  sel.id = pro.seller and pro.name like '%"+name+"%' and sel.typeId = "+sellerid;
		}
		List<Object> list = dao.query(hql,pageNo, pageSize);
		return list;
	}
	@Override
	public List<Object> selImages(Integer id,String name,int pageNo, int pageSize)
	{
		String hql="";
		if(name == null ||name.equals(""))
		{
			hql = "from Images ima,Product pro where ima.type = 2 and ima.compositeId = "+id+" and pro.id = ima.compositeId";
		}
		else
		{
			hql = "from Images ima,Product pro where ima.type = 2 and ima.compositeId = "+id+" and pro.id = ima.compositeId and pro.name like '%"+name+"%'";
		}
		List<Object> list = dao.query(hql,pageNo, pageSize);
		return list;
	}
	@Override
	public Integer numProductImage(Integer id, String name)
	{
		String hql = "";
		if(name == null || name.equals(""))
		{
			hql="select count(*) from imges ima,product pro where ima.type = 2 and ima.compositeId = "+id+" and pro.id = ima.compositeId";
		}
		else
		{
			hql="select count(*) from imges ima,product pro where ima.type = 2 and ima.compositeId = "+id+" and pro.id = ima.compositeId and pro.name like '%"+name+"%'";
		}
		int count = dao.countBySql(hql);
		return count;
	}
	@Override
	public int numImagesOrr(String name, int sellerid)
	{
		String hql = "";
		if(name == null || name.equals(""))
		{
			hql="select count(*) from imges ima,seller sel where sel.typeId = "+sellerid+" and ima.type = 1 and ima.compositeId = sel.id";
		}
		else
		{
			hql="select count(*) from imges ima,seller sel where  sel.typeId = "+sellerid+" and ima.type = 1 and sel.titile like '%"+name+"%' and ima.compositeId = sel.id";
		}
		int count = dao.countBySql(hql);
		return count;
	}

	@Override
	public List<Object> selImagesOrr(String name, int sellerid, int pageNo,
			int pageSize) {
		String hql = "";
		if(name == null || name.equals(""))
		{
			hql="from Images ima,Seller sel where sel.typeId = "+sellerid+" and ima.type = 1 and ima.compositeId = sel.id";
		}
		else
		{
			hql=" from Images ima,Seller sel where  sel.typeId = "+sellerid+" and ima.type = 1 and sel.titile like '%"+name+"%' and ima.compositeId = sel.id";
		}
		List<Object> list = dao.query(hql,pageNo, pageSize);
		return list;
	}
	@Override
	public List<Seller> selSellerAll(String sellerId) 
	{
		List<Seller> list = dao.query("from Seller where id in(" + sellerId
					+ ")");
		return list;
	}
	@Override
	public boolean addProduct(Product product)
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(product);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public Product selProductId(Integer id)
	{
		Product product = (Product) dao.loadById(Product.class, id);
		return product;
	}
	@Override
	public boolean delProduct(Integer id)
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
	public boolean delImages(Integer id) {
		boolean flag = false;
		try {
			dao.delById(Images.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean addImages(Images iamges)
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(iamges);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public List<Object> selSellers(Integer typeId) {
		// TODO Auto-generated method stub
		String hql = "from Seller s where s.typeId = "+typeId;
		List<Object> list = dao.query(hql);
		return list;
	}
	
	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

}
