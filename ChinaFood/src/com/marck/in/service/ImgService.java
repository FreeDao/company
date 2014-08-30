package com.marck.in.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	/**
	 * 删除图片
	 * @param id
	 * @param uploadFolder 
	 * @param filepath 
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void delImg(Integer id, String filepath, String uploadFolder) {
		// TODO Auto-generated method stub
		Img img = (Img) hdb.find(Img.class, id);
		String path = img.getUrl();
		hdb.delete(img);
		String name = path.substring(path.lastIndexOf("/"));
		File f = new File(filepath+name);
		if(f.exists()){
			f.delete();
		}
	}

	public List<Img> uploadImg(File file,String fileFileName,String filepath, String brief, String fileId,
			List<Img> imgs, Integer menuId, Map<String, Object> map, String projectUrl, String tempFolder) throws IOException {
		// TODO Auto-generated method stub
		//图片上传
		String md5Code = "";
		md5Code = CommonUtil.Md5(file);
		File uploadPath = new File(filepath);
		if(!uploadPath.exists()){
			uploadPath.mkdirs();
		}
		
		File temp = File.createTempFile(md5Code, fileFileName.substring(fileFileName.lastIndexOf(".")),uploadPath);
		FileUtils.copyFile(file, temp);
		temp.deleteOnExit();
		
		if( null == imgs ){
			imgs = new ArrayList<Img>();
		}
		
		Img img = new Img();
		img.setBrief(brief);
		img.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
		img.setMenuId(menuId);
		img.setUrl(filepath+"/"+temp.getName());
		img.setFileId(fileId);
		imgs.add(img);
		
		map.put("url", projectUrl + "/"+tempFolder+"/"+temp.getName());
		map.put("brief", brief);
		map.put("msg", "上传成功");
		map.put("code", 1);
		
		return imgs;
	}

}
