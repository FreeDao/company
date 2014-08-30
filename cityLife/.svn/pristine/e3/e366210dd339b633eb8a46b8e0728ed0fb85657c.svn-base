package com.era.action;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Checkversion;
import com.era.service.CheckVersionService;
import com.era.util.BaseUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CheckVersionAction extends ActionSupport implements ModelDriven<Checkversion>,ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Checkversion checkVersion = new Checkversion();
	private CheckVersionService checkVersionService;
	
	/**
	 * 客户端检查更新
	 * 
	 * 第一次请求数字 1
	 * 第二次请求返回的时间
	 */
	public void checkVersion(){
		String appType_str = request.getParameter("appType");//对应客户端的类型，1 ios 2 android
		String versionNum_str = request.getParameter("versionNum");//对应版本
		String result = "";
		if(appType_str!=null && versionNum_str != null){
			Checkversion ck = checkVersionService.getNewVersion(Integer.parseInt(appType_str), versionNum_str);
			if(ck != null){				
				JSONArray array = JSONArray.fromObject(ck);
				
				result = "{\"responseCode\":\"" + 0 +"\",\"results\":"+ array.toString() + "}";	
			}else{//没有更新
				result = "{\"responseCode\":\"" + -3 + "\"}";
			}			
		}else{
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}
	

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Checkversion getModel() {
		return checkVersion;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Checkversion getCheckVersion() {
		return checkVersion;
	}

	public void setCheckVersion(Checkversion checkVersion) {
		this.checkVersion = checkVersion;
	}

	public CheckVersionService getCheckVersionService() {
		return checkVersionService;
	}

	public void setCheckVersionService(CheckVersionService checkVersionService) {
		this.checkVersionService = checkVersionService;
	}
}
