package com.era.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
import com.era.orm.User;
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
			String phone = request.getParameter("iphone");
			if( null == phone || "".equals(phone)){
				map.put("responseCode", "1");
				map.put("msg", "电话号码错误");
				return SUCCESS;
			}
			
			int lackt = lotterDrawService.setMyLackLotter(request.getParameter("iphone"),request.getParameter("activitiesId"),request.getParameter("official"));
			if(lackt == 0)
			{
				map.put("responseCode", "1");
				map.put("msg", "抽奖次数已用完");
				return SUCCESS;
			}
			
			LotteryDraw ld = lotterDrawService.getLotteryDraw(request.getParameter("iphone"),request.getParameter("activitiesId"),request.getParameter("official"));
			
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
				model.setSurplus(BaseUtils.surplus - 1);
				model.setActivitiesId(Integer.parseInt(request.getParameter("activitiesId")));
				model.setOfficial(Integer.parseInt(request.getParameter("official")));
		    }else{
		    	model = lotterDrawService.checkSurplus(ld,request.getParameter("iphone"));
		    	model.setActivitiesId(Integer.parseInt(request.getParameter("activitiesId")));
				model.setOfficial(Integer.parseInt(request.getParameter("official")));
		    }
			
			if(Integer.valueOf(request.getParameter("official"))==1)
			{
					
				Calendar calendar = Calendar.getInstance();
				String now = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
			    
			    Integer temp = new Random().nextInt(100);
			    //System.out.println(temp);
				if(temp > 0 && temp <= 5){
					model.setIsWin(1);
				}else if(temp <= 10 && temp > 5){
					model.setIsWin(2);
				}/*else if(temp == 8){
					model.setIsWin(3);
				}else if(temp == 88){
					model.setIsWin(4);
				}*/else{
					model.setIsWin(0);
				}
		        
				if(now.compareTo("2014-07-05") < 0 || now.compareTo("2014-08-05") > 0  ){
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
					}else if(model.getIsWin() == 3){
						map.put("draw",model.getDraw());
						map.put("msg", "恭喜！获得50元红包，请与工作人员联系！");
					}else if(model.getIsWin() == 4){
						map.put("draw",model.getDraw());
						map.put("msg", "恭喜！获得100元红包，请与工作人员联系！");
					}else{
						map.put("msg", "抱歉！未中奖，再接再厉！");
					}
					int lack = lotterDrawService.setMyLackLotter(request.getParameter("iphone"),request.getParameter("activitiesId"),request.getParameter("official"));
					map.put("lack",lack);
					
				}
				else
				{
					map.put("responseCode", "1");
					map.put("msg", "发送失败");
				}
				
			}
			else
			{
				Activities activities =lotterDrawService.selActivitiesId(Integer.valueOf(request.getParameter("activitiesId")));
				try {
					
					Integer temp = new Random().nextInt(100);
					
					
			    	if(temp<=activities.getRate())
			    	{
			    		model.setIsWin(1);
			    		lotterDrawService.decreaseNum(activities);
		    			map.put("responseCode", "0");
		    			map.put("msg", "中奖了");
		    			map.put("isWin", 1);
						map.put("title",activities.getTitle());
			    	}
			    	else
			    	{
			    		map.put("responseCode", "1");
			    		map.put("isWin", 0);
						map.put("msg", "未中奖");
			    	}
			    	lotterDrawService.addLotteryDraw(model);
			    	int lack = lotterDrawService.setMyLackLotter(request.getParameter("iphone"),request.getParameter("activitiesId"),request.getParameter("official"));
					map.put("lack",lack);
				} catch (Exception e) {
					// TODO: handle exception
					map.put("responseCode", "1");
					map.put("msg", "抽奖出错");
					e.printStackTrace();
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
			String designated = request.getParameter("designated");
			/*if(BaseUtils.isChinaese(request.getParameter("designated")))
			{
				designated = new String(request.getParameter("designated").getBytes("ISO-8859-1"),"UTF-8");
			}else
			{
				designated = request.getParameter("designated");
			}*/
			List<Issue> listDraw = lotterDrawService.selMyLotteryDraw(request.getParameter("official"),request.getParameter("iphone"),designated,request.getParameter("pageNo"),request.getParameter("pageNum"));
			JSONArray array = JSONArray.fromObject(listDraw);
			map.put("responseCode", "0");
			map.put("list",array);
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
			List<Activities> listAct = lotterDrawService.selActivities(request.getParameter("log"),request.getParameter("dim"),null,request.getParameter("lack"),request.getParameter("pageNo"),request.getParameter("pageNum"),request.getParameter("sellerId"),request.getParameter("official"),request.getParameter("cityId"));
			JSONArray array = JSONArray.fromObject(listAct);
			map.put("responseCode", "0");
			map.put("list",array);
		} catch (Exception e) {
			e.printStackTrace();
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
//		try {
//			List<Issue> listSue = lotterDrawService.selIssue(request.getParameter("iphone"),request.getParameter("pageNo"),request.getParameter("pageNum"));
////			List<Activities> listAct = lotterDrawService.selActivities("1",null,null,null,null,null);
//			Activities act = null;
//			for(int i = 0;i<listAct.size();i++)
//			{
//				act = listAct.get(0);
//			}
//			if(act == null || act.equals(""))
//			{
//				
//			}
//			else
//			{
//				map.put("lotteryAddtime",act.getLotteryAddtime());
//			}
//			JSONArray array = JSONArray.fromObject(listSue);
//			map.put("responseCode", "0");
//			map.put("results",array);
//		} catch (Exception e) {
//			map.put("responseCode", "1");
//		}
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
			List<LotteryDraw> listWinning = lotterDrawService.selWinning(sms,request.getParameter("pageNo"),request.getParameter("pageNum"),request.getParameter("official"));
			JSONArray array = JSONArray.fromObject(listWinning);
			map.put("responseCode", "0");
			map.put("list",array);
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
			int lack = lotterDrawService.setMyLackLotter(request.getParameter("iphone"),request.getParameter("activitiesId"),request.getParameter("official"));
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
//	/**
//	 * 安卓推送
//	 * @return
//	 */
//	public String selSellerTui()
//	{
//		try {
//			List<Seller> listSeller = lotterDrawService.selSellerTui();
//			if(listSeller.size()>0)
//			{
//				for (int i = 0; i < listSeller.size(); i++) 
//				{
//					Seller seller = new Seller();
//					seller.setAddress(listSeller.get(i).getAddress());
//					seller.setAddtime(listSeller.get(i).getAddtime());
//					seller.setAreaId(listSeller.get(i).getAreaId());
//					seller.setBrief(listSeller.get(i).getBrief());
//					seller.setCityId(listSeller.get(i).getCityId());
//					seller.setCollectId(listSeller.get(i).getCollectId());
//					seller.setDim(listSeller.get(i).getDim());
//					seller.setDistance(listSeller.get(i).getDistance());
//					seller.setId(listSeller.get(i).getId());
//					seller.setImgesId(0);
//					seller.setLevel(listSeller.get(i).getLevel());
//					seller.setLog(listSeller.get(i).getLog());
//					seller.setLogo(listSeller.get(i).getLogo());
//					seller.setName(listSeller.get(i).getName());
//					seller.setPhone(listSeller.get(i).getPhone());
//					seller.setPreferential(listSeller.get(i).getPreferential());
//					seller.setPrice(listSeller.get(i).getPrice());
//					seller.setProductId(listSeller.get(i).getProductId());
//					seller.setProductImgs(listSeller.get(i).getProductImgs());
//					seller.setSort(listSeller.get(i).getSort());
//					seller.setTitile(listSeller.get(i).getTitile());
//					seller.setType(listSeller.get(i).getType());
//					seller.setTypeId(listSeller.get(i).getTypeId());
//					lotterDrawService.addSeller(seller);
//				}
//				JSONArray array = JSONArray.fromObject(listSeller);
//				map.put("responseCode", "0");
//				map.put("results",array);
//			}
//			else
//			{
//				map.put("responseCode", "2");
//			}
//		} catch (Exception e) {
//			map.put("responseCode",1);
//		}
//		return SUCCESS;
//	}
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
			String result=client.mt(request.getParameter("sms"),"您好，找回密码验证码为："+yzm+"【小区助手】","","","");
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
	 * 领取摇奖机会
	 * @return
	 */
	public String receiveOpportunity()
	{
		try {
			User user = lotterDrawService.selUserId(request.getParameter("userId"));
			URL url;
			String newTime = "";
//			try{
				/*url = new URL("http://www.bjtime.cn");
				 URLConnection uc = url.openConnection();// 生成连接对象
			        uc.connect(); // 发出连接
			        long ld = uc.getDate(); // 取得网站日期时间
*/			        Date date = new Date(); // 转换为标准时间对象
			        java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
			        newTime = format1.format(date);
			        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			        java.util.Date d1 = df.parse(newTime);
			        java.util.Date d2 = df.parse(user.getAddtime());
			        if(d2.getTime()<d1.getTime())
					{
			        	lotterDrawService.updateUser(request.getParameter("userId"),user.getNumber()+1);
						User u = lotterDrawService.selUserId(request.getParameter("userId"));
						map.put("number", u.getNumber());
					}
			        else
			        {
						map.put("responseCode", "1");
						map.put("msg", "今天已领取");
			        }

/*			} catch (MalformedURLException e) {
				e.printStackTrace();
			}// 取得资源对象
			catch (IOException e) {
				e.printStackTrace();
			}
*/			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("responseCode", "1");
			map.put("msg", "领取失败");
		}
		return SUCCESS;
	}
	/**
	 * 兑换摇奖机会
	 * @return
	 */
	public String exchangeOpportunity()
	{
		try {
			User user = lotterDrawService.selUserId(request.getParameter("userId"));
			int t = 10*Integer.valueOf(request.getParameter("num"));
			int dd = user.getIntegral() - t;
			if(dd>0)
			{
				lotterDrawService.updateUserJi(request.getParameter("userId"),user.getNumber()+Integer.valueOf(request.getParameter("num")),user.getIntegral()-10*Integer.valueOf(request.getParameter("num")));
				map.put("responseCode", "0");
				map.put("msg", "兑换成功");
			}
			else
			{
				map.put("responseCode", "1");
				map.put("msg", "兑换失败,积分不够");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("responseCode", "1");
			map.put("msg", "兑换失败");
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
