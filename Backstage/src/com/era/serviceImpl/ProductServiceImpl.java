package com.era.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Code;
import com.era.orm.Product;
import com.era.orm.Protype;
import com.era.orm.User;
import com.era.service.ProductService;
import com.era.util.BaseUtils;

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
	
	
	/**
	 * TODO 查询获取所有的产品类型名称
	 * 
	 * @param sellerId
	 * @return
	 */
	@Override
	public List getAllProductType(int proId) {
		String  hql = "select id,typeName from Protype where proId = "+proId;
		
		List<Object[]> list = dao.query(hql);
		
		System.out.println("list protype : "+list.size());
		
		List<Protype> list_protype = new ArrayList<Protype>();

		for(Object[] object : list){
			Protype pt = new Protype();
			pt.setId(Integer.parseInt(object[0]+""));
			pt.setTypeName(object[1]+"");

			System.out.println(object[1]);
			
			list_protype.add(pt);
		}
		System.out.println("list_protype : "+list_protype.size());
		return list_protype;
	}
	
	/**
	 * 查询产品类型下面的对应的图片
	 * 
	 * @param sellerId
	 * @return
	 */
	@Override
	public List getProductTypeImgs(int proId,int proTypeId) {
		String hql = "";
		List list_proImgs = new ArrayList();		
		List list = new ArrayList();
		
		if(proTypeId > 0){//查询对应类型
			hql = "select i.imgUrl from Images as i where i.type = 4 and i.compositeId ="+proTypeId;
			list_proImgs = dao.query(hql);
		}else{//proId = -1 查询所有
			/*
			 * 根据产品id查询下面对应所有产品类型id
			 * 然后再根据产品类型id查询对应图片
			 */
			hql = "select id from Protype where proId = "+proId;
			List<Integer> list_proTypeIds = dao.query(hql);
			for(Integer id : list_proTypeIds){
				hql = "select i.imgUrl from Images as i where i.type = 4 and i.compositeId ="+id;
				List list_ids_imgs = dao.query(hql);
				list_proImgs.add(list_ids_imgs);
			}
			
			//在查询完所有的同时，把之前的产品图片也一并查询出来
			hql = "select id from Product where seller = ";
			hql = "select i.imgUrl from Images as i where i.type = 2 and i.compositeId = "+proId;
			List list_ago_imgs = dao.query(hql);
			list_proImgs.add(list_ago_imgs);
		}
		System.out.println("ago "+list_proImgs.toString());
		String str_proImgs = list_proImgs.toString().replace("[]", "").replace("[", "").replace("]","").replace("[],","").replace(",[]","");
		str_proImgs = BaseUtils.del_space(str_proImgs);
		System.out.println("after "+str_proImgs);
		list.add(str_proImgs);
		
		return list;
	}
	@Override
	public Code selCodeSms(String iphone, String code)
	{
		List<Code> code1 = dao.query("from Code where iphone = '"+iphone+"' and code = '"+code+"'");
		if(code1.size()>0)
		{
			return code1.get(0);
		}
		else
		{
			return null;
		}
	}
	@Override
	public boolean addCode(Code code) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(code);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addUserCode(User user) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(user);
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
	public User selUserY(String phone) {
		List<User> code1 = dao.query("from User where email = '"+phone+"'");
		if(code1.size()>0)
		{
			return code1.get(0);
		}
		else
		{
			return null;
		}
	}

	@Override
	public int selCodeAddtime(String parameter,String date1) {
		String hql = null;
		hql = "select count(*) from Code where sendTime > '"+date1+"' and  iphone = '"+parameter+"'";
		int number = dao.countQuery(hql);
		return number;
	}
}
