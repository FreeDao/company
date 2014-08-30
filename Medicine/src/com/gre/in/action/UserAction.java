package com.gre.in.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.common.BaseAction;
import com.gre.common.CommonUtil;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.FeedBack;
import com.gre.common.model.UpdateInfo;
import com.gre.common.model.User;
import com.gre.common.model.UserCollect;
import com.gre.in.service.UserService;

@Component("userAction")
@Scope("prototype")
public class UserAction extends BaseAction{

	@Autowired
	private UserService userService;
	
	private Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 用户登陆
	 * @return
	 */
	public String login(){
		try {
			String userName = request.getParameter("userName");
			String passWord = request.getParameter("passWord");
			String type = request.getParameter("type");
			User user;
			if("qq".equals(type)){
				 user = userService.findUser(userName);
				if( null == user ){
					User u = new User();
					u.setUserName(userName);
					userService.saveOrUpdateObject(u);
					JSONObject json = JSONObject.fromObject(u);
					map.put("results", json);
					map.put("responseCode", 0);
				}else{
					user.setUserName(userName);
					JSONObject json = JSONObject.fromObject(user);
					map.put("results", json);
					map.put("responseCode", 0);
				}
			}else {
				user = userService.findUser(userName,CommonUtil.Md5(passWord));
				if(null != user){
					JSONObject json = JSONObject.fromObject(user);
					map.put("results", json);
					map.put("responseCode", 0);
				}else{
					map.put("responseCode", 1);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("登陆错误！");
			map.put("responseCode", 2);
		}
		return SUCCESS;
	}
	
	/**
	 * 用户注册
	 * @return
	 */
	public String register(){
		try {
			String userName = request.getParameter("userName");
			String passWord = request.getParameter("passWord");
			User user = userService.findUser(userName);
			if(null == user){
				User u = new User();
				u.setUserName(userName);
				u.setPassWord(CommonUtil.Md5(passWord));
				userService.saveOrUpdateObject(u);
				JSONObject json = JSONObject.fromObject(u);
				map.put("results", json);
				map.put("responseCode", 0);
			}else{
				map.put("responseCode", 1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("注册失败！");
			map.put("responseCode", 2);
		}
		return SUCCESS;
	}
	
	/**
	 * 信息是否收藏
	 * @return
	 */
	public String isCollect(){
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			int collectId = Integer.parseInt(request.getParameter("collectId"));
			int type = Integer.parseInt(request.getParameter("type"));
			
			UserCollect userCollect = userService.findCollect(userId,collectId,type);
			
			if(null == userCollect){
				map.put("isCollect", 0);
			}else{
				map.put("isCollect", 1);
			}
			
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("收藏功能出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 用户收藏添加/删除
	 * @return
	 */
	public String toggleCollect(){
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			int collectId = Integer.parseInt(request.getParameter("collectId"));
			int type = Integer.parseInt(request.getParameter("type"));
			
			UserCollect userCollect = userService.findCollect(userId,collectId,type);
			
			if(null == userCollect){
				userCollect = new UserCollect();
				userCollect.setUserId(userId);
				userCollect.setCollectId(collectId);
				userCollect.setType(type);
				userService.saveOrUpdateObj(userCollect);
			}	else{
				userService.delObj(userCollect);
			}
				map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("收藏功能出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询我的收藏
	 * @return
	 */
	public String myCollect(){
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			int type = Integer.parseInt(request.getParameter("type"));
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			
			JSONArray ja = new JSONArray();
			
			PageUtil pu = new PageUtil();
			
			pu = userService.findCollectList(userId,type,pageNow,limit);
			ja = JSONArray.fromObject(pu.getData());
			
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("results", ja);
			map.put("responseCode", 0);
			
		} catch (Exception e) {
			// TODO: handle exception.
			log.error("保存商户信息错误！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 提交反馈信息
	 * @return
	 */
	public String saveFeedBack(){
		try {
			String content = "";
			if(null != content){
				content = CommonUtil.changeIos8859ToUtf8(request.getParameter("content"));
			}
			String email = request.getParameter("email");
			
			FeedBack fb = new FeedBack();
			System.out.println(DateUtil.getNowString("yyyy-MM-dd"));
			fb.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			fb.setContent(content);
			fb.setEmail(email);
			
			userService.saveOrUpdateObject(fb);
			
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("保存反馈信息错误！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询是否需要更新信息
	 * @return
	 */
	public String isUpdate(){
		try {
			String version = request.getParameter("version");
			int platform = Integer.parseInt(request.getParameter("platform"));
			
			 UpdateInfo ui  = userService.findUpdateInfo(version,platform);
			 
			 if( null == ui ){
				 ui = userService.findUpdateInfo(platform);
				 if(null == ui){
					 ui = new UpdateInfo();
					 ui.setContent("初始化");
					 ui.setIsForced(0);
					 ui.setPlatForm(platform);
					 ui.setVersion("0.0");
					 userService.saveOrUpdateObj(ui);
				 }
				 map.put("version", ui.getVersion());
				 map.put("content", ui.getContent());
				 map.put("url", ui.getUrl());
				 map.put("isForced", ui.getIsForced());
				 map.put("responseCode", 0);
			 }else{
				 map.put("responseCode", 1);
			 }
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询更新功能出错！");
			map.put("responseCode", 2);
		}
		return SUCCESS;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
