package com.marck.in.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marck.common.DateUtil;
import com.marck.common.PageUtil;
import com.marck.common.dao.HDB;
import com.marck.common.model.Comment;
import com.marck.common.model.User;

@Component("commentService")
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class CommentService {
	
	@Autowired
	private HDB hdb;

	/**
	 * 设置评论列表
	 * @param comment
	 * @param map
	 * @param pageNo
	 * @param pageNum
	 */
	public void setCommentList(Comment comment, Map<String, Object> map,
			Integer pageNo, Integer pageNum) {
		// TODO Auto-generated method stub
		String hql = "select new Comment(c.id,c.content,c.addTime,c.userId,c.menuId,c.pid,u.nick) from Comment c,User u  where c.userId = u.id and c.pid="+comment.getPid()+" and c.menuId = "+comment.getMenuId();
		PageUtil pu = hdb.findHql(hql,pageNo,pageNum);
		map.put("lists", pu.getData());
		map.put("msg", "数据查询成功");
		map.put("code", 1);
		map.put("total", pu.getTotal());
		map.put("hasNext", pu.getHasNext());
	}

	/**
	 * 添加评论
	 * @param comment
	 * @param map 
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void addComment(Comment comment, Map<String, Object> map) {
		// TODO Auto-generated method stub
		User user = (User) hdb.find(User.class, comment.getUserId());
		if( null == user ){
			map.put("msg", "用户不存在");
			map.put("code", 0);
		}else{
			comment.setAddTime(DateUtil.getNowString("yyyy-MM-dd HH:ss:mm"));
			hdb.saveOrUpdate(comment);
			map.put("msg", "添加成功");
			map.put("code", 1);
		}
	}

}
