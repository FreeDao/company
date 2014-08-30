package com.marck.in.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marck.common.CommonUtil;
import com.marck.common.DateUtil;
import com.marck.common.SystemArguments;
import com.marck.common.dao.HDB;
import com.marck.common.model.Captcha;
import com.marck.common.model.Feedback;
import com.marck.common.model.Info;
import com.marck.common.model.Region;
import com.marck.common.model.SystemAgrs;
import com.marck.common.model.Type;
import com.marck.common.model.User;
import com.marck.common.model.Version;

@Component("generalService")
@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
public class GeneralService {
	
	@Autowired
	private HDB hdb;

	/**
	 * 检查但钱版本信息
	 * @param version
	 * @param p 
	 * @param map
	 */
	public void checkVersion(Version version, String p, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hql = "from Version v where v.platForm = '"+p+"'";
		List<Version> versions = (List<Version>) hdb.findHql(hql);
		if( versions.size() > 0 ){
			Version v = versions.get(0);
			if(v.getVersion().equals(version.getVersion())){
				map.put("code", 0);
				map.put("msg", "当前为最新版本，不需要更新");
				map.put("force", v.getIsForced());
			}else{
				map.put("code", 1);
				map.put("msg", "有新版本，需要更新");
				map.put("force", v.getIsForced());
				map.put("url", v.getUrl());
			}
		}else{
			Version v = new Version();
			v.setContent("初始化");
			v.setIsForced(2);
			v.setPlatForm(p);
			v.setUrl("");
			v.setVersion("1.0.0");
			hdb.saveOrUpdate(v);
			map.put("code", 0);
			map.put("msg", "当前为最新版本，不需要更新");
			map.put("force", v.getIsForced());
		}
	}

	/**
	 * 添加用户反馈信息
	 * @param feedback
	 * @param map
	 */
	public void addFeedBack(Feedback feedback, Map<String, Object> map) {
		// TODO Auto-generated method stub
		feedback.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
		hdb.saveOrUpdate(feedback);
		map.put("msg", "反馈成功");
		map.put("code", 1);
	}

	/**
	 * 设置区域列表
	 * @param region
	 * @param map
	 */
	public void setRegion(Region region, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hql = "";
		List<Region> regions = new ArrayList<Region>();
		if(!CommonUtil.validParams(region.getMenuId())){
			hql = "select l from Menu m,com.marck.common.model.List l where m.listId = l.id and m.id ="+region.getMenuId();
			 com.marck.common.model.List list = (com.marck.common.model.List) hdb.findUniqueHql(hql);
			 if( null == list){
				 map.put("msg", "查询失败，数据库没有对应的menuId，请检查menuId是否正确");
				 map.put("code", 0);
				 return;
			 }
			 
			 List<SystemAgrs> sas = (List<SystemAgrs>) hdb.findHql("from SystemAgrs sa");
			 if( sas.size() < 0 ){
				 map.put("msg", "查询失败，系统尚未初始化");
				 map.put("code", 0);
				 return;
			 }
			 
			 
			 if(sas.get(0).getModel() != 1 && "Commodity".equals(list.getTable()) ){
				 hql = "select distinct r from Commodity c,Region r,Seller s where s.id = c.sellerId ";
				 if(region.getPid() != 0){
					 hql += " and r.pid="+region.getPid();
				 }
				 if(region.getLevel() == 1){
					 hql += " and s.provinceId = r.id ";
				 }else if(region.getLevel() == 2){
					 hql += " and s.cityId = r.id ";
				 }else{
					 hql += " and s.areaId = r.id ";
				 }
			 }else{
				 hql = "select distinct r from "+list.getTable()+" l,Region r where 1=1 ";
				 if(region.getPid() != 0){
					 hql += " and r.pid="+region.getPid();
				 }
				 if(region.getLevel() == 1){
					 hql += " and l.provinceId = r.id ";
				 }else if(region.getLevel() == 2){
					 hql += " and l.cityId = r.id ";
				 }else{
					 hql += " and l.areaId = r.id ";
				 }
			 }
			 
			 regions = (List<Region>) hdb.findHql(hql);
			
		}else{
			hql ="from Region r where r.level = " + region.getLevel();

			if( region.getPid() != 0){
				hql += " and r.pid="+region.getPid();
			}
			
			hdb.findHql(hql);
			regions = (List<Region>) hdb.findHql(hql);
		}
		 map.put("msg", "查询成功");
		 map.put("code", 1);
		 map.put("lists",regions);
	}

