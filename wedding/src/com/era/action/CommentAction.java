package com.era.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Comment;
import com.era.orm.Feature;
import com.era.orm.User;
import com.era.service.CommentService;
import com.era.service.FeatureService;
import com.era.service.UserService;
import com.era.util.BaseUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CommentAction extends ActionSupport implements ModelDriven<Comment>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	private Comment model = new Comment();
	
	private CommentService commentService;
	private UserService userService;
	private FeatureService featureService;
	private Map<String,Object> map = new HashMap<String,Object>();
	
	/**
	 * 添加评论
	 * @return
	 */
	public String addComment()
	{
		String msg = "";
		try 
		{
			if(request.getParameter("userId") == null || request.getParameter("userId").equals(""))
			{
				model.setUserName("游客");
			}
			else
			{
				User user = userService.selUserId(Integer.valueOf(request.getParameter("userId")));
				model.setUserName(user.getUserName());
			}
			if(request.getParameter("featureId") == null || request.getParameter("featureId").equals(""))
			{
				msg = "景点ID为空";
				map.put("responseCode", "1");
				map.put("msg", msg);
				return SUCCESS;
			}
			Feature feature = featureService.selFeatureId(Integer.valueOf(request.getParameter("featureId")));
			if(feature == null || feature.equals(""))
			{
				msg = "未找到景点";
				map.put("responseCode", "1");
				map.put("msg", msg);
				return SUCCESS;
			}
			if(BaseUtils.isChinaese(request.getParameter("comment")))
			{
				model.setComment(new String(request.getParameter("comment").getBytes("ISO-8859-1"), "UTF-8"));
			}
			else
			{
				model.setComment(request.getParameter("comment"));
			}
			model.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
			model.setFeature(feature.getFeatureName());
			model.setFeatureId(Integer.valueOf(request.getParameter("featureId")));
			boolean bool = commentService.addComment(model);
			if(bool)
			{
				JSONArray array = JSONArray.fromObject(model);
				map.put("results",array);
				map.put("responseCode", "0");
			}
			else
			{
				map.put("responseCode", "1");
			}
		} 
		catch (Exception e) 
		{
			map.put("responseCode", "1");
			map.put("msg", "添加异常");
		}
		return SUCCESS;
	}
	/**
	 * 通过景点ID查询评论
	 * @return
	 */
	public String selCommnetId()
	{
		try 
		{
			List<Comment> list = commentService.listComment(Integer.valueOf(request.getParameter("featureId")),request.getParameter("pageNo"),request.getParameter("pageNum"));
			JSONArray array = JSONArray.fromObject(list);
			if(list.size()>0)
			{
				map.put("def", "0");
			}
			else
			{
				map.put("def", "1");
			}
			map.put("lists",array);
			map.put("responseCode", "0");
		}
		catch (Exception e)
		{
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Comment getModel() {
		return model;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public void setMap(Map<String, Object> map) {
		this.map = map;
	}


	public FeatureService getFeatureService() {
		return featureService;
	}


	public void setFeatureService(FeatureService featureService) {
		this.featureService = featureService;
	}

}
