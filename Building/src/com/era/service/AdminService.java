package com.era.service;

import java.util.List;

import com.era.orm.Admin;
import com.era.orm.Collect;
import com.era.orm.Image;
import com.era.orm.Information;
import com.era.orm.Regionallocations;
import com.era.orm.Seller;
import com.era.orm.SupplyAndDemand;
import com.era.orm.Type;
import com.era.orm.User;


public interface AdminService 
{
	/**
	 * 用户登陆
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public Admin login(String userName,String passWord);
	/**
	 * 用户信息
	 * @param id
	 * @return
	 */
	public User selUserId(int id);
	/**
	 * 管理员信息
	 * @param id
	 * @return
	 */
	public Admin selAdimId(int id);
	/**
	 * 修改用户信息
	 * @return
	 */
	public boolean updateUser(Admin admin);
	/**
	 * 查询所有用户列表
	 * @param name
	 * @return
	 */
	public Integer numUserAll(String name);
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> selUserAll(String name,int pageNo, int pageSize);
	/**
	 * 删除用户
	 * @return
	 */
	public boolean delUser(Integer id);
	
	public boolean delArea(int id);
	
	public List<Object> selCity();
	
	public int numberArea();
	
	public boolean isArea(String areaName);
	
	public boolean addArea(Regionallocations area);
	
	public List<Object> selArea(String areaName,int pageNo, int pageSize);
	
	/**
	 * 查询全部收藏条数
	 */
	public int numberComment();
	
	/**
	 * 查询所有的收藏
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Collect> selComment(String id, int pageNo, int pageSize);
	
	/**
	 * 删除收藏
	 * @param id
	 * @return
	 */
	public boolean delComment(int id);
	
	/**
	 * 查询全部资讯条数
	 */
	public int numberInformation();
	/**
	 * 查询所有的资讯
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Information> selInformation(String title, int pageNo, int pageSize);
	
	public boolean addInformation(Information information);
	
	public int numberType();
	
	public List<Object> selType();
	
	public boolean addSeller(Seller seller);
	
	public List<Type> selType(String name,int pageNo, int pageSize);
	
	public boolean addType(Type type);
	
	public boolean delType(int id);
	
	/**
	 * 删除资讯
	 * @param id
	 * @return
	 */
	public boolean delInformation(int id);
	/**
	 * 上传图片
	 * @return
	 */
	public boolean updateImage(Image image);
	
	/**
	 * 查询图片条数
	 * @param id
	 * @return
	 */
	public Integer numImageAll(String id);
	/**
	 * 查询图片
	 * @return
	 */
	public List<Image> selImageAll(String id,int pageNo, int pageSize);
	/**
	 * 删除图片
	 * @param id
	 * @return
	 */
	public boolean delImage(int id);
	
	/**
	 * 获取城市的名称
	 * @return
	 */
	public Integer numAreaAll(String name);
	
	public Integer numSeller(String sellerName);
	
	@SuppressWarnings("rawtypes")
	public List selSeller(String SellerName,int pageNo, int pageSize);
	
	public boolean delSeller(int id);
	
	public Seller selSellerById(String id);
	
	public List<Object> selUser();
	
	public List<Object> selSeller();
	
	public SupplyAndDemand selSupplyById(String id);
	
	public boolean delSupply(int id);
	
	public Integer numSupply(String title);
	
	public List<SupplyAndDemand> selSupply(String title,int pageNo, int pageSize);
	
	public boolean addSupply(SupplyAndDemand supply);
}
