package com.marck.in.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.common.BaseAction;
import com.marck.common.CommonUtil;
import com.marck.common.model.Img;
import com.marck.in.service.ImgService;

@Component("imgAction")
@Scope("prototype")
public class ImgAction extends BaseAction{

	@Autowired
	private ImgService imgService;
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private Img img;
	private Integer pageNo;
	private Integer pageNum;
	
	/**
	 * 获取图片集合
	 */
	public String list(){
		try {
			
			if(null == img  || CommonUtil.validParams(img.getPid(),img.getMenuId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			imgService.setImgList(img,map,pageNo,pageNum);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询评论列表异常",e);
			map.put("msg", "查询异常");
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

	public Img getImg() {
		return img;
	
	}

	public void setImg(Img img) {
		this.img = img;
	}


}
