package com.era.service;

public interface PutCommentService {
	/**
	 * 查询商户的所有评论条数
	 * 
	 * @param sellerId
	 * @return
	 */
	public String numberComment(int sellerId);
}
