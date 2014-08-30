package com.era.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String,Object> map = new HashMap<String, Object>();
	private List<Checkversion> list;
	
	private String rows; 
	private String page;
	
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
	
	/**
	 * 查询版本更新
	 * @return
	 * @throws Exception 
	 */
	public String selCheckVersion() throws Exception
	{
		list=checkVersionService.selCheckVersion(request.getParameter("name"),page,rows);
   	 	int total=checkVersionService.numberCheckVersion(request.getParameter("name"));
   	 	map.put("total", total);
   	 	map.put("rows", list);
		return SUCCESS;
	}
	/**
	 * 删除版本更新
	 * @return
	 * @throws Exception
	 */
	public String delCheckVersion() throws Exception
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		checkVersionService.delCheckVersion(Integer.parseInt(ids[i])); 
        } 
		return null;
	}
	/**
	 * 添加版本更新
	 * @return
	 */
	public String addCheckVersion()
	{
		try {
			Checkversion check = new Checkversion();
			check.setAddTime(BaseUtils.getNowStringDateTime(new Date()));
			check.setAppType(Integer.valueOf(request.getParameter("appType")));
			check.setAppUrl(request.getParameter("appUrl"));
			check.setVersionNum(request.getParameter("versionNum"));
			checkVersionService.addCheckVersion(check);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
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

	public List<Checkversion> getList() {
		return list;
	}

	public void setList(List<Checkversion> list) {
		this.list = list;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
