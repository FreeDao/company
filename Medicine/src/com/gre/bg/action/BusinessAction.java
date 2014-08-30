package com.gre.bg.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.bg.service.BusinessService;
import com.gre.common.BaseAction;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.Business;
import com.gre.common.model.Type;

@Component("businessAction")
@Scope("prototype")
public class BusinessAction extends BaseAction{

	@Autowired
	private BusinessService businessService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private File file;
	private String fileContentType;
	private String fileFileName;
	
	private Business business;
	
	/**
	 * 查询所有商业信息
	 * @return
	 */
	public String index(){
		try {
			pu = businessService.findBusinessAllList(pageNow,limit);
			pu.setUrl("bg/business!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}

	/**
	 * 删除指定商业信息
	 * @return
	 */
	public String delBusiness(){
		try {
			businessService.delObjById(Business.class,business.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("BusinessAction中方法delBusiness出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != business ){
				business = (Business) businessService.findObjById(Business.class,business.getId());
			}
			List<Type> types = (List<Type>) businessService.findAllList(Type.class);
			request.setAttribute("types", types);
			return "addOrEdit";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改信息
	 * @return
	 */
	public String addOrEdit(){
		try {
			if( null != business.getId() && !"".equals(business.getId()) ){
				Business b = (Business) businessService.findObjById(Business.class, business.getId());
				business.setLogo(b.getLogo());
			}else{
				business.setLogo(projectUrl+"/img/zw.jpg");
			}
			
			if(null != file){
				//上传图片存放路径
				String filepath=request.getRealPath("/uploadImg");
				//图片上传
				String now = DateUtil.getNowString();
				File f = new File(filepath+"/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
				if(!f.exists()){
					f.createNewFile();
				}
				FileInputStream is = new FileInputStream(file);
				FileOutputStream os = new FileOutputStream(f);
				byte[] buf=new byte[1024];
				  int len;
				  while((len=is.read(buf))>0){
				   os.write(buf, 0, len);
				   os.flush();
				  }
				  os.close();
				  is.close();
				  business.setLogo(projectUrl + "/uploadImg/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
				  //n.setLogo("http://www.tcshenghuo.org:8080/Greening/uploadImg/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
			}
			businessService.saveOrUpdateObj(business);
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("BusinessAction中方法add出错");
			return index();
		}
	}
	
	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
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
