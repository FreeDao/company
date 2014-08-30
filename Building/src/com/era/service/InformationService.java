package com.era.service;
import java.util.List;

import com.era.orm.Information;

public interface InformationService
{
	/**
	 * 查询所有的资讯
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public List<Information> allInformat(String pageNo,String pageNum);
}
