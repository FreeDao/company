package com.era.service;

import java.util.List;

import com.era.orm.Comment;

public interface CommentService
{
	/**
	 * 查询所有评论
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Object> selComment(int city,int pageNo, int pageSize);
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	public boolean delComment(int id);
	/**
	 * 查询所有评论条数
	 * @return
	 */
	public int numberComment(int city);

}
