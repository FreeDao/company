package com.marck.in.service;

import java.io.File;
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
import com.marck.common.model.Img;
import com.marck.common.model.Seller;
import com.marck.common.model.User;

@Component("imgService")
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ImgService {
	
	@Autowired
	private HDB hdb;

	/**
	 * 查询图片
	 * @param img
	 * @param map
	 * @param pageNo
	 * @param pageNum
	 */
	public void setImgList(Img img, Map<String, Object> map, Integer pageNo,
			Integer pageNum) {
		// TODO Auto-generated method stub
		String hql = "from Img i where i.pid="+img.getPid()+" and i.menuId="+img.getMenuId();
		PageUtil pu = hdb.findHql(hql,pageNo,pageNum);
		map.put("lists", pu.getData());
		map.put("msg", "查询成功");
		map.put("code", 1);
		map.put("hasNext", pu.getHasNext());
	}

}
