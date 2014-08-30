package com.marck.in.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.common.BaseAction;
import com.marck.common.CommonUtil;
import com.marck.common.model.Comment;
import com.marck.in.service.CommentService;

@Component("commentAction")
@Scope("prototype")
public class CommentAction extends BaseAction{

	@Autowired
	private CommentService commentService;
	
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private Comment comment;
	private Integer pageNo;
	private Integer pageNum;
	
	/**
	 * 获取评论列表
	 */
	public String list(){
		try {
			System.out.println(comment.getAddTime());
			
			if(null == comment  || CommonUtil.validParams(comment.getPid(),comment.getMenuId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			commentService.setCommentList(comment,map,pageNo,pageNum);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询评论列表异常",e);
			map.put("msg", "查询异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 添加评论
	 * @return
	 */
	public String add(){
		try {
			
			if(null == comment  || CommonUtil.validParams(comment.getPid(),comment.getMenuId(),comment.getUserId(),comment.getContent())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			commentService.addComment(comment,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添加评论异常",e);
			map.put("msg", "评论添加异常");
			map.put("code", 0);
		}
		return SUCCESS;
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

	public Comment getComment() {
		return comment;
	
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
