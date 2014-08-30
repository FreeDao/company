package com.gre.bg.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.bg.service.CateService;
import com.gre.common.BaseAction;
import com.gre.common.CommonUtil;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.Cate;
import com.gre.common.model.Img;

@Component("bgCateAction")
@Scope("prototype")
public class CateAction extends BaseAction{

	@Autowired
	private CateService cateService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private List<File> files;
	private List<String> filesContentType;
	private List<String> filesFileName;
	
	private Cate cate;
	
	/**
	 * 查询所有餐饮美食
	 * @return
	 */
	public String index(){
		try {
			pu = cateService.findAllList(Cate.class,pageNow,limit);
			pu.setUrl("bg/cate!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("CateAction中方法index出错");
		}
		return ERROR;
	}

	/**
	 * 删除指定餐饮美食
	 * @return
	 */
	public String delCate(){
		try {
			cateService.delObjById(Cate.class,cate.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("CateAction中方法delCate出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != cate){
				cate = (Cate) cateService.findObjById(Cate.class,cate.getId());
			}
			return "addOrEdit";
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("CateAction中方法goAddOrEdit出错");
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改信息
	 * @return
	 */
	public String addOrEdit(){
		try {
			Cate e = new Cate();
			if( null != cate.getId() ){
				e = (Cate) cateService.findObjById(Cate.class, cate.getId());
				cate.setAddTime(e.getAddTime());
				cate.setLogo(e.getLogo());
				List<Img> imgs = cateService.findAllImgByPid(cate.getId());
				for(Img i : imgs){
					cateService.delObj(i);
				}
			}else{
				cate.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
				cate.setLogo(projectUrl+"/img/zw.jpg");
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
						  cate.setLogo(projectUrl + "/uploadImg/"+md5Code+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
						  cateService.saveOrUpdateObj(cate);
					  }else{
						  Img img = new Img();
						  img.setImgUrl(projectUrl + "/uploadImg/"+md5Code+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
						  String name = filesFileName.get(i);
						  img.setName(name.substring(0, name.indexOf(".")));
						  img.setParentId(cate.getId());
						  img.setType(0);
						  cateService.saveOrUpdateObj(img);
					  }
					  //n.setLogo("http://www.tcshenghuo.org:8080/Greening/uploadImg/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
				}
			}else{
				cateService.saveOrUpdateObj(cate);
			}
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("CateAction中方法addOrEdit出错");
			return index();
		}
	}

	public Cate getCate() {
		return cate;
	}

	public void setCate(Cate cate) {
		this.cate = cate;
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
