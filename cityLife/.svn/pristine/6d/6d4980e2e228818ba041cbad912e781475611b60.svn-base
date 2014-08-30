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
import com.era.orm.Winning;
import com.era.service.LotteryDrawService;
import com.era.util.BaseUtils;
import com.era.util.DateUtil;

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
	public List<LotteryDraw> selMyLotteryDraw(String iphone,String pageNo,String pageNum) 
	{
		List<LotteryDraw> list = null;
		String hql="from LotteryDraw ld where ld.isWin <> 0 and ld.isWin is not null and ld.iphone='"+iphone+"'";
		if(pageNo == null || pageNo.equals("") || pageNum == null || pageNum.equals("")) 
		{
			list = dao.query(hql);
		}
		else
		{
			list = dao.query(hql,Integer.valueOf(pageNo), Integer.valueOf(pageNum));
		}
		
		String now = DateUtil.getNowString("yyyy-MM-dd");
		int result = now.compareTo("2014-06-20");
		if(result > 0){
			result = 1;
		}else{
			result = 0;
		}
		
		for(LotteryDraw ld : list){
			ld.setIsPast(result);
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
	public List<Activities> selActivities(String lack,String pageNo, String pageNum) {
		List<Activities> list = null;
			if(pageNo == null || pageNo.equals("") || pageNum == null || pageNum.equals("")) 
			{
				list = dao.query("from Activities");
			}
			else
			{
				list = dao.query("from Activities",Integer.valueOf(pageNo), Integer.valueOf(pageNum));
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
				issueOne.setListDraw(listDraw);
				listIssue.add(issueOne);
			}
		}
		return listIssue;
	}
	@Override
	public List<Winning> selWinning(String sms, String pageNo, String pageNum) {
		List<Winning> list = null;
		String hql="from Winning where 1 = 1";
		if(sms == null || sms.equals(""))
		{
			
		}
		else
		{
			hql += " and iphone = '"+sms+"'";
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
	public Integer setMyLackLotter(String iphone)
	{
        Date date = new Date(); // 转换为标准时间对象
        java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String newTime = format1.format(date);
        String hql ="from LotteryDraw where iphone ='"+iphone+"' and addtime = '"+newTime+"'";
        List<LotteryDraw> lds = dao.query(hql);
        if(lds.size()>0){
        	LotteryDraw ld = lds.get(0);
        	if(ld.getIsWin() != 0){
        		return 0;
        	}else{
        		return ld.getSurplus();
        	}
        }else{
        	return BaseUtils.surplus;
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
	public List<Recommend> selRecommend(String name,String cityId,String isIos, String ismend, String typeId,
			String pageNo, String pageNum) {
		List<Recommend> list = null;
		String hql="from Recommend where 1 = 1";
		if( null == name || "".equals(name)){
			hql += " and isCompany = 1 ";
		}else{
			hql += " and isCompany = 0 and name = '"+BaseUtils.changeIos8859ToUtf8(name)+"' ";
		}
		/*if(cityId == null || cityId.equals(""))
		{
			
		}
		else
		{
			hql += " and cityId = "+cityId;
		}*/
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
		for(Recommend r : list){
			if( "1".equals(isIos) )
			{
				r.setUrl(r.getIurl());
			}
			else
			{
				r.setUrl(r.getAurl());
			}
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
			int i = dao.update("update User set passWord = '"+md5+"' where email = '"+parameter+"'");
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
	public Integer selRecommendNum(String name, String cityId, String isIos) {
		// TODO Auto-generated method stub
		String hql="from Recommend where 1 = 1";
		if( null == name || "".equals(name)){
			hql += " and isCompany = 1 ";
		}else{
			hql += " and isCompany = 0 and name = '"+BaseUtils.changeIos8859ToUtf8(name)+"' ";
		}
		/*if(isIos == null || isIos.equals(""))
		{
			
		}
		else
		{
			hql += " and isIos = "+isIos;
		}*/
		/*if(cityId == null || cityId.equals(""))
		{
			
		}
		else
		{
			hql += " and cityId = "+cityId;
		}*/
		return dao.query(hql).size();
	}
	
	@Override
	public LotteryDraw getLotteryDraw(String iphone) {
		// TODO Auto-generated method stub
		Date date = new Date(); // 转换为标准时间对象
        java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String newTime = format1.format(date);
        String hql ="from LotteryDraw where iphone ='"+iphone+"' and addtime = '"+newTime+"'";
        List<LotteryDraw> lds = dao.query(hql);
        if(lds.size()>0){
        	return lds.get(0);
        }else{
        	return null;
        }
	        
	}



}
