package com.tcsh.action;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tcsh.common.BaseAction;
import com.tcsh.common.DateUtil;
import com.tcsh.common.PageUtil;
import com.tcsh.model.local.Img;
import com.tcsh.model.local.Information;
import com.tcsh.model.local.Top;
import com.tcsh.service.InformationService;


@Component("informationAction")
@Scope("prototype")
public class InformationAction extends BaseAction{

	@Autowired
	private InformationService informationService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit ;
	
	private List<File> files;
	private List<String> filesContentType;
	private List<String> filesFileName;
	
	private Information information;
	
	/**
	 * 查询所有行业资讯
	 * @return
	 */
	public String index(){
		try {
			pu = informationService.findInformationList(pageNow,limit);
			pu.setUrl("bg/information!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("InformationAction中方法index出错");
		}
		return ERROR;
	}

	/**
	 * 删除指定行业资讯
	 * @return
	 */
	public String delInformation(){
		try {
			informationService.delObjById(Information.class,information.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("InformationAction中方法delInformation出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != information){
				information = (Information) informationService.findObjById(Information.class,information.getId());
			}
			return "addOrEdit";
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("InformationAction中方法goAddOrEdit出错");
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改信息
	 * @return
	 */
	public String addOrEdit(){
		try {
			Information e = new Information();
			if( null != information.getId() ){
				e = (Information) informationService.findObjById(Information.class, information.getId());
				information.setAddTime(e.getAddTime());
				information.setLogo(e.getLogo());
				List<Img> imgs = informationService.findAllImgByPid(information.getId());
				for(Img i : imgs){
					informationService.delObj(i);
				}
			}else{
				information.setAddTime(DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss"));
				information.setLogo(projectUrl+"/img/zw.jpg");
			}
			
			if(null != files){
				//上传图片存放路径
				String filepath=request.getRealPath("/uploadImg");
				//图片上传
				String now = DateUtil.getNowString();
				for (int i = 0; i < files.size(); i++) {
					System.out.println(filepath+"/"+now+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
					File f = new File(filepath+"/"+now+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
					if(!f.exists()){
						f.createNewFile();
					}
					
					FileUtils.copyFile(files.get(i), f);
					
					  if( i == 0){
						  information.setLogo(projectUrl + "/uploadImg/"+now+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
						  informationService.saveOrUpdateObj(information);
					  }else{
						  Img img = new Img();
						  img.setImgUrl(projectUrl + "/uploadImg/"+now+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
						  img.setName(filesFileName.get(i));
						  img.setParentId(information.getId());
						  img.setType(0);
						  informationService.saveOrUpdateObj(img);
					  }
					  //n.setLogo("http://www.tcshenghuo.org:8080/Greening/uploadImg/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
				}
			}else{
				informationService.saveOrUpdateObj(information);
			}
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("msg", 0);
			log.debug("InformationAction中方法addOrEdit出错");
			return index();
		}
	}

	/**
	 * 置顶开关
	 * @return
	 */
	public String topToggle(){
		try {
			List<Top> tops = informationService.findTop(information.getId());
			if(tops.size() != 0){
				for(Top t : tops){
					informationService.delObj(t);
				}
			}else{
				Top top = new Top();
				top.setAddTime(DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss"));
				top.setParentId(information.getId());
				top.setType(0);
				informationService.saveOrUpdateObj(top);
			}
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("InformationAction中方法topToggle出错");
			return index();
		}
	}
	
	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
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
