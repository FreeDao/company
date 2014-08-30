package com.era.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Activities;
import com.era.orm.Issue;
import com.era.orm.LotteryDraw;
import com.era.orm.Recommend;
import com.era.orm.RecommendType;
import com.era.orm.Retrieve;
import com.era.orm.Seller;
import com.era.orm.Tock;
import com.era.orm.Winning;
import com.era.service.LotteryDrawService;
import com.era.util.BaseUtils;
import com.era.util.Client;
import com.era.util.LoginUser;
import com.era.util.PiaoJuTong;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LotteryDrawAction extends ActionSupport implements ModelDriven<LotteryDraw>,
ServletRequestAware{

	/**
	 * 抽奖
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private LotteryDraw model = new LotteryDraw();
	private LotteryDrawService lotterDrawService;
	private Map<String,Object> map  = new HashMap<String,Object>();

	private File file;
	private String fileFileName;
	private String fileContentType;
	
	private String rows; 
	private String page;
	
	/**
	 * 添加抽奖号
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String addLotteryDraw() throws UnsupportedEncodingException
	{
		int lackt = lotterDrawService.setMyLackLotter(request.getParameter("iphone"));
		if(lackt>0)
		{
			map.put("responseCode", "1");
			map.put("msg", "抽奖次数已用完");
		}
		else
		{
			/*URL url = null;
			URLConnection uc = null;
			try {
				url = new URL("http://www.bjtime.cn");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			try {
				uc = url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 生成连接对象
		        try {
					uc.connect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 发出连接
		        long ld = uc.getDate(); // 取得网站日期时间
*/		        Date date = new Date(); // 转换为标准时间对象
			model.setAddtime(BaseUtils.getNowStringDateTimeRQ(date));
			Issue issue = lotterDrawService.selIssueId();
			if(issue == null || issue.equals(""))
			{
				map.put("responseCode", "1");
				map.put("msg", "暂时未有抽奖");
				return SUCCESS;
			}
			model.setDesignated(issue.getId());//第几期
			String yzm = BaseUtils.RandomStringTo();
			model.setDraw(yzm);//抽奖号码
			model.setIphone(request.getParameter("iphone"));//抽奖手机号
			boolean bool = lotterDrawService.addLotteryDraw(model);
			if(bool)
			{
				map.put("responseCode", "0");
				map.put("draw",model.getDraw());
				int lack = lotterDrawService.setMyLackLotter(request.getParameter("iphone"));
				map.put("lack",1-lack);
			}
			else
			{
				map.put("responseCode", "1");
				map.put("msg", "发送失败");
			}
		}
		return SUCCESS;
	}
	/**
	 * 我的抽奖号
	 */
	public String selMyLotteryDraw()
	{
		try {
			String designated = "";
			if(BaseUtils.isChinaese(request.getParameter("designated")))
			{
				designated = new String(request.getParameter("designated").getBytes("ISO-8859-1"),"UTF-8");
			}else
			{
				designated = request.getParameter("designated");
			}
			List<LotteryDraw> listDraw = lotterDrawService.selMyLotteryDraw(request.getParameter("iphone"),designated,request.getParameter("pageNo"),request.getParameter("pageNum"));
			JSONArray array = JSONArray.fromObject(listDraw);
			map.put("responseCode", "0");
			map.put("results",array);
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	/**
	 * 验证当天是否超过5次 预留
	 * @return
	 */
	public String proving()
	{
		
		return SUCCESS;
	}
	/**
	 * 查询活动
	 * @return
	 */
	public String activities()
	{
		try {
			List<Activities> listAct = lotterDrawService.selActivities(null,request.getParameter("lack"),request.getParameter("pageNo"),request.getParameter("pageNum"));
			JSONArray array = JSONArray.fromObject(listAct);
			map.put("responseCode", "0");
			map.put("results",array);
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	/**
	 * 期号
	 * @return
	 */
	public String issue()
	{
		try {
			List<Issue> listSue = lotterDrawService.selIssue(request.getParameter("iphone"),request.getParameter("pageNo"),request.getParameter("pageNum"));
			List<Activities> listAct = lotterDrawService.selActivities("1",null,null,null);
			Activities act = null;
			for(int i = 0;i<listAct.size();i++)
			{
				act = listAct.get(0);
			}
			if(act == null || act.equals(""))
			{
				
			}
			else
			{
				map.put("lotteryAddtime",act.getLotteryAddtime());
			}
			JSONArray array = JSONArray.fromObject(listSue);
			map.put("responseCode", "0");
			map.put("results",array);
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	
	/**
	 * 我的中奖信息
	 * @return
	 */
	public String winning()
	{
		try {
			String sms = null;
			if(request.getParameter("sms") == null || request.getParameter("sms").equals(""))
			{
				sms = request.getParameter("iphone");
			}
			else
			{
				sms = request.getParameter("sms");
			}
			List<Winning> listWinning = lotterDrawService.selWinning(sms,request.getParameter("pageNo"),request.getParameter("pageNum"));
			JSONArray array = JSONArray.fromObject(listWinning);
			map.put("responseCode", "0");
			map.put("results",array);
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	/**
	 * 我今天还有几次抽奖
	 * @return
	 */
	public String myLackLotter()
	{
		try {
			int lack = lotterDrawService.setMyLackLotter(request.getParameter("iphone"));
			map.put("responseCode", "0");
			map.put("lack",1-lack);
		} catch (Exception e) {
			map.put("responseCode",1);
		}
		return SUCCESS;
	}
	/**
	 * 获取iphone手机tock
	 * @return
	 */
	public String addTock()
	{
		try {
			Tock tock = new Tock();
			int tockTwo = lotterDrawService.selTockWY(request.getParameter("tock"));
			if(tockTwo>0)
			{
				map.put("responseCode",1);
			}
			else
			{
				tock.setDim(request.getParameter("dim"));
				tock.setDog(request.getParameter("dog"));
				tock.setTockId(request.getParameter("tock"));
				String city = BaseUtils.getaddressforxybyGooglehttpconnection(request.getParameter("dog"),request.getParameter("dim"));
				tock.setCity(city);
				boolean bool = lotterDrawService.addTock(tock);
				if(bool)
				{
					map.put("responseCode", "0");
				}
				else
				{
					map.put("responseCode",1);
				}
			}
		} catch (Exception e) {
			map.put("responseCode",1);
		}
		return SUCCESS;
	}
	/**
	 * 安卓推送
	 * @return
	 */
	public String selSellerTui()
	{
		try {
			List<Seller> listSeller = lotterDrawService.selSellerTui();
			if(listSeller.size()>0)
			{
				for (int i = 0; i < listSeller.size(); i++) 
				{
					Seller seller = new Seller();
					seller.setAddress(listSeller.get(i).getAddress());
					seller.setAddtime(listSeller.get(i).getAddtime());
					seller.setBrief(listSeller.get(i).getBrief());
					seller.setCollectId(listSeller.get(i).getCollectId());
					seller.setDim(listSeller.get(i).getDim());
					seller.setDistance(listSeller.get(i).getDistance());
					seller.setId(listSeller.get(i).getId());
					seller.setImgesId(0);
					seller.setLevel(listSeller.get(i).getLevel());
					seller.setLog(listSeller.get(i).getLog());
					seller.setLogo(listSeller.get(i).getLogo());
//					seller.setName(listSeller.get(i).getName());
					seller.setPhone(listSeller.get(i).getPhone());
					seller.setPreferential(listSeller.get(i).getPreferential());
					seller.setPrice(listSeller.get(i).getPrice());
					seller.setProductImgs(listSeller.get(i).getProductImgs());
					seller.setSort(listSeller.get(i).getSort());
					seller.setTitile(listSeller.get(i).getTitile());
					seller.setTypeId(listSeller.get(i).getTypeId());
					lotterDrawService.addSeller(seller);
				}
				JSONArray array = JSONArray.fromObject(listSeller);
				map.put("responseCode", "0");
				map.put("results",array);
			}
			else
			{
				map.put("responseCode", "2");
			}
		} catch (Exception e) {
			map.put("responseCode",1);
		}
		return SUCCESS;
	}
	/**
	 * 查询应用
	 * @return
	 */
	public String selRecommend()
	{
		try {
			List<Recommend> listRecommend = lotterDrawService.selRecommend(request.getParameter("cityId"),request.getParameter("isIos"),request.getParameter("ismend"),request.getParameter("typeId"),request.getParameter("pageNo"),request.getParameter("pageNum"));
			if(listRecommend.size()>0)
			{
				map.put("ref", "0");
			}
			else
			{
				map.put("ref", "1");
			}
			JSONArray array = JSONArray.fromObject(listRecommend);
			map.put("results",array);
			map.put("responseCode", "0");
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	/**
	 * 查看应用汇分类
	 * @return
	 */
	public String selRecommendType()
	{
		try {
			List<RecommendType> listRecommendType = lotterDrawService.selRecommendType(request.getParameter("hot"));
			if(listRecommendType.size()>0)
			{
				map.put("ref", "0");
			}
			else
			{
				map.put("ref", "1");
			}
			JSONArray array = JSONArray.fromObject(listRecommendType);
			map.put("results",array);
			map.put("responseCode", "0");
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	
	/**
	 * 找回密码发送验证码
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String selRetrieve() throws UnsupportedEncodingException
	{
		try {
			String sn = "SDK-DLS-010-00484";
			String pwd = "143146";
			Client client = new Client(sn, pwd);
			String yzm = BaseUtils.RandomString();
			String result=client.mt(request.getParameter("sms"),"您好，找回密码验证码为："+yzm+"【同城生活圈】","","","");
			if(result.length()>8)
			{
				Retrieve retrieve = new Retrieve();
				/*URL url=new URL("http://www.bjtime.cn");//取得资源对象
			       URLConnection uc=url.openConnection();//生成连接对象
			       uc.connect(); //发出连接
			       long ld=uc.getDate(); //取得网站日期时间
*/			       Date date=new Date(); //转换为标准时间对象
			       DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					String date1 = format1.format(date);  
					
					
				retrieve.setAddtime(date1);
				
				Date date3 = format1.parse(date1);
				int minutes=date3.getMinutes();
				minutes=minutes+2;
				date3.setMinutes(minutes);
				String date2 = format1.format(date3);  
				retrieve.setTimedao(date2);
				
				retrieve.setIphone(request.getParameter("sms"));
				retrieve.setRetrieve(yzm);
				boolean bool = lotterDrawService.addRetrieve(retrieve);
				if(bool)
				{
					map.put("code",yzm);
					map.put("responseCode", "0");
				}
				else
				{
					map.put("responseCode", "1");
				}
			}
			else
			{
				map.put("responseCode", "1");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	/**
	 * 验证验证码是否正确
	 * @return
	 */
	public String selRetrievePassWord()
	{
		try {
			List<Retrieve> retrieve = lotterDrawService.selRetrievePassWord(request.getParameter("sms"),request.getParameter("retrieve"));
			if(retrieve.size() <1)
			{
				map.put("responseCode", "1");
				map.put("msg", "验证码过期或手机不正确");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("sms",request.getParameter("sms"));
			}
 		} catch (Exception e) {
 			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String updaePassWord()
	{
		try {
			boolean bool = lotterDrawService.updaePassWord(request.getParameter("sms"),PiaoJuTong.Md5(request.getParameter("passWord")));
			if(bool)
			{
				map.put("responseCode", "0");
				map.put("msg", "找回成功");
			}
			else
			{
				map.put("responseCode", "1");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	/**
	 * 查询官方抽奖
	 * @return
	 */
	public String selOfficial()
	{
		try {
			LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			List<Activities> list=lotterDrawService.selOfficial(lu,null,null,2,page,rows);
	   	 	int total=lotterDrawService.numberOfficial(lu,null,null,2);
	   	 	map.put("total", total);
	   	 	map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String selOfficialTwo()
	{
		try {
			LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			List<Activities> list=lotterDrawService.selOfficial(lu,request.getParameter("name"),request.getParameter("youxiang"),1,page,rows);
	   	 	int total=lotterDrawService.numberOfficial(lu,request.getParameter("name"),request.getParameter("youxiang"),1);
	   	 	map.put("total", total);
	   	 	map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除官方抽奖
	 * 
	 * @return
	 * @throws IOException
	 */
	public String delOfficial() throws IOException 
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		lotterDrawService.delOfficial(Integer.parseInt(ids[i])); 
        }
		return null;
	}
	/**
	 * 添加抽奖
	 * @return
	 */
	public String addOfficial()
	{
		try {
			LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			
			Activities act = new Activities();
			
			if(request.getParameter("id") == null || request.getParameter("id").equals(""))
			{
				act.setId(null);
			}
			else
			{
				act.setId(Integer.valueOf(request.getParameter("id")));
			}
			act.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
			act.setSort(0);
			act.setLucky(1);
			act.setPersonal(1);
			act.setLotteryAddtime(request.getParameter("lotteryAddtime"));
			act.setBife(request.getParameter("bife"));
			act.setComment(request.getParameter("comment"));
			act.setTitle(request.getParameter("title"));
			act.setPrize(request.getParameter("prize"));
			act.setRules(request.getParameter("rules"));
			act.setRate(Integer.parseInt(request.getParameter("rate")));
			act.setRootQode(Integer.parseInt(request.getParameter("rootQode")));
			act.setIphone(request.getParameter("iphone"));
			act.setPrice(request.getParameter("price"));
			act.setNum(request.getParameter("num"));
			act.setSellId(lu.getId());
			act.setLog(lu.getLog());
			act.setDim(lu.getDim());
			StringBuffer url = request.getRequestURL();
			String projectUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).toString();
	        //D:\apache-tomcat-6.0.18\webapps\struts2_upload\images
	        System.out.println("realpath: "+projectUrl);
	        if (file != null) { 
	        	String filepath=request.getRealPath("/uploadImgs");
				//图片上传
				String now = com.era.util.DateUtil.getNowString();
					File f = new File(filepath+"/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
					if(!f.exists()){
						f.createNewFile();
					}
					
					FileUtils.copyFile(file, f);
					
				act.setLogo(projectUrl + "/uploadImgs/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
	        }
			
			lotterDrawService.addfdasfdsaf(act);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 查询商户中用户中奖信息
	 * @return
	 */
	public String selLerLottery()
	{
		try {
			List<Winning> list=lotterDrawService.selLerLottery(request.getParameter("name"),request.getParameter("youxiang"),page,rows);
	   	 	int total=lotterDrawService.numberWinning(request.getParameter("name"),request.getParameter("youxiang"));
	   	 	map.put("total", total);
	   	 	map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 删除中奖信息
	 * @return
	 */
	public String delLerLottery()
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		lotterDrawService.delLerLottery(Integer.parseInt(ids[i])); 
        } 
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public LotteryDraw getModel() {
		return model;
	}

	public LotteryDrawService getLotterDrawService() {
		return lotterDrawService;
	}

	public void setLotterDrawService(LotteryDrawService lotterDrawService) {
		this.lotterDrawService = lotterDrawService;
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
