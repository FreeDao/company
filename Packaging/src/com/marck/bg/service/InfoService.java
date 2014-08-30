package com.marck.bg.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marck.common.CommonUtil;
import com.marck.common.DateUtil;
import com.marck.common.PageUtil;
import com.marck.common.dao.HDB;
import com.marck.common.model.Comment;
import com.marck.common.model.Commodity;
import com.marck.common.model.Info;

@Component("infoService")
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class InfoService {

	@Autowired
	private HDB hdb;

	public PageUtil getInfoList(Integer menuId, String queryValue, Integer pageNow,
			Integer limit) {
		// TODO Auto-generated method stub
		String hql = "from Info i where i.menuId="+menuId;
		if( null != queryValue && !"".equals(queryValue) ){
			hql += " and i.title like '%"+queryValue+"%' or i.source like '%"+queryValue+"%' or i.content like '%"+queryValue+"%' or i.addTime like '%"+queryValue+"%' ";
			if("是".equals(queryValue)){
				hql += " or s.isTop = 1 ";
			}
			if("否".equals(queryValue)){
				hql += " or s.isTop <> 1 ";
			}
		}
		return hdb.findHql(hql, pageNow, limit);
	}

	/**
	 * 查询资讯信息
	 * @param info
	 * @return
	 */
	public Info getInfo(Info info) {
		// TODO Auto-generated method stub
		return (Info) hdb.find(Info.class, info.getId());
	}

	/**
	 * 添加货更新资讯
	 * @param info
	 * @param menuId 
	 * @param uploadFolder 
	 * @param projectUrl 
	 * @param filepath 
	 * @param imgFileName 
	 * @param imgContentType 
	 * @param img 
	 * @return
	 * @throws IOException 
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public boolean addOrUpdateInfo(Info info, Integer menuId, File img, String imgContentType, String imgFileName, String filepath, String projectUrl, String uploadFolder) throws IOException {
		// TODO Auto-generated method stub
		boolean isNew = false;
		if(CommonUtil.validParams(info.getId())){
			info.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			info.setMenuId(menuId);
			info.setCategory(0);
			info.setLogo(projectUrl+ "/img/zw.jpg");
			isNew = true;
		}else{
			Info i = (Info) hdb.find(Info.class, info.getId());
			info.setLogo(i.getLogo());
			info.setAddTime(i.getAddTime());
			info.setMenuId(i.getMenuId());
			info.setCategory(i.getCategory());
		}
		
		if(img != null){
			//图片上传
			String md5Code = "";
			md5Code = CommonUtil.Md5(img);
			
			String path = CommonUtil.checkPath(projectUrl);
			
			File f = new File(path+"/"+md5Code+imgFileName.substring(imgFileName.lastIndexOf(".")));
			if(!f.exists()){
				f.createNewFile();
				FileUtils.copyFile(img, f);
			}
			
			info.setLogo(projectUrl + "/download!img?name="+md5Code+imgFileName.substring(imgFileName.lastIndexOf(".")));

		}
		
		hdb.merge(info);
		
		return isNew;
	}

	/**
	 * 删除资讯
	 * @param info
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void delInfo(Info info) {
		// TODO Auto-generated method stub
		info = (Info) hdb.find(Info.class, info.getId());
		String hql = "from Comment c where c.menuId = "+info.getMenuId()+" and c.pid="+info.getId();
		List<Comment> comments = (List<Comment>) hdb.findHql(hql);
		for(Comment comment: comments){
			hdb.delete(comment);
		}
		hdb.delete(info);
	}
	
	
}
