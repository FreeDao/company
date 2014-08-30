package com.era.service;

import java.util.List;

import com.era.orm.RecommendType;
import com.era.orm.Retrieve;
import com.era.orm.Recommend;
import com.era.orm.Seller;
import com.era.orm.Activities;
import com.era.orm.Issue;
import com.era.orm.LotteryDraw;
import com.era.orm.Tock;
import com.era.orm.User;
import com.era.orm.Winning;

public interface LotteryDrawService 
{
	/**
	 * 添加抽奖号码
	 * @return
	 */
	public boolean addLotteryDraw(LotteryDraw draw);
	
	/**
	 * 查询我的抽奖号
	 * @param iphone
	 * @param designated
	 * @return
	 */
	public List<Issue> selMyLotteryDraw(String official,String iphone,String designated,String pageNo,String pageNum);
	/**
	 * 查询活动
	 * @param pageNo
	 * @param pageNum
	 * @param cityId 
	 * @return
	 */
	public List<Activities> selActivities(String log,String dim,String yz,String lack,String pageNo,String pageNum,String sellerId,String official, String cityId);
	/**
	 * 期号
	 * @return
	 */
	public List<Issue> selIssue(String iphone,String pageNo,String pageNum);
	/**
	 * 我的中奖信息
	 * @param sms
	 * @param pageNo
	 * @param num
	 * @return
	 */
	public List<LotteryDraw> selWinning(String sms,String pageNo,String pageNum,String official);
	/**
	 * 我今天还有几次抽奖机会
	 * @param iphone
	 * @param official 
	 * @param string 
	 * @return
	 */
	public int setMyLackLotter(String iphone, String activitiesId, String official);
	/**
	 * 查询是否当前期
	 * @return
	 */
	public Issue selIssueId();
	/**
	 * 添加推送的人
	 * @param tock
	 * @return
	 */
	public boolean addTock(Tock tock);
	/**
	 * 安卓推送
	 * @return
	 */
	public List<Seller> selSellerTui();
	/**
	 * 更新商户
	 * @param seller
	 */
	public void addSeller(Seller seller);
	/**
	 * 查询应用汇
	 * @param isIos
	 * @param ismend
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public List<Recommend> selRecommend(String cityId,String isIos,String ismend,String typeId,String pageNo,String pageNum);
	/**
	 * 查看应用汇分类
	 * @return
	 */
	public List<RecommendType> selRecommendType(String hot);
	/**
	 * 找回密码
	 * @param parameter
	 * @param parameter2
	 * @return
	 */
	public List<Retrieve> selRetrievePassWord(String parameter, String parameter2);
	/**
	 * 添加找回密码验证信息
	 * @param retrieve
	 * @return
	 */
	public boolean addRetrieve(Retrieve retrieve);
	/**
	 * 修改密码
	 * @param parameter
	 * @param md5
	 * @return
	 */
	public boolean updaePassWord(String parameter, String md5);

	public int selTockWY(String parameter);

	public Activities selActivitiesId(Integer valueOf);

	public User selUserId(String parameter);

	public boolean updateUser(String parameter, int i);

	public boolean updateUserJi(String parameter, int i, int j);

	public LotteryDraw getLotteryDraw(String iphone, String activitiesId, String official);

	public LotteryDraw checkSurplus(LotteryDraw ld,String iphone);

	public void decreaseNum(Activities activities); 
}
