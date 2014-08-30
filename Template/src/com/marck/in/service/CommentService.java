package com.marck.in.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marck.common.CommonUtil;
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
		String hql = "from Comment c  where c.pid="+comment.getPid()+" and c.menuId = "+comment.getMenuId();
		PageUtil pu = hdb.findHql(hql,pageNo,pageNum);
		
		for(Comment c: (List<Comment>)pu.getData()){
			if(!CommonUtil.validParams(c.getUserId())){
				hql = "from User u where u.id="+c.getId();
				User user = (User) hdb.find(User.class, c.getUserId());
				c.setNick(user.getNick());
			}
		}
		
		String sql = "select avg(c.level) from comment c where c.pid="+comment.getPid()+" and c.menuId = "+comment.getMenuId();
		List<Object> avg = (List<Object>) hdb.findSql(sql);
		
		Double levelAvg = (Double) avg.get(0) == null ? 0 : (double)Math.round((Double)avg.get(0)*10)/10 ;
		
		map.put("lists", pu.getData());
		map.put("msg", "数据查询成功");
		map.put("code", 1);
		map.put("levelAvg",levelAvg);
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
		/*User user = (User) hdb.find(User.class, comment.getUserId());
		if( null == user ){
			map.put("msg", "用户不存在");
			map.put("code", 0);
		}else{*/
			comment.setAddTime(DateUtil.getNowString("yyyy-MM-dd HH:ss:mm"));
			comment.setContent(CommonUtil.changeIos8859ToUtf8(comment.getContent()));
			hdb.saveOrUpdate(comment);
			map.put("msg", "添加成功");
			map.put("code", 1);
		//}
	}

}
