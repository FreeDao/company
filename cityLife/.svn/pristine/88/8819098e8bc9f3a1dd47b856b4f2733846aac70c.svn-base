package com.era.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Collect;
import com.era.orm.Comment;
import com.era.service.CollectService;
import com.era.service.CommentService;
import com.era.util.BaseUtils;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CommentAction extends ActionSupport implements
		ModelDriven<Comment>, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	CommentService commentService;
	private List<Object> list;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	Comment model = new Comment();
	private CollectService collectService;
	private String result;

	
	/**
	 * TODO 查询商家评论
	 */
	public void getCommentList() {
		try {
			int number = commentService.countComment(Integer.valueOf(request.getParameter("sellerId")));
			List<Comment> list = commentService.getCommentInfo(Integer.valueOf(request.getParameter("sellerId")),Integer.valueOf(request.getParameter("pageNo")), Integer.valueOf(request.getParameter("pageNum")));
			JSONArray array = JSONArray.fromObject(list);
			result = "{\"responseCode\":\"" + 0 + "\",\"countNum\":\""+ number + "\",\"results\":" + array.toString() + "}";
		} catch (Exception e) {
			e.printStackTrace();
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}

	/**
	 * TODO 查询商家评论条数
	 */
	public void countComment() {
		int sellerId = Integer.parseInt(request.getParameter("sellerId"));
		if (sellerId > 0) {
			int number = commentService.countComment(sellerId);
			if (number > 0) {
				JSONArray array = JSONArray.fromObject(number);
				result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
			} else {
				result = "{\"responseCode\":\"" + 1 + "\"}";
			}
		}else {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}

	/**
	 * TODO 添加评论
	 * @throws UnsupportedEncodingException 
	 */
	public void putComment() throws UnsupportedEncodingException
	{
		String content = "";
			if(BaseUtils.isChinaese(request.getParameter("content")))
			{
				content =  new String(request.getParameter("content").getBytes("ISO-8859-1"),"UTF-8");
			}
			else
			{
				content = request.getParameter("content");
			}
			model.setConent(content);
			model.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
			model.setSellerId(Integer.valueOf(request.getParameter("sellerId")));
			model.setUser("游客");
			model.setLevel(Double.valueOf(request.getParameter("level")));
			boolean flag = commentService.putComment(model);
			if (flag) {
				result = "{\"responseCode\":\"" + 0 + "\"}";
			} else {
				result = "{\"responseCode\":\"" + 1 + "\"}";
			}
		BaseUtils.responseInfo(result);
	}

	/**
	 * TODO 商家查询评论
	 */
	public void getComment() {
				List<Comment> list = commentService.getComment(Integer.valueOf(request.getParameter("sellerId")),Integer.valueOf(request.getParameter("pageNo")),Integer.valueOf(request.getParameter("pageNum")));
				if (list != null && list.size() > 0) {
					JSONArray array = JSONArray.fromObject(list);
					result = "{\"responseCode\":\"" + 0 + "\",\"results\":"
							+ array.toString() + "}";
				} else {
					result = "{\"responseCode\":\"" + 1 + "\"}";
				}
		BaseUtils.responseInfo(result);
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

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getPagesum() {
		return pagesum;
	}

	public void setPagesum(int pagesum) {
		this.pagesum = pagesum;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public CollectService getCollectService() {
		return collectService;
	}

	public void setCollectService(CollectService collectService) {
		this.collectService = collectService;
	}

	public void setModel(Comment model) {
		this.model = model;
	}
}
