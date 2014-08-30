package com.era.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.City;
import com.era.orm.Client;
import com.era.orm.Market;
import com.era.service.CityService;
import com.era.service.ClientService;
import com.era.service.MarketService;
import com.era.util.alertInFo;
import com.era.util.text;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ClientAction extends ActionSupport implements ModelDriven<Client>,
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private ClientService clientService;
	private CityService cityService;
	private MarketService marketService;
	Client model = new Client();
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private int pagenum;
	private String nameOne;
	
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	private String qrurlFileName;
	private List<Object> list;
	private List<City> listCity;
	private List<Market> listMarket;
	
	/**
	 * 查询所有的客户端
	 * @return
	 */
	public String selClient()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		pagecount = clientService.numberClient(request.getParameter("name"));
		nameOne = request.getParameter("name");
		if(pagecount<15){
			pageCount=1;
		}else{
			pageCount = pagecount/15;
			int i  = pagecount%15;
			if(i>0)
			{
				pageCount+=1;
			}
		}
		if (pagenum < 1) {
			pagenum = 1;
		}
		if (pagenum > pagecount) {
			if(pagecount == 0)
			{
				
			}
			else
			{
				pagenum = pageCount;
			}
		}
		list = clientService.selClient(request.getParameter("name"),pagenum,15);
		System.out.println(list);
		return SUCCESS;	}
	
	
	/**
	 * 添加客户端
	 * @return
	 * @throws IOException
	 */
	public String addClient() throws IOException
	{
		Client client = new Client();
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			client.setId(null);
		}
		else
		{
			client.setId(Integer.valueOf(request.getParameter("id")));
		}
		
		client.setName(request.getParameter("name"));
		client.setCityId(Integer.valueOf(request.getParameter("city")));
		client.setMarketId(Integer.valueOf(request.getParameter("market")));
		client.setConent(request.getParameter("conent"));
		client.setUrl(request.getParameter("url"));
		
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
			"/qrurl" + "/"+getPictureFileName()));
			
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
					}
		}
		client.setImgUrl("http://171.34.32.126:8080/City/qrurl/"+getPictureFileName());
		
		if(getQrurlFileName() != null)
		{
			String StreamFos = getQrurlFileName().substring(getQrurlFileName().indexOf("."));
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
			"/qrurl" + "/" +getQrurlFileName()));
			
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
					}
		}
		client.setQrUrl("http://171.34.32.126:8080/City/qrurl/"+getQrurlFileName());
		text.pressImage("D://00.png","http://tcshenghuo.org:8080/City/qrurl/"+getQrurlFileName(), 300, 400,0.6f);
		boolean bool = clientService.addClient(client);
		if(bool)
		{
			alertInFo.alertReturn("添加或修改成功");
			selClient();
		}
		else
		{
			alertInFo.alertReturn("添加或修改异常！");
		}
		return SUCCESS;
	}
	
	
	
	/**
	 * 删除客户端
	 * @return
	 * @throws IOException
	 */
	public String delClient() throws IOException{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool=clientService.delClient(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selClient();
			alertInFo.alertReturn("删除成功");
		}
		else
		{
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String addClientPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		model = null;
		listCity = cityService.getCityAll();
		listMarket = marketService.allMarket();
		return SUCCESS;
	}
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String updateClientPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		listCity = cityService.getCityAll();
		model = clientService.selClient(Integer.valueOf(request.getParameter("id")));
		listMarket = marketService.allMarket();
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
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


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public int getPagenum() {
		return pagenum;
	}


	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}


	public String getNameOne() {
		return nameOne;
	}


	public void setNameOne(String nameOne) {
		this.nameOne = nameOne;
	}


	public List<Object> getList() {
		return list;
	}


	public void setList(List<Object> list) {
		this.list = list;
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


	public CityService getCityService() {
		return cityService;
	}


	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}


	public List<City> getListCity() {
		return listCity;
	}


	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}


	public MarketService getMarketService() {
		return marketService;
	}


	public void setMarketService(MarketService marketService) {
		this.marketService = marketService;
	}


	public String getQrurlFileName() {
		return qrurlFileName;
	}


	public void setQrurlFileName(String qrurlFileName) {
		this.qrurlFileName = qrurlFileName;
	}


	public List<Market> getListMarket() {
		return listMarket;
	}


	public void setListMarket(List<Market> listMarket) {
		this.listMarket = listMarket;
	}


	@Override
	public Client getModel() {
		return model;
	}

}
