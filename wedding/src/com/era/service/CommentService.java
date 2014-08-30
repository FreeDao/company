package com.era.service;

import java.util.List;

import com.era.orm.Comment;

public interface CommentService 
{
	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	public boolean addComment(Comment comment);
	/**
	 * 通过景点ID查询评论
	 * @param id
	 * @return
	 */
	public List<Comment> listComment(int id,String pageNo,String pageNum);
}
