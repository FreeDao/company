package com.era.service;

import java.util.List;

import com.era.orm.Admin;
import com.era.orm.Area;
import com.era.orm.Feature;
import com.era.orm.Image;
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
	/**
	 * 查询所有景点
	 * @param name
	 * @return
	 */
	public Integer numfeatureAll(String name,String good);
	/**
	 * 查询所有景点
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Object> selfeatureAll(String name,String good,int pageNo, int pageSize);
	/**
	 * 添加修改景点
	 * @param feature
	 * @return
	 */
	public boolean updateFeature(Feature feature);
	/**
	 * 删除景点
	 * @param feature
	 * @return
	 */
	public boolean delFeature(Integer id);
	
	/**
	 * 查询所有地区
	 * @return
	 */
	public List<Area> selAreaAll();
	/**
	 * 通过id查询景点
	 * @param id
	 * @return
	 */
	public Feature selFeatureId(Integer id);
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
	/**
	 * 查询城市
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Area> selAreaAll(String name,int pageNo, int pageSize);
	/**
	 * 删除地区
	 * @param id
	 * @return
	 */
	public boolean delArea(int id);
	/**
	 * 通过id查询地区
	 * @return
	 */
	public Area selAreaId(int id);
	/**
	 * 添加或修改城市
	 * @param area
	 * @return
	 */
	public boolean updateArea(Area area);
	
}
