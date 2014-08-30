package com.era.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Code;
import com.era.orm.Product;
import com.era.orm.Protype;
import com.era.orm.User;
import com.era.service.ProductService;
import com.era.util.BaseUtils;
import com.era.util.Client;
import com.era.util.PiaoJuTong;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	private ProductService productService;
	private List<Product> list;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private String idOne;
	private Map<String,Object> map  = new HashMap<String,Object>();
	Product model = new Product();
	Code code = new Code();
	User user = new User();
	/**
	 * 通过ID查询产品
	 * @return
	 */
	public String selProduct()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		idOne = request.getParameter("id");
		pagecount = productService.numberProduct(request.getParameter("id"));
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
				pagenum = pagecount;
			}
		}
		list = productService.selProductId(request.getParameter("id"),pagenum,15);
		return SUCCESS;
	}
	
	/**
	 * 删除产品
	 * @return
	 * @throws IOException 
	 */
	public String delProduct() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = productService.delProduct(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			alertInFo.alertReturn("删除成功");
		}
		else
		{
			alertInFo.alertReturn("删除失败");
		}
		return SUCCESS;
	}
	
	
	/**
	 * TODO 客户端查询显示获取产品类型及对应产品类型下面的图片
	 * @return
	 */
	public void getProduct(){
		/*
		 * 1、先查询出对应该商家的所有产品类型，同时默认显示全部
		 * 2、根据产品类型编号查询对应的产品类型下面所有图片
		 * 3、所有的产品
		 */
		String result ="";
		String proId_str = request.getParameter("productId");//产品表id
		
		String proTypeId_str = request.getParameter("proTypeId");//产品类型表id
		
		if(proId_str != null){
			int proId = Integer.parseInt(proId_str);
			//对应产品类型
			List list_pt = productService.getAllProductType(proId);
			JSONArray array_pt = new JSONArray();
			int size = list_pt.size();
			for(int i=0;i<size;i++){
				Protype p = (Protype)list_pt.get(i);
				JSONObject object = new JSONObject();
				object.put("id",p.getId());
				object.put("typeName",p.getTypeName());	
				array_pt.add(object);
			}
			if(list_pt != null){
				int proTypeId = proTypeId_str != null ? Integer.parseInt(proTypeId_str) : -1;					
				List list_ptImgs = productService.getProductTypeImgs(proId,proTypeId);
				JSONArray array_imgs = JSONArray.fromObject(list_ptImgs);
				
				result = "{\"responseCode\":\"" + 0 + "\",\"proTypeName\":"+array_pt.toString()+",\"results\":"+ array_imgs.toString() + "}";
			}else{
				result = "{\"responseCode\":\"" + 0 + "\",\"proTypeName\":"+ -2 +",\"results\":"+ -2+ "}";
			}			
		}else{
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}
	/**
	 * 发送短信
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public String sms() throws IOException, ParseException
	{
		/*URL url=new URL("http://www.bjtime.cn");//取得资源对象
	       URLConnection uc=url.openConnection();//生成连接对象
	       uc.connect(); //发出连接
	       long ld=uc.getDate(); //取得网站日期时间
*/	       Date date=new Date(); //转换为标准时间对象
		 DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		 String date1 = format1.format(date);  
		Date date3 = format1.parse(date1);
		user = productService.selUserY(request.getParameter("sms"));
		int count = productService.selCodeAddtime(request.getParameter("sms"),date1);
		if(count==0)
		{
			if(user == null || user.equals(""))
			{
				String sn = "SDK-DLS-010-00484";
				String pwd = "143146";
				Client client = new Client(sn, pwd);
				String yzm = BaseUtils.RandomString();
				String result=client.mt(request.getParameter("sms"),"您好，欢迎注册同城生活圈，您的验证码是："+yzm+"【同城生活圈】","","","");
				if(result.length()>8)
				{
					int minutes=date3.getMinutes();
					minutes=minutes+1;
					date3.setMinutes(minutes);
					String date2 = format1.format(date3);  
					//发送成功
					code.setCode(yzm);
					code.setIphone(request.getParameter("sms"));
					code.setAddtime(date1);
					code.setSendTime(date2);
					productService.addCode(code);
					map.put("code",yzm);
					map.put("responseCode", "0");
				}
				else
				{
					//发送失败
					map.put("responseCode", "1");
					map.put("msg", "发送失败");
				}
			}
			else
			{
				//发送失败
				map.put("responseCode", "1");
				map.put("msg", "用户名已存在");
			}
		}
		else
		{
			//发送失败
			map.put("responseCode", "1");
			map.put("msg", "时间未超过60秒");
		}
		return SUCCESS;
	}
	/**
	 * 验证验证码
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String codeSms() throws UnsupportedEncodingException
	{
		String content = null;
		User u = new User();
		User t = new User();
		t = productService.selUserY(request.getParameter("sms"));
		if(t == null || t.equals(""))
		{
		
		code = productService.selCodeSms(request.getParameter("sms"),request.getParameter("codeExt"));
		if(code == null)
		{
			map.put("responseCode", "1");
			map.put("msg", "验证码或手机不正确");
		}
		else
		{
			u.setEmail(request.getParameter("sms"));
			if(request.getParameter("password") == null || request.getParameter("password").equals(""))
			{
				map.put("responseCode", "1");
				map.put("msg", "密码为空");
			}
			else
			{
				u.setPassWord(PiaoJuTong.Md5(request.getParameter("password")));
			}
			if(BaseUtils.isChinaese(request.getParameter("nick")))
			{
				content = new String(request.getParameter("nick").getBytes("ISO-8859-1"),"UTF-8");
				u.setNickName(content);
			}
			else
			{
				u.setNickName(request.getParameter("nick"));
			}
			productService.addUserCode(u);
			map.put("results",u);
			map.put("user",u);
			map.put("responseCode", "0");
		}
		
		}
		else
		{
			map.put("msg", "用户已存在");
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Product getModel() {
		return model;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
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

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getIdOne() {
		return idOne;
	}

	public void setIdOne(String idOne) {
		this.idOne = idOne;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