	/**
	 * 设置类型列表
	 * @param type
	 * @param map
	 */
	public void setType(Type type, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hql = "";
		List<Type> types = new ArrayList<Type>();
		if(!CommonUtil.validParams(type.getMenuId())){
			hql = "select l from Menu m,com.marck.common.model.List l where m.listId = l.id and m.id ="+type.getMenuId();
			 com.marck.common.model.List list = (com.marck.common.model.List) hdb.findUniqueHql(hql);
			 if( null == list){
				 map.put("msg", "查询失败，数据库没有对应的menuId，请检查menuId是否正确");
				 map.put("code", 0);
				 return;
			 }
			 hql = "select distinct t from "+list.getTable()+" l,Type t where t.type = "+type.getType();
			 if(type.getPid() != 0){
				 hql += " and t.pid="+type.getPid();
			 }
			 if(type.getLevel() == 1){
				 hql += " and l.typeId = t.id ";
			 }else{
				 hql += " and l.subTypeId = t.id ";
			 }
			 types = (List<Type>) hdb.findHql(hql);
		}else{
			hql ="from Type t where t.level = " + type.getLevel()+" and t.type = "+type.getType();
			 if(type.getPid() != 0){
				 hql += " and t.pid="+type.getPid();
			 }
			hdb.findHql(hql);
			types = (List<Type>) hdb.findHql(hql);
		}
		 map.put("msg", "查询成功");
		 map.put("code", 1);
		 map.put("lists",types);
	}

	/**
	 * 根据id获取咨询信息
	 * @param parseInt
	 * @return
	 */
	public Info getInfo(Integer id) {
		// TODO Auto-generated method stub
		return (Info) hdb.find(Info.class, id);
	}

	/**
	 * 修改密码
	 * @param user
	 * @param map
	 */
	public void changePassWord(User user, Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		if(user.getCaptcha().equals(SystemArguments.captcha)){
			changePw(user,map);
			return;
		}
		
		String hql = "from Captcha c where c.phone='"+user.getPhone()+"'";
		
		List<Captcha> captchas = (List<Captcha>) hdb.findHql(hql);
		
		if(captchas.size() <= 0){
			map.put("msg", "没有验证码信息，请先获取验证码");
			map.put("code", 0);
			return;
		}
		
		Captcha c = captchas.get(0);
		
		Long now = new Date().getTime();
		
		if( (now - Long.parseLong(c.getCaptchaTime())) > SystemArguments.captchaTime ){
			map.put("msg", "验证码已过期,请重新获取验证码");
			map.put("code", 0);
		}else if( user.getCaptcha().equals(c.getCaptcha()) && user.getPhone().equals(c.getPhone()) ){
			changePw(user,map);
		}else{
			map.put("msg", "验证码错误");
			map.put("code", 0);
		}
	}

	/**
	 * 修改密码
	 */
	public void changePw(User user,Map<String, Object> map){
		String hql = "from User u where u.phone='"+user.getPhone()+"'";
		
		List<User> users = (List<User>) hdb.findHql(hql);
		if(users.size() <= 0){
			 map.put("msg", "没有该用户");
			 map.put("code", 0);
			 return;
		}
		
		User u = users.get(0);
		u.setPassWord(CommonUtil.Md5(user.getPassWord()));
		hdb.saveOrUpdate(u);
		map.put("msg", "修改成功");
		map.put("code", 1);
	}
	
}
