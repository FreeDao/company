package com.era.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Images;
import com.era.orm.Mall;
import com.era.orm.Market;
import com.era.orm.Seller;
import com.era.orm.Sellermanager;
import com.era.orm.Share;
import com.era.orm.ShopType;
import com.era.orm.ShopTypeTwo;
import com.era.orm.User;
import com.era.orm.Video;
import com.era.orm.Village;
import com.era.service.AreaService;
import com.era.service.SellerService;
import com.era.util.BaseUtils;
import com.era.util.DateUtil;
import com.era.util.LoginUser;
import com.era.util.PiaoJuTong;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SellerAction  extends ActionSupport implements ModelDriven<Seller>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HttpServletRequest request;
	private SellerService sellerSerivce;
	private AreaService areaService;
	private List<Seller> listSeller;
	private List<Images> imgs;
	private List<Share> listShare;
	
	private List<Object> list;
	private Object obj;
	
	private File file;
	private String fileFileName;
	private String fileContentType;
	
	
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	
	 private String rows; 
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

		private String page;
	
	
	private Map<String,Object> map  = new HashMap<String,Object>();
	
	Seller model = new Seller();
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	
	private String cityName;//城市名字
	private String cityId;//城市Id
	private String log;//经度
	private String dim;//纬度
	private String keyStr;//关键字
	
	public SellerAction() {
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * 查询所有的商户
	 * @return
	 */
	public String selSeller()
	{
		try {
			pagecount = sellerSerivce.numberSeller(request.getParameter("name"),request.getParameter("shop"));
			listSeller = sellerSerivce.selSeller(request.getParameter("name"),request.getParameter("shop"),Integer.valueOf(page),Integer.valueOf(rows));
			map.put("total", pagecount);
	   	 	map.put("rows", listSeller);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除商户
	 * @return
	 * @throws IOException 
	 */
	public String delSeller() throws IOException
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		sellerSerivce.delSeller(Integer.parseInt(ids[i])); 
        } 
		return SUCCESS;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String sellerPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		return SUCCESS;
	}

	
	/**
	 * TODO 便民商户
	 */
	public String getSellerList()
	{
		try {
			List list = sellerSerivce.getSellerInfoList(request.getParameter("log"), request.getParameter("dim"), request.getParameter("pageNo"), request.getParameter("pageNum"),request.getParameter("typeId"),request.getParameter("shop"));
			if(list.size()<1)
			{
				map.put("responseCode", "-2");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("list", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("responseCode", "1");
			map.put("msg", "查询异常");
		}
		return SUCCESS;
	}
	/**
	 * 小区
	 * @return
	 */
	public String selShopType()
	{
		try {
			List<ShopType> list = sellerSerivce.selShopType();
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
			map.put("msg", "查询异常");
		}
		return SUCCESS;
	}
	/**
	 * 二级栏目
	 * @return
	 */
	public String selShopTypeTwo()
	{
		try {
			List<ShopTypeTwo> list = sellerSerivce.selShopTypeTwo(request.getParameter("shopId"));
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
			map.put("msg", "查询异常");
		}
		return SUCCESS;
	}
	/**
	 * 查询商城
	 * @return
	 */
	public String selMall()
	{
		try {
			List<Mall> list = sellerSerivce.selMall();
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
			map.put("msg", "查询异常");
		}
		return SUCCESS;
	}
	/**
	 * 查询租售房源
	 * @return
	 */
	public String selVideo()
	{
		try {
			String type = request.getParameter("type");//租房还是售房
			String log = request.getParameter("log");//
			String dim = request.getParameter("dim");//
			String villageId = request.getParameter("villageId");//小区ID
			String pageNo = request.getParameter("pageNo");
			String pageNum =  request.getParameter("pageNum");
			List<Video> list = sellerSerivce.selVideo(type,log,dim,villageId,pageNo,pageNum);
			if(list.size()<1)
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
			map.put("msg", "查询异常");
		}
		return SUCCESS;
	}
	/**
	 * 后台查询商户个人信息
	 * @return
	 */
	public String selSellerLogo()
	{
		try {
			 LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			 obj=sellerSerivce.selSellerLogo(lu.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 后台查询商户个人信息
	 * @return
	 */
	public String selSellerInfo()
	{
		try {
			 LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			 obj=sellerSerivce.selSellerInfoById(lu.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 后台查询商户便民类型
	 * @return
	 */
	public String selMarketType()
	{
		try {
			 list=sellerSerivce.selMarketList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 后台查询租售房消息
	 * @return
	 */
	public String selVideoBackstage()
	{
		try {
			List<Video> list=sellerSerivce.selVideoBackstage(request.getParameter("name"),page,rows);
	   	 	int total=sellerSerivce.numberVideoBackstage(request.getParameter("name"));
	   	 	map.put("total", total);
	   	 	map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 删除租售房消息
	 * @return
	 */
	public String delVideoBackstage()
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		sellerSerivce.delVideoBackstage(Integer.parseInt(ids[i])); 
        } 
		return null;
	}
	
	/**
	 * 添加租售房消息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String addVideo() throws UnsupportedEncodingException
	{
		try {
			String type = request.getParameter("personal");//1个人2经纪人
			String name = "";//小区名称
			String title = "";//标题
			String address = "";//地址
			String layout = "";//户型
			String price = request.getParameter("price");//1个人2经纪人
			String bife = "";//1个人2经纪人
			String info = "";//1个人2经纪人
			Map<String, String> json = alertInFo.getGeocoderLatitude(request.getParameter("address"));
			if(json == null || json.equals(""))
			{
				map.put("responseCode", "1");
				map.put("msg", "未找到该地址");
				return SUCCESS;
			}
			if ( BaseUtils.isChinaese(request.getParameter("name"))) {
				// 用于IOS客户端传递过来的用户名
				name = new String(
						request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				name = request.getParameter("name");
			}
			if ( BaseUtils.isChinaese(request.getParameter("title"))) {
				// 用于IOS客户端传递过来的用户名
				title = new String(
						request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				title = request.getParameter("title");
			}
			if ( BaseUtils.isChinaese(request.getParameter("address"))) {
				// 用于IOS客户端传递过来的用户名
				address = new String(
						request.getParameter("address").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				address = request.getParameter("address");
			}
			if ( BaseUtils.isChinaese(request.getParameter("layout"))) {
				// 用于IOS客户端传递过来的用户名
				layout = new String(
						request.getParameter("layout").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				layout = request.getParameter("layout");
			}
			if ( BaseUtils.isChinaese(request.getParameter("bife"))) {
				// 用于IOS客户端传递过来的用户名
				bife = new String(
						request.getParameter("bife").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				bife = request.getParameter("bife");
			}
			if ( BaseUtils.isChinaese(request.getParameter("info"))) {
				// 用于IOS客户端传递过来的用户名
				info = new String(
						request.getParameter("info").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				info = request.getParameter("info");
			}
			
			Video video = new Video();
			video.setAddress(address);
			
			video.setLog(json.get("lng"));
			video.setDim(json.get("lat"));
			video.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
			video.setBife(bife);
			video.setInfo(info);
			video.setLayout(layout);
			video.setTitle(title);
			video.setPrice(Integer.valueOf(price));
			video.setQuarters(name);
			video.setPersonal(Integer.valueOf(type));
			boolean bool = sellerSerivce.addVideo(video);
			if(bool)
			{
				map.put("responseCode", "0");
			}
			else
			{
				map.put("responseCode", "1");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "添加租售信息异常");
		}
		return SUCCESS;
	}
	/**
	 * 查询分享
	 * @return
	 */
	public String selShare()
	{
		try 
		{
			listShare = sellerSerivce.selShare(request.getParameter("dim"),request.getParameter("log"),request.getParameter("villageId"),request.getParameter("pageNo"),request.getParameter("pageNum"));
			if(listShare.size()<1)
			{
				map.put("responseCode", "-2");
			}
			else
			{
				map.put("list", listShare);
				map.put("responseCode", "0");
			}
			
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询分享");
		}
		return SUCCESS;
	}
	
	/**
	 * 查询分享后台
	 * @return
	 */
	public String selShareBackstage()
	{
		List<Object> list = sellerSerivce.selShareBackstage(request.getParameter("name"),Integer.valueOf(page),Integer.valueOf(rows));
		int total=sellerSerivce.numberShareBackstage(request.getParameter("name"));
   	 	map.put("total", total);
   	 	map.put("rows", list);
		return SUCCESS;
	}
	/**
	 * 删除分享
	 * @return
	 */
	public String delShareBackstage()
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		sellerSerivce.delShareBackstage(Integer.parseInt(ids[i])); 
        } 
		return null;
	}
	
	/**
	 * 查询商城信息
	 * @return
	 */
	public String selMallBackstage()
	{
		List<Mall> list=sellerSerivce.selMallBackstage(request.getParameter("name"),Integer.valueOf(page),Integer.valueOf(rows));
   	 	int total=sellerSerivce.numberMallBackstage(request.getParameter("name"));
   	 	map.put("total", total);
   	 	map.put("rows", list);
		return SUCCESS;
	}
	
	/**
	 * 删除商城信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public String delMallBackstage() throws IOException 
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		sellerSerivce.delMallBackstage(Integer.parseInt(ids[i])); 
        } 
		return null;
	}
	/**
	 * 添加商城信息
	 * @return
	 */
	public String addMall()
	{
		try {
			Mall ma = new Mall();
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			
		}
		else
		{
			ma.setId(Integer.valueOf(request.getParameter("id")));
		}
			ma.setBife(request.getParameter("bife"));
			ma.setIntegral(Integer.valueOf(request.getParameter("integral")));
			ma.setNumber(Integer.valueOf(request.getParameter("number")));
			ma.setTitle(request.getParameter("title"));
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
				ma.setLogo("http://localhost:8080/City/images/"+getPictureFileName());
			}
			else
			{
				ma.setLogo("");
			}
			sellerSerivce.addMall(ma);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加商户
	 * @return
	 * @throws ParseException 
	 */
	public String addSeller() throws ParseException
	{
		try {
			
			String id = request.getParameter("id");
			String address = request.getParameter("address");
			String brief = request.getParameter("brief");
			String title = request.getParameter("titile");
			String product = request.getParameter("name");
			String phone = request.getParameter("phone");
			String level = request.getParameter("level");
			String villageId = request.getParameter("villageId");
			String recruitment =request.getParameter("recruitment");
			String typeId = request.getParameter("typeId");
			String endTime = request.getParameter("endTime");
			LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			
			Seller s ;
			User u ;
			if(id == null || id.equals(""))
			{
				s = new Seller();
				u = new User();
			}else{
				s = sellerSerivce.getSeller(id);
				u = sellerSerivce.getUser(id);
			}
			
			if(address != null && !address.equals(""))
			{
				Map<String, String> json = alertInFo.getGeocoderLatitude(address);
				if(json == null || json.equals(""))
				{
					alertInFo.alertReturn("地图不存在此地址!");
					return SUCCESS;
				}
				s.setLog(json.get("lng"));
				s.setDim(json.get("lat"));
				s.setAddress(address);
			}
			s.setTypeId(Integer.parseInt(typeId));
			s.setAddtime(DateUtil.getNowDate("yyyy-MM-dd"));
			s.setName(product);
			s.setBrief(brief);
			s.setTitile(title);
			s.setShop(0);
			s.setSort(0);
			s.setEndTime(endTime);
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
					s.setLogo(logo);
			}
			
			s.setPhone(phone);
			
			if(level != null && !level.equals(""))
			{
				s.setLevel(Double.valueOf(level));//星级
			}
			s.setRecruitment(recruitment);
			
			if(lu.getRole() == 3){
				s.setVillageId(Integer.parseInt(lu.getVillageId()));
			}else{
				s.setVillageId(Integer.parseInt(villageId));
			}
			s.setUserId(lu.getId());
			s.setUserRole(lu.getRole());
			sellerSerivce.addSeller(s);
			
			String sellid = String.valueOf(100000+s.getId());
			if( null == id || "".equals(id)){
				Sellermanager ss = new Sellermanager();
				ss.setUserName(sellid);
				ss.setUserPwd(PiaoJuTong.Md5("123456"));
				ss.setQq("111111");
				ss.setTelePhone(model.getPhone());
				ss.setEmail("111111@qq.com");
				ss.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
				ss.setSellerId(s.getId());
				sellerSerivce.saveOrUpdateSellerManager(ss);
				
				u.setEmail(sellid);
				u.setPassWord(PiaoJuTong.Md5("123456"));
				u.setSellIsNo(1);
				u.setSellerId(ss.getId());
				u.setAddtime(DateUtil.getNowString("yyyy-MM-dd"));
			}
			
			u.setNickName(s.getTitile());
			u.setImgUrl(s.getLogo());
			u.setVillageId(s.getVillageId());
			u.setEndTime(endTime);
			sellerSerivce.saveOrUpdateObject(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 查询所有市场
	 * @return
	 */
	public String selMarket()
	{
		List<Market> list = sellerSerivce.selMarketAll();
		map.put("msg", list);
		return SUCCESS;
	}
	/**
	 * 一级栏目
	 * @return
	 */
	public String selShopIdY()
	{
		List<ShopType> list  = sellerSerivce.selShopIdY();
		map.put("msg", list);
		return SUCCESS;
	}
	/**
	 * 二级栏目
	 * @return
	 */
	public String selShopIdE()
	{
		List<ShopTypeTwo> list = sellerSerivce.selShopIdE(request.getParameter("Yid"));
		map.put("msg", list);
		return SUCCESS;
	}
	/**
	 * 查询小区
	 * @return
	 */
	public String selVillageList()
	{
		list  = sellerSerivce.selVillage();
		return SUCCESS;
	}
	/**
	 * 一级栏目
	 * @return
	 */
	public String selShopId1()
	{
		list  = sellerSerivce.selShopId1();
		return SUCCESS;
	}
	/**
	 * 二级栏目
	 * @return
	 */
	public String selShopId2()
	{
		list = sellerSerivce.selShopId2(request.getParameter("id"));
		return SUCCESS;
	}
	/**
	 * 查询用户地址
	 * @return
	 */
	public String selAddress()
	{
		return SUCCESS;
	}
	/**
	 * 删除用户地址
	 * @return
	 */
	public String delAddress()
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		sellerSerivce.delMallBackstage(Integer.parseInt(ids[i])); 
        } 
		return null;
	}
	
	/**
	 * 保存和更新seller信息
	 * @return
	 */
	public String addOrUpdateSeller(){
		try{
			String title = request.getParameter("titile");
			String phone = request.getParameter("phone");
			String typeId = request.getParameter("typeId");
			String address = request.getParameter("address");
			String brief = request.getParameter("brief");
			String name = request.getParameter("name");
			String recruitment = request.getParameter("recruitment");
			
			String log = "" ;
			String dim = "" ;
			if(address != null && !address.equals(""))
			{
				Map<String, String> json = alertInFo.getGeocoderLatitude(address);
				if(json == null || json.equals(""))
				{
					alertInFo.alertReturn("地图不存在此地址!");
					return SUCCESS;
				}
				log =json.get("lng");
				dim =json.get("lat");
			}
			
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
			sellerSerivce.addOrUpdateSeller(lu,title,phone,typeId,address,brief,name,recruitment,log,dim,logo);
			request.setAttribute("msg", 0);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询seller表根据shop字段判断是便民还是商品
	 * @return
	 */
	public String selSellerByType(){
		try {
			LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			Integer type = Integer.parseInt(request.getParameter("type"));
			List<Seller> list=sellerSerivce.selCommodity(type,lu,Integer.valueOf(page),Integer.valueOf(rows));
	   	 	int total=sellerSerivce.numberCommodity(type,lu);
	   	 	map.put("total", total);
	   	 	map.put("rows", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加图片
	 * @return
	 */
	public String addImg(){
		try {
			StringBuffer url = request.getRequestURL();
			String projectUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).toString();
			String filepath=request.getRealPath("/uploadImgs");
			String type = request.getParameter("type");
			String compositeId = request.getParameter("compositeId");
			sellerSerivce.saveOrUpdateImg(file,projectUrl,filepath,fileFileName,fileContentType,type,compositeId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询图片
	 * @return
	 */
	public String selImg(){
		try {
			pagecount = sellerSerivce.numberImages(request.getParameter("type"),request.getParameter("id"));
			imgs = sellerSerivce.selImages(request.getParameter("type"),request.getParameter("id"));
			map.put("rows", imgs);
			map.put("total", pagecount);
			//System.out.println("tettet");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String delImg(){
		try {
			String items=request.getParameter("items");
	    	String[] ids=items.split(",");
	    	for (int i = 0; i < ids.length; i++) { 
	    		sellerSerivce.delImg(Integer.parseInt(ids[i])); 
	        } 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Seller getModel() {
		return model;
	}

	public SellerService getSellerSerivce() {
		return sellerSerivce;
	}

	public void setSellerSerivce(SellerService sellerSerivce) {
		this.sellerSerivce = sellerSerivce;
	}

	public List<Images> getImgs() {
		return imgs;
	}

	public void setImgs(List<Images> imgs) {
		this.imgs = imgs;
	}

	public List<Seller> getListSeller() {
		return listSeller;
	}

	public void setListSeller(List<Seller> listSeller) {
		this.listSeller = listSeller;
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

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getDim() {
		return dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}
	
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getKeyStr() {
		return keyStr;
	}

	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}

	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<Share> getListShare() {
		return listShare;
	}

	public void setListShare(List<Share> listShare) {
		this.listShare = listShare;
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

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	
}
