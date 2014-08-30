package com.era.serviceImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.era.dao.BaseDAO;
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

public class LotteryDrawServiceImpl implements LotteryDrawService{

	private BaseDAO dao;
	
	@Override
	public boolean addLotteryDraw(LotteryDraw draw) 
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(draw);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public List<Issue> selMyLotteryDraw(String official,String iphone, String designated,String pageNo,String pageNum) 
	{
		List<Issue> list = null;
		String hql="select s from Issue s ";
		
		if(pageNo == null || pageNo.equals("") || pageNum == null || pageNum.equals("")) 
		{
			list = dao.query(hql);
		}
		else
		{
			list = dao.query(hql,Integer.valueOf(pageNo), Integer.valueOf(pageNum));
		}
		for(Issue i : list){
			hql="from LotteryDraw l where l.designated = "+i.getId();
			if(iphone != null && !"".equals(iphone)){
				hql+=" and l.iphone = '"+iphone+"'";
			}
			if(designated == null || designated.equals(""))
			{
				
			}
			else
			{
				hql += " and l.designated = '"+designated+"'";
			}
			if(official == null || official.equals(""))
			{
				
			}
			else
			{
				hql += " and l.official ="+official;
			}
			
			List<LotteryDraw> lds = dao.query(hql);
			i.setListDraw(lds);
		}
		return list;
	}
	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}
	@Override
	public List<Activities> selActivities(String log ,String dim,String yz,String lack,String pageNo, String pageNum,String sellerId,String official,String cityId) {
		List<Activities> list = null;
		String hql="select a from Activities a,Sellermanager sm,Seller s,Village v where a.sellId = sm.id and sm.sellerId = s.id and s.villageId = v.id and a.sellId <> 1 and v.cityId = "+Integer.parseInt(cityId)+" and a.personal = "+Integer.parseInt(official);
		if(log == null || log.equals(""))
		{
			
		}
		else
		{
			hql+=" order by ACOS(SIN(("+log+" * 3.1415) / 180 ) *SIN((a.dim * 3.1415) / 180 ) + COS(("+log+" * 3.1415) / 180 ) * COS(( a.dim * 3.1415) / 180 ) *COS(("+log+" * 3.1415) / 180 - (a.log * 3.1415) / 180 ) ) * 6380 asc";
		}
		
		if(pageNo == null || pageNo.equals("") || pageNum == null || pageNum.equals("")) 
		{
			list = dao.query(hql);
		}
		else
		{
			list = dao.query(hql,Integer.valueOf(pageNo), Integer.valueOf(pageNum));
		}
		return list;
	}
	@Override
	public List<Issue> selIssue(String iphone,String pageNo, String pageNum) {
		List<Issue> list = null;
		List<Issue> listIssue = new ArrayList<Issue>();
		if(pageNo == null || pageNo.equals("") || pageNum == null || pageNum.equals("")) 
		{
			list = dao.query("from Issue");
		}
		else
		{
			list = dao.query("from Issue",Integer.valueOf(pageNo), Integer.valueOf(pageNum));
		}
		for (int i = 0; i < list.size(); i++) 
		{
			Issue issue = list.get(i);
			Issue issueOne = new Issue();
			List<LotteryDraw> listDraw = dao.query("from LotteryDraw where iphone = '"+iphone+"' and designated = '"+issue.getTitle()+"'");
			if(listDraw.size()>0)
			{
				issueOne.setTitle(issue.getTitle());
				issueOne.setId(issue.getId());
				//issueOne.setListDraw(listDraw);
				listIssue.add(issueOne);
			}
		}
		return listIssue;
	}
	@Override
	public List<LotteryDraw> selWinning(String sms, String pageNo, String pageNum,String official) {
			// TODO Auto-generated method stub
			
			String hql="select new Activities(a.id, a.dim, a.log, a.title,a.comment, a.logo, a.addtime, a.sort,a.lotteryAddtime, a.bife, a.prize, a.rules,a.lucky, a.large, a.personal, a.rate,a.rootQode, a.address, a.iphone, a.sellId,a.price, a.num, l.isWin, l.isReceiving,l.draw) from LotteryDraw l,Activities a where l.activitiesId = a.id and l.isWin <> 0 and l.iphone='"+sms+"' and l.official="+Integer.parseInt(official);

			return dao.query(hql,Integer.valueOf(pageNo), Integer.valueOf(pageNum));
	}
	@Override
	public int setMyLackLotter(String iphone,String activitiesId, String official)
	{
		 Date date = new Date(); // 转换为标准时间对象
        java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String newTime = format1.format(date);
        User user = (User) dao.loadObject("from User where email = '"+iphone+"'");
        String hql ="from LotteryDraw where iphone ='"+iphone+"' and addtime = '"+newTime+"' and official ="+Integer.parseInt(official);
        List<LotteryDraw> lds = dao.query(hql);
        if(lds.size()>0){
        	LotteryDraw ld = lds.get(0);
        	if(ld.getIsWin() != 0){
        		return 0;
        	}else{
        		return ld.getSurplus()+user.getNumber();
        	}
        }else{
        	return BaseUtils.surplus+user.getNumber();
        }
	}
	@Override
	public Issue selIssueId() {
		List<Issue> listIssue = dao.query("from Issue where current = 1");
		Issue issue = null;
		if(listIssue.size()>0)
		{
			issue = listIssue.get(0);
		}
		return issue;
	}
	@Override
	public boolean addTock(Tock tock) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(tock);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public List<Seller> selSellerTui()
	{
		List<Seller> list = dao.query("from Seller where imgesId = 1");
		return list;
	}
	@Override
	public void addSeller(Seller seller) 
	{
		try {
			dao.saveOrUpdate(seller);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Recommend> selRecommend(String cityId,String isIos, String ismend, String typeId,
			String pageNo, String pageNum) {
		List<Recommend> list = null;
		String hql="from Recommend where 1 = 1";
		if(isIos == null || isIos.equals(""))
		{
			
		}
		else
		{
			hql += " and isIos = "+isIos;
		}
		if(cityId == null || cityId.equals(""))
		{
			
		}
		else
		{
			hql += " and cityId = "+cityId;
		}
		if(ismend == null || ismend.equals(""))
		{
			
		}
		else
		{
			hql += " and ismend = "+ismend;
		}
		if(typeId == null || typeId.equals(""))
		{
			
		}
		else
		{
			hql += " and typeId = "+typeId;
		}
		if(pageNo == null || pageNo.equals("") || pageNum == null || pageNum.equals("")) 
		{
			list = dao.query(hql);
		}
		else
		{
			list = dao.query(hql,Integer.valueOf(pageNo), Integer.valueOf(pageNum));
		}
		return list;
	}
	@Override
	public List<Retrieve> selRetrievePassWord(String parameter, String parameter2) {
		List<Retrieve> list = dao.query("from Retrieve where iphone = '"+parameter+"' and retrieve = '"+parameter2+"' and addtime < timedao");
		return list;
	}
	@Override
	public boolean addRetrieve(Retrieve retrieve) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(retrieve);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean updaePassWord(String parameter, String md5)
	{
		try {
			int i = dao.update("update User set passWord = '"+md5+"' where email = "+parameter);
			if(i>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public List<RecommendType> selRecommendType(String hot) 
	{
		List<RecommendType> list = dao.query("from RecommendType where hot = "+hot);
		return list;
	}
	@Override
	public int selTockWY(String parameter) {
		int count = dao.countBySql("select count(*) from Tock where tockId = '"+parameter+"'");
		return count;
	}
	@Override
	public Activities selActivitiesId(Integer valueOf) {
		Activities act = (Activities) dao.loadById(Activities.class, valueOf);
		return act;
	}
	@Override
	public User selUserId(String parameter) {
		User user = (User) dao.loadById(User.class, Integer.valueOf(parameter));
		return user;
	}
	@Override
	public boolean updateUser(String parameter, int t) {
		try {
			String newTime = "";
			/*URL url;
			url = new URL("http://www.bjtime.cn");
			 URLConnection uc = url.openConnection();// 生成连接对象
		        uc.connect(); // 发出连接
		        long ld = uc.getDate(); // 取得网站日期时间
*/		        Date date = new Date(); // 转换为标准时间对象
		        java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
				newTime = format1.format(date);
			int i = dao.update("update User set number = "+t+",addtime='"+newTime+"' where id = "+parameter);
			if(i>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean updateUserJi(String parameter, int t, int j) {
		try {
			int i = dao.update("update User set number = "+t+",integral = "+j+" where id = "+parameter);
			if(i>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public LotteryDraw getLotteryDraw(String iphone, String activitiesId,
			String official) {
		// TODO Auto-generated method stub
		Date date = new Date(); // 转换为标准时间对象
        java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String newTime = format1.format(date);
        String hql ="from LotteryDraw where iphone ='"+iphone+"' and addtime = '"+newTime+"' and official ="+Integer.parseInt(official);
        List<LotteryDraw> lds = dao.query(hql);
        if(lds.size()>0){
        	return lds.get(0);
        }else{
        	return null;
        }
	}
	@Override
	public LotteryDraw checkSurplus(LotteryDraw ld,String iphone) {
		// TODO Auto-generated method stub
		User user = (User) dao.loadObject("from User where email = '"+iphone+"'");
		if(ld.getSurplus() == 0){
			user.setNumber(user.getNumber() - 1);
			dao.saveOrUpdate(user);
		}else{
			ld.setSurplus(ld.getSurplus() - 1);
		}
		return ld;
	}
	@Override
	public void decreaseNum(Activities activities) {
		// TODO Auto-generated method stub
		activities.setNum(activities.getNum()-1);
		dao.saveOrUpdate(activities);
	}

}
