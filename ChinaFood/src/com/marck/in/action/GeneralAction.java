package com.marck.in.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.common.BaseAction;
import com.marck.common.CommonUtil;
import com.marck.common.model.Comment;
import com.marck.common.model.Feedback;
import com.marck.common.model.Info;
import com.marck.common.model.Region;
import com.marck.common.model.Type;
import com.marck.common.model.User;
import com.marck.common.model.Version;
import com.marck.in.service.CommentService;
import com.marck.in.service.GeneralService;

@Component("generalAction")
@Scope("prototype")
public class GeneralAction extends BaseAction {

	@Autowired
	private GeneralService generalService;
	
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private Version version;
	private Feedback feedback;
	private Region region;
	private Type type;
	private User user;
	private Integer pageNo;
	private Integer pageNum;
	
	/**
	 * 查看版本信息
	 */
	public String version(){
		try {
			
			String p = request.getParameter("p");
			
			if( version == null || CommonUtil.validParams(p,version.getVersion()) ){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			generalService.checkVersion(version,p,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询评论列表异常",e);
			map.put("msg", "查询异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 用户反馈
	 * @return
	 */
	public String feedback(){
		try {
			
			if( feedback == null || CommonUtil.validParams(feedback.getContent(),feedback.getEmail()) ){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			generalService.addFeedBack(feedback,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("反馈信息异常",e);
			map.put("msg", "反馈信息异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}

	/**
	 * 查询区域
	 * @return
	 */
	public String region(){
		try {
			
			if( region == null || CommonUtil.validParams(region.getLevel()) ){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			generalService.setRegion(region,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询区域异常",e);
			map.put("msg", "查询区域异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询类型
	 * @return
	 */
	public String type(){
		try {
			
			if( type == null || CommonUtil.validParams(type.getLevel(),type.getType()) ){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			generalService.setType(type,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询区域异常",e);
			map.put("msg", "查询区域异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String changePassWord(){
		try {
			
			if( user == null || CommonUtil.validParams(user.getPhone(),user.getPassWord(),user.getCaptcha()) ){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			generalService.changePassWord(user,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("修改密码异常",e);
			map.put("msg", "修改密码异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询资讯跳转内文页面
	 * @return
	 */
	public String info(){
		try {
			
			String id = request.getParameter("id");
			
			if( CommonUtil.validParams(id) ){
				return SUCCESS;
			}else{
				Info info = generalService.getInfo(Integer.parseInt(id));
				request.setAttribute("info", info);
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("跳转内文页异常",e);
		}
		return ERROR;
	}
	
	public Integer getPageNo() {
		return pageNo;
	
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageNum() {
		return pageNum;
	
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Map<String, Object> getMap() {
		return map;//null == map?"":map;
	
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Version getVersion() {
		return version;
	
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public Feedback getFeedback() {
		return feedback;
	
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public Region getRegion() {
		return region;
	
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Type getType() {
		return type;
	
	}

	public void setType(Type type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	
	}

	public void setUser(User user) {
		this.user = user;
	}

}
