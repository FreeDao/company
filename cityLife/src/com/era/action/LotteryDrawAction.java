package com.era.action;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

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
	
	/**
	 * 添加抽奖号
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String addLotteryDraw() throws UnsupportedEncodingException
	{
		Integer lackt = lotterDrawService.setMyLackLotter(request.getParameter("iphone"));
		if(lackt == 0)
		{
			map.put("responseCode", "1");
			map.put("msg", "抽奖次数已用完");
		}
		else
		{
		    LotteryDraw ld = lotterDrawService.getLotteryDraw(request.getParameter("iphone"));
			
		    if( null == ld ){
		    	Date date = new Date(); // 转换为标准时间对象
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
				model.setIsReceiving(0);
		    }else{
		    	model = ld;
		    	model.setSurplus(model.getSurplus() - 1);
		    }
		    
		    Calendar calendar = Calendar.getInstance();
			String now = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		    
		    Integer temp = new Random().nextInt(100);
		    System.out.println(temp);
			if(temp > 0 && temp <= 5){
				model.setIsWin(1);
			}else if(temp <= 10 && temp > 5){
				model.setIsWin(2);
			}else{
				model.setIsWin(0);
			}
	        
			if(now.compareTo("2014-05-25") < 0 || now.compareTo("2014-06-25") > 0  ){
				model.setIsWin(0);
			}
			
			boolean bool = lotterDrawService.addLotteryDraw(model);
			if(bool)
			{
				map.put("responseCode", "0");
				if(model.getIsWin() == 1){
					map.put("draw",model.getDraw());
					map.put("msg", "恭喜！获得5元红包，请与工作人员联系！");
				}else if(model.getIsWin() == 2){
					map.put("draw",model.getDraw());
					map.put("msg", "恭喜！获得10元红包，请与工作人员联系！");
				}else{
					map.put("msg", "抱歉！未中奖，再接再厉！");
				}
				int lack = lotterDrawService.setMyLackLotter(request.getParameter("iphone"));
				map.put("lack",lack);
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
			List<LotteryDraw> listDraw = lotterDrawService.selMyLotteryDraw(request.getParameter("iphone"),request.getParameter("pageNo"),request.getParameter("pageNum"));
			//JSONArray array = JSONArray.fromObject(listDraw);
			map.put("responseCode", "0");
			map.put("results",listDraw);
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
			List<Activities> listAct = lotterDrawService.selActivities(request.getParameter("lack"),request.getParameter("pageNo"),request.getParameter("pageNum"));
			for(Activities a : listAct){
				if( null != a.getLucky() && 1 == a.getLucky()){
					a.setUrl("http://www.tcshenghuo.org/cityLife/appHtml/luck.jsp");
				}
			}
			
			JSONArray array = JSONArray.fromObject(listAct);
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
			Integer lack = lotterDrawService.setMyLackLotter(request.getParameter("iphone"));
			map.put("responseCode", "0");
			map.put("lack",lack);
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
					seller.setAreaId(listSeller.get(i).getAreaId());
					seller.setBrief(listSeller.get(i).getBrief());
					seller.setCityId(listSeller.get(i).getCityId());
					seller.setCollectId(listSeller.get(i).getCollectId());
					seller.setDim(listSeller.get(i).getDim());
					seller.setDistance(listSeller.get(i).getDistance());
					seller.setId(listSeller.get(i).getId());
					seller.setImgesId(0);
					seller.setLevel(listSeller.get(i).getLevel());
					seller.setLog(listSeller.get(i).getLog());
					seller.setLogo(listSeller.get(i).getLogo());
					seller.setName(listSeller.get(i).getName());
					seller.setPhone(listSeller.get(i).getPhone());
					seller.setPreferential(listSeller.get(i).getPreferential());
					seller.setPrice(listSeller.get(i).getPrice());
					seller.setProductId(listSeller.get(i).getProductId());
					seller.setProductImgs(listSeller.get(i).getProductImgs());
					seller.setSort(listSeller.get(i).getSort());
					seller.setTitile(listSeller.get(i).getTitile());
					seller.setType(listSeller.get(i).getType());
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
			Integer pageNo = 1;
			Integer pageNum = 10;
			if( null != request.getParameter("pageNo") && !"".equals(request.getParameter("pageNo"))){
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			if( null != request.getParameter("pageNo") && !"".equals(request.getParameter("pageNo"))){
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
					
			List<Recommend> listRecommend = lotterDrawService.selRecommend(request.getParameter("name"),request.getParameter("cityId"),request.getParameter("isIos"),request.getParameter("ismend"),request.getParameter("typeId"),request.getParameter("pageNo"),request.getParameter("pageNum"));
			Integer count = lotterDrawService.selRecommendNum(request.getParameter("name"),request.getParameter("cityId"),request.getParameter("isIos")); 
			if(count > pageNo*pageNum){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
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
	
}
