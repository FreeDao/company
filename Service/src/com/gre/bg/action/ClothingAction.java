package com.gre.bg.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.bg.service.ClothingService;
import com.gre.common.BaseAction;
import com.gre.common.CommonUtil;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.Clothing;
import com.gre.common.model.Img;

@Component("bgClothingAction")
@Scope("prototype")
public class ClothingAction extends BaseAction{

	@Autowired
	private ClothingService clothingService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private List<File> files;
	private List<String> filesContentType;
	private List<String> filesFileName;
	
	private Clothing clothing;
	
	/**
	 * 查询所有服装
	 * @return
	 */
	public String index(){
		try {
			pu = clothingService.findAllList(Clothing.class,pageNow,limit);
			pu.setUrl("bg/clothing!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("ClothingAction中方法index出错");
		}
		return ERROR;
	}

	/**
	 * 删除指定服装
	 * @return
	 */
	public String delClothing(){
		try {
			clothingService.delObjById(Clothing.class,clothing.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("ClothingAction中方法delClothing出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != clothing){
				clothing = (Clothing) clothingService.findObjById(Clothing.class,clothing.getId());
			}
			return "addOrEdit";
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("ClothingAction中方法goAddOrEdit出错");
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改信息
	 * @return
	 */
	public String addOrEdit(){
		try {
			Clothing e = new Clothing();
			if( null != clothing.getId() ){
				e = (Clothing) clothingService.findObjById(Clothing.class, clothing.getId());
				clothing.setAddTime(e.getAddTime());
				clothing.setLogo(e.getLogo());
				List<Img> imgs = clothingService.findAllImgByPid(clothing.getId());
				for(Img i : imgs){
					clothingService.delObj(i);
				}
			}else{
				clothing.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
				clothing.setLogo(projectUrl+"/img/zw.jpg");
			}
			
			if(null != files){
				//上传图片存放路径
				String filepath=request.getRealPath("/uploadImg");
				//图片上传
				String md5Code = "";
				for (int i = 0; i < files.size(); i++) {
					md5Code = CommonUtil.Md5(files.get(i));
					File f = new File(filepath+"/"+md5Code+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
					if(!f.exists()){
						f.createNewFile();
					}
					FileUtils.copyFile(files.get(i), f);
					
					if( i == 0){
						clothing.setLogo(projectUrl + "/uploadImg/"+md5Code+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
						clothingService.saveOrUpdateObj(clothing);
						//n.setLogo("http://www.tcshenghuo.org:8080/Greening/uploadImg/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
					}else{
					  Img img = new Img();
					  img.setImgUrl(projectUrl + "/uploadImg/"+md5Code+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
					  String name = filesFileName.get(i);
					  img.setName(name.substring(0, name.indexOf(".")));
					  img.setParentId(clothing.getId());
					  img.setType(1);
					  clothingService.saveOrUpdateObj(img);	
					}
				}
			}else{
				clothingService.saveOrUpdateObj(clothing);
			}
			
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("ClothingAction中方法addOrEdit出错");
			return index();
		}
	}

	public Clothing getClothing() {
		return clothing;
	}

	public void setClothing(Clothing clothing) {
		this.clothing = clothing;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<String> getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(List<String> filesContentType) {
		this.filesContentType = filesContentType;
	}

	public List<String> getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
	}

	public PageUtil getPu() {
		return pu;
	}

	public void setPu(PageUtil pu) {
		this.pu = pu;
	}

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
}
