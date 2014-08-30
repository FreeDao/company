package com.era.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Busmarset;
import com.era.orm.City;
import com.era.orm.Guarantee;
import com.era.orm.Market;
import com.era.orm.VillageManager;
import com.era.service.CityService;
import com.era.service.MarketService;
import com.era.util.BaseUtils;
import com.era.util.BusMarketQuery;
import com.era.util.LoginUser;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MarketAction extends ActionSupport implements ModelDriven<Market>,
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	private MarketService marketService;
	private CityService cityService;
	private List<Object> list;
	private List<City> listCity;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private String typeOne;
	private String result;
	private Map<String,Object> map  = new HashMap<String,Object>();
	private String rows; 
	private String page;
	
	private File picture;
	private String pictureContentType;
	private String pictureFileName;

	private File file;
	private String fileContentType;
	private String fileFileName;
	
	Market model = new Market();

	/**
	 * 查询显示对应区域下面 所有市场类型列表
	 * 
	 */
	public String getMarketList() {
		try {
			List<Market> list = marketService.getMarketList(request.getParameter("villageId"),request.getParameter("pageNo"),request.getParameter("pageNum"),request.getParameter("marketType"));
			if(list == null ||list.equals(""))
			{
				map.put("responseCode", "-2");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("list", list);
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "注册异常");
		}
		return SUCCESS;
	}
	/**
	 * 通过市场名称查询市场
	 * @throws UnsupportedEncodingException 
	 */
	public void selMark() throws UnsupportedEncodingException
	{
		String markName = "";
		String pageNo = request.getParameter("pageNo");
		String pageNum = request.getParameter("pageNum");
		String cityId = request.getParameter("cityId");
		
		if(request.getParameter("markName") == null || request.getParameter("markName").equals(""))
		{
			
		}
		else
		{
			if(BaseUtils.isChinaese(request.getParameter("markName")))
			{
				markName = new String(request.getParameter("markName").getBytes("ISO-8859-1"),"UTF-8");
			}
			else
			{
				markName = request.getParameter("markName");
			}
		}
		List<Market> listMark = marketService.selLikeMark(markName,cityId,pageNo,pageNum);
		JSONArray array = JSONArray.fromObject(listMark);
		if(listMark.size()>0 || listMark != null)
		{
			result = "{\"responseCode\":\"" + 0 + "\",\"results\":" + array + "}";
		}
		else
		{
			result = "{\"responseCode\":\"" + 0 + "\",\"def\":\""+ 0 + "\",\"results\":" + array + "}";
		}
		BaseUtils.responseInfo(result);
	}
	/**
	 * 查询获取每个类型下面对应的注入商家是谁
	 */
	public void getMarketSeller(){
		String typeId_str = request.getParameter("typeId");
		if(typeId_str != null){
			Integer typeId = Integer.parseInt(typeId_str);
			Busmarset b = marketService.getBusMarketSet(typeId);
			if(b!=null){
				JSONArray array = JSONArray.fromObject(b);
				result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
			}else{
				result = "{\"responseCode\":\"" + -2 + "\"}";	
			}
		}else{
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}

	/**
	 * 查询市场类型
	 * 
	 * @return
	 */
	public String selMarket() {
		if (request.getSession().getAttribute("userid") == null) 
		{
			return "error";
		}
		pagecount = marketService.numberMarket(request.getParameter("type"));
		typeOne = request.getParameter("type");
		if (pagecount < 15) {
			pageCount = 1;
		} else {
			pageCount = pagecount / 15;
			int i = pagecount % 15;
			if (i > 0) {
				pageCount += 1;
			}
		}
		if (pagenum < 1) {
			pagenum = 1;
		}
		if (pagenum > pagecount) {
			if (pagecount == 0) {

			} else {
				pagenum = pagecount;
			}
		}
		list = marketService.selMarket(request.getParameter("type"), pagenum,15);
		listCity = cityService.getCityAll();
		return SUCCESS;
	}

	/**
	 * 删除市场类型
	 * 
	 * @return
	 * @throws IOException
	 */
	public String delMarket() throws IOException {
		if (request.getSession().getAttribute("userid") == null) {
			return "error";
		}
		boolean bool = marketService.delMarket(Integer.valueOf(request
				.getParameter("id")));
		if (bool) {
			alertInFo.alertReturn("删除成功");
			selMarket();
		} else {
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String addMarketPage() {
		if (request.getSession().getAttribute("userid") == null) {
			return "error";
		}
		listCity = cityService.getCityAll();
		return SUCCESS;
	}

	/**
	 * 我要报修
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String addGuarantee() throws UnsupportedEncodingException
	{
		try {
			Guarantee tee = new Guarantee();
			String bife = "";
			String address ="";
			String iphone = request.getParameter("iphone");
			String outAddtime = request.getParameter("outAddtime");
			String isNo = request.getParameter("isNo");
			String villageId = request.getParameter("villageId");
			if(BaseUtils.isChinaese(request.getParameter("bife")))
			{
				bife = new String(request.getParameter("bife").getBytes("ISO-8859-1"),"UTF-8");
			}
			else
			{
				bife = request.getParameter("bife");
			}
			if(BaseUtils.isChinaese(request.getParameter("address")))
			{
				address = new String(request.getParameter("address").getBytes("ISO-8859-1"),"UTF-8");
			}
			else
			{
				address = request.getParameter("address");
			}
			tee.setAddress(address);
			tee.setIphone(iphone);
			tee.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
			tee.setOutAddtime(outAddtime);
			tee.setIsNo(Integer.valueOf(isNo));
			tee.setBife(bife);
			tee.setVillageId(Integer.valueOf(villageId));
			boolean bool = marketService.addGuarantee(tee);
			if(bool)
			{
				map.put("responseCode","0");
			}
			else
			{
				map.put("responseCode", "1");
				map.put("msg", "注册异常");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "注册异常");
		}
		return SUCCESS;
	}
	
	public String selGuarantee(){
		LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
		String villageId =request.getParameter("villageId");
		if(lu.getRole() == 5){
			villageId = lu.getVillageId()+"";
		}
		List<Guarantee> list = marketService.getGuaranteeList(villageId,page,rows);
		int total=marketService.numberGuarantee(villageId);
   	 	map.put("total", total);
   	 	map.put("rows", list);
		return SUCCESS;
	}
	
	
	/**
	 * 查询便民/小区
	 * @return
	 */
	public String selMarekt()
	{
		List<Market> list = marketService.getMarketList(request.getParameter("villageId"),page,rows,request.getParameter("marketType"));
		int total=marketService.numberMarekt(request.getParameter("villageId"),request.getParameter("marketType"));
   	 	map.put("total", total);
   	 	map.put("rows", list);
		return SUCCESS;
	}
	/**
	 * 删除便民/小区
	 * @return
	 */
	public String delMarekt()
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		marketService.delMarekt(Integer.parseInt(ids[i])); 
        } 
		return null;
	}
	/**
	 * 添加便民/小区
	 * @return
	 */
	public String addMarekt()
	{
		try {
			Market mar = new Market();
			if(getPictureFileName() != null)
			{
				String StreamFos = getPictureFileName().substring(getPictureFileName().indexOf("."));
				long picturefis = getPicture().length();
				if (picturefis > 100000) {
				alertInFo.alertReturn("你上传的文件过大！");
				return SUCCESS;
				}

				if (!StreamFos.equals(".gif") && !StreamFos.equals(".jpg")
				&& !StreamFos.equals(".bmp")) {
				alertInFo.alertReturn("你上传的文件过大！");
				return SUCCESS;
				}

				FileOutputStream fos = new FileOutputStream(ServletActionContext.getRequest().getRealPath(
				"/images" + "/"+getPictureFileName()));
				
				FileInputStream fis = new FileInputStream(getPicture());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
						}
				mar.setLogo("http://localhost:8080/City/images/"+getPictureFileName());
			}
			else
			{
				mar.setLogo("");
			}
			mar.setType(request.getParameter("type"));
			mar.setTypeEr(Integer.valueOf(request.getParameter("typeEr")));
			mar.setVillageId(Integer.valueOf(request.getParameter("villageId")));
			marketService.addMarket(mar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加修改商品
	 * @return
	 */
	public String addOrUpdateCommodity(){
		try {
			String id = request.getParameter("id");
			String title = request.getParameter("titile");
			String phone = request.getParameter("phone");
			String otherPrice = request.getParameter("otherPrice");
			String price = request.getParameter("price");
			String villageId = request.getParameter("villageId");
			String shopId = request.getParameter("shopId");
			String shopIdTwo = request.getParameter("shopIdTwo");
			String brief = request.getParameter("brief");
			String level = request.getParameter("level");
			LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			String logo = null;
			StringBuffer url = request.getRequestURL();
			String projectUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).toString();
	        if (file != null) { 
	        	String filepath=request.getRealPath("/uploadImgs");
				//图片上传
				String now = com.era.util.DateUtil.getNowString();
					File f = new File(filepath+"/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
					if(!f.exists()){
						f.createNewFile();
					}
					
					FileUtils.copyFile(file, f);
					
					logo = projectUrl + "/uploadImgs/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1);
	        }
			marketService.saveOrUpdateCommodity(lu,id,title,phone,otherPrice,price,villageId,shopId,shopIdTwo,brief,level,logo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询市场主
	 * @return
	 */
	public String selBusMarket(){
		try{
			LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			List<BusMarketQuery> list = marketService.getBusMarketList(lu,page,rows);
			Integer total=marketService.numberBusMarekt(lu);
	   	 	map.put("total", total);
	   	 	map.put("rows", list);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加和更新市场主
	 * @return
	 */
	public String addBusMarket(){
		try {
			String id = request.getParameter("id");
			String userName = request.getParameter("userName");
			String passWord = request.getParameter("userPwd");
			String bmsUserName = request.getParameter("bmsUserName");
			String telephone = request.getParameter("telephone");
			String qq = request.getParameter("qq");
			String email = request.getParameter("email");
			String cityId = request.getParameter("city");
			String villageId = request.getParameter("villageId");
			
			LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			
			boolean isSave = marketService.saveOrUpdateBusMarket(lu,id,userName,passWord,bmsUserName,telephone,qq,email,cityId,villageId);
			
			if(!isSave){
				alertInFo.alertReturn("账号已经存在！");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除市场主
	 * @return
	 */
	public String delBusMarket(){
		try {
			String items=request.getParameter("items");
	    	String[] ids=items.split(",");
	    	for (int i = 0; i < ids.length; i++) { 
	    		marketService.delBusMarket(Integer.parseInt(ids[i])); 
	        } 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	/**
	 * 查询物业管理账号
	 * @return
	 */
	public String selVillageManager(){
		try{
			LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			List<VillageManager> list = marketService.getVillageManagerList(lu,page,rows);
			Integer total=marketService.numberVillageManager(lu);
	   	 	map.put("total", total);
	   	 	map.put("rows", list);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加和更新物业管理信息
	 * @return
	 */
	public String addVillageManager(){
		try {
			String id = request.getParameter("id");
			String userName = request.getParameter("userName");
			String passWord = request.getParameter("passWord");
			String villageId = request.getParameter("villageId");
			
			LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			
			boolean isSave = marketService.saveOrUpdateVillageManager(lu,id,userName,passWord,villageId);
			
			if(!isSave){
				alertInFo.alertReturn("账号已经存在！");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除物业管理账号
	 * @return
	 */
	public String delVillageManager(){
		try {
			String items=request.getParameter("items");
	    	String[] ids=items.split(",");
	    	for (int i = 0; i < ids.length; i++) { 
	    		marketService.delVillageManager(Integer.parseInt(ids[i])); 
	        } 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Market getModel() {
		return model;
	}

	public MarketService getMarketService() {
		return marketService;
	}

	public void setMarketService(MarketService marketService) {
		this.marketService = marketService;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public int getPagenum() {
		return pagenum;
	} 
 
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getPagesum() {
		return pagesum;
	}

	public void setPagesum(int pagesum) {
		this.pagesum = pagesum;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public String getTypeOne() {
		return typeOne;
	}

	public void setTypeOne(String typeOne) {
		this.typeOne = typeOne;
	}

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<City> getListCity() {
		return listCity;
	}

	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
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
	public File getPicture() {
		return picture;
	}
	public void setPicture(File picture) {
		this.picture = picture;
	}
	public String getPictureContentType() {
		return pictureContentType;
	}
	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}
	public String getPictureFileName() {
		return pictureFileName;
	}
	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
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
	
}
