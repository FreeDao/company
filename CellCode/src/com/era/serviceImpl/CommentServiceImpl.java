package com.era.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Comment;
import com.era.orm.Images;
import com.era.service.CommentService;
import com.era.util.BaseUtils;
import com.era.util.DateUtil;

public class CommentServiceImpl implements CommentService {
	private BaseDAO dao;
	private String hql = "";

	@Override
	public List<Object> selComment(int pageNo, int pageSize) {
		String hql = "select coll.id,coll.addtime,user.nickName,sell.titile,coll.conent from Comment coll,Seller sell,User user where coll.sellerId = sell.id and coll.user = user.id order by coll.addtime desc";
		List<Object> list = dao.query(hql, pageNo, pageSize);
		return list;
	}

	@Override
	public int numberComment() {
		String hql = "select count(*) from Comment coll,Seller sell,User user where coll.sellerId = sell.id and coll.user = user.id order by coll.addtime desc";
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public int countComment(int sellerId,String type) {
		hql ="select count(*) from Comment as c where c.sellerId="+sellerId+" and c.type="+type;
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public boolean putComment(Comment comment) {
		boolean flag = false;
		try {
			dao.save(comment);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean delComment(int id) {
		boolean flag = false;
		try {
			dao.delById(Comment.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Comment> getComment(int sellerId, int pageNo, int pageNum) {
		hql = "from Comment where sellerId=" + sellerId+ " order by addtime desc";
		List<Comment> list = dao.query(hql, pageNo, pageNum);
		return list;
	}
	
	/**
	 * 查询获取对应商家评论列表
	 */
	@Override
	public List<Comment> getCommentInfo(String type,int sellerId, int pageNo, int pageNum) {
		hql = "select c.id,c.conent,c.addtime,c.level,c.user from Comment as c where c.sellerId="+sellerId+ " and c.type="+type+" order by c.addtime desc";
		List<Object[]> list = dao.query(hql,pageNo,pageNum);
		List<Comment> list_com = new ArrayList();
		for(Object[] object : list){
			Comment co = new Comment();
			co.setId(Integer.parseInt(object[0]+""));
			String user = object[4]+"";
			if(BaseUtils.isIp(user)){
				user = "游客";
			}else{
				user = user+"";
			}			
			co.setNickName(user);
			co.setLevel(Double.parseDouble(object[3]+""));
			co.setConent(object[1]+"");
			co.setAddtime(object[2]+"");
			list_com.add(co);
		}
		return list_com;
	}
	

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Comment> selShareComment(String shareId, String pageNo,
			String pageNum) {
		// TODO Auto-generated method stub
		String hql = "select new Comment(c.id,c.conent,c.addtime,c.sellerId,c.user,c.level,c.type,c.pid,c.receiver,u.nickName,'') from Comment c,User u where c.user = u.id and c.sellerId ="+Integer.parseInt(shareId)+" and c.type = 4 and c.level = 0";
		
		if( null == pageNo || pageNo.equals("") ){
			pageNo = "1";
		}
		
		if( null == pageNum || pageNum.equals("") ){
			pageNum = "10";
		}
		
		List<Comment> cs = dao.query(hql, Integer.parseInt(pageNo), Integer.parseInt(pageNum));
		
		for(Comment c : cs){
			hql = "select new Comment(c.id,c.conent,c.addtime,c.sellerId,c.user,c.level,c.type,c.pid,c.receiver,u.nickName,(select us.nickName from User us where us.id = c.receiver )) from Comment c,User u where c.user = u.id and c.sellerId ="+Integer.parseInt(shareId)+" and c.pid = "+c.getId()+" and c.type = 4 and c.level = 1";
			List<Comment> ccs = dao.query(hql);
			c.setCs(ccs);
		}
		
		return cs;
	}

	@Override
	public void addShareComment(String shareId, String userId, String pid,
			String receiver, String content) {
		// TODO Auto-generated method stub
		Comment c = new Comment();
		c.setAddtime(DateUtil.getNowString("yyyy-MM-dd HH:mm:ss"));
		c.setConent(content);
		
		if(pid != null && !pid.equals("")){
			c.setLevel(new Double(1));
			c.setPid(Integer.parseInt(pid));
		}else{
			c.setLevel(new Double(0));
		}
		if(receiver != null && !receiver.equals("")){
			c.setReceiver(Integer.parseInt(receiver));
		}
		
		c.setSellerId(Integer.parseInt(shareId));
		c.setType(4);
		c.setUser(userId);
		dao.save(c);
		
	}

}
