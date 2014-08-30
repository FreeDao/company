package com.gre.bg.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.bg.service.HairdressingService;
import com.gre.common.BaseAction;
import com.gre.common.CommonUtil;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.Hairdressing;
import com.gre.common.model.Img;

@Component("bgHairdressingAction")
@Scope("prototype")
public class HairdressingAction extends BaseAction{

	@Autowired
	private HairdressingService hairdressingService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private List<File> files;
	private List<String> filesContentType;
	private List<String> filesFileName;
	
	private Hairdressing hairdressing;
	
	/**
	 * 查询所有餐饮美食
	 * @return
	 */
	public String index(){
		try {
			pu = hairdressingService.findAllList(Hairdressing.class,pageNow,limit);
			pu.setUrl("bg/hairdressing!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("HairdressingAction中方法index出错");
		}
		return ERROR;
	}

	/**
	 * 删除指定餐饮美食
	 * @return
	 */
	public String delHairdressing(){
		try {
			hairdressingService.delObjById(Hairdressing.class,hairdressing.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("HairdressingAction中方法delHairdressing出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != hairdressing){
				hairdressing = (Hairdressing) hairdressingService.findObjById(Hairdressing.class,hairdressing.getId());
			}
			return "addOrEdit";
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("HairdressingAction中方法goAddOrEdit出错");
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改信息
	 * @return
	 */
	public String addOrEdit(){
		try {
			Hairdressing e = new Hairdressing();
			if( null != hairdressing.getId() ){
				e = (Hairdressing) hairdressingService.findObjById(Hairdressing.class, hairdressing.getId());
				hairdressing.setAddTime(e.getAddTime());
				hairdressing.setLogo(e.getLogo());
				List<Img> imgs = hairdressingService.findAllImgByPid(hairdressing.getId());
				for(Img i : imgs){
					hairdressingService.delObj(i);
				}
			}else{
				hairdressing.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
				hairdressing.setLogo(projectUrl+"/img/zw.jpg");
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
					if(i == 0){
						  hairdressing.setLogo(projectUrl + "/uploadImg/"+md5Code+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
						  //n.setLogo("http://www.tcshenghuo.org:8080/Greening/uploadImg/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
						  hairdressingService.saveOrUpdateObj(hairdressing);
					}else{
					  Img img = new Img();
					  img.setImgUrl(projectUrl + "/uploadImg/"+md5Code+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
					  String name = filesFileName.get(i);
					  img.setName(name.substring(0, name.indexOf(".")));
					  img.setParentId(hairdressing.getId());
					  img.setType(4);
					  hairdressingService.saveOrUpdateObj(img);	
					}
				}
			}else{
				hairdressingService.saveOrUpdateObj(hairdressing);
			}
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("HairdressingAction中方法addOrEdit出错");
			return index();
		}
	}

	public Hairdressing getHairdressing() {
		return hairdressing;
	}

	public void setHairdressing(Hairdressing hairdressing) {
		this.hairdressing = hairdressing;
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
