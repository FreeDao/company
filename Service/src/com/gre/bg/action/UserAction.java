package com.gre.bg.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.bg.service.UserService;
import com.gre.common.BaseAction;
import com.gre.common.CommonUtil;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.FeedBack;
import com.gre.common.model.UpdateInfo;
import com.gre.common.model.User;

@Component("bgUserAction")
@Scope("prototype")
public class UserAction extends BaseAction{

	@Autowired
	private UserService userService;
	
	private User user; 
	
	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	/**
	 * 系统首页
	 * @return
	 */
	public String index(){
		try {
			pu = new PageUtil();
			pu.setUrl("bg/user!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("方法index出错");
		}
		return ERROR;
	}
	
	/**
	 * 用户登陆
	 * @return
	 */
	public String login(){
		try {
			if(!"admin".equals(user.getUserName())){
				return "fail";
			}
			user = userService.findUser(user.getUserName(),CommonUtil.Md5(user.getPassWord()));
			if( null == user ){
				request.setAttribute("msg", "账号或密码错误！");
				return "fail";
			}else{
				request.getSession().setAttribute("userSession", user);
				return "login";
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("方法login出错");
		}
		return ERROR;
	}

	/**
	 * 查询自己的信息
	 * @return
	 */
	public String myInfo(){
		try {
			user = (User) userService.findObjectById(User.class,user.getId());
			return "info";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("方法myInfo出错");
		}
		return ERROR;
	}
	
	/**
	 * 查询所有的反馈信息
	 * @return
	 */
	public String selAllFeedBack(){
		try {
			pu = userService.findAllList(FeedBack.class,pageNow,limit);
			pu.setUrl("bg/user!selAllFeedBack");
			return "feedBackList";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("方法selAllFeedBack出错");
		}
		return ERROR;
	}
	
	/**
	 * 查询版本编号
	 * @return
	 */
	public String selVersion(){
		try {
			List<UpdateInfo> uis = (List<UpdateInfo>) userService.findAllList(UpdateInfo.class);
			UpdateInfo android = new UpdateInfo();
			UpdateInfo ios = new UpdateInfo();
			for( int i=0 ; i < uis.size() ; i++){
				if(uis.get(i).getPlatForm() == 0){
					ios = uis.get(i);
				}else if(uis.get(i).getPlatForm() == 1){
					android = uis.get(i);
				}
			}
			pu = new PageUtil();
			pu.setUrl("bg/user!selVersion");
			request.setAttribute("android", android);
			request.setAttribute("ios", ios);
			request.setAttribute("iisForced", ios.getIsForced());
			request.setAttribute("aisForced", android.getIsForced());
			return "version";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("方法selversion出错");
		}
		return ERROR;
	}
	
	/**
	 * 更新版本信息
	 * @return
	 */
	public String updateVersion(){
		try {
			String iisForced = request.getParameter("iisForced");
			String iversion = request.getParameter("iversion");
			String iurl = request.getParameter("iurl");
			String icontent = request.getParameter("icontent");
			
			String aisForced = request.getParameter("aisForced");
			String aversion = request.getParameter("aversion");
			String aurl = request.getParameter("aurl");
			String acontent = request.getParameter("acontent");
			
			UpdateInfo ios = userService.findUpdateInfoByPlatForm(0);
			UpdateInfo andorid = userService.findUpdateInfoByPlatForm(1);
			if(null == ios){
				ios = new UpdateInfo();
			}
			if(null == andorid){
				andorid = new UpdateInfo();
			}
			ios.setContent(icontent);
			ios.setPlatForm(0);
			ios.setUrl(iurl);
			ios.setVersion(iversion);
			if( null!= iisForced && !"".equals(iisForced) ){
				ios.setIsForced(Integer.parseInt(iisForced));
			}else{
				ios.setIsForced(0);
			}
			userService.saveOrUpdateObj(ios);
			
			andorid.setContent(acontent);
			andorid.setPlatForm(1);
			andorid.setUrl(aurl);
			andorid.setVersion(aversion);
			if( null!= aisForced && !"".equals(aisForced) ){
				andorid.setIsForced(Integer.parseInt(aisForced));
			}else{
				andorid.setIsForced(0);
			}
			userService.saveOrUpdateObj(andorid);
			request.setAttribute("msg", 1);
			return selVersion();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.error("方法updateVersion出错");
		}
		return ERROR;
	}
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public String selAllAccount(){
		try {
			pu = userService.findAllList(User.class,pageNow,limit);
			pu.setUrl("bg/user!selAllAccount");
			return "AccountList";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("方法selAllAccount出错");
		}
		return ERROR;
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEditAccount(){
		try {
			if( null != user ){
				user = (User) userService.findObjById(User.class,user.getId());
			}
			return "addOrEditAccount";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改信息
	 * @return
	 */
	public String addOrEditAccount(){
		try {
			User u = new User();
			if( null != user.getId() ){
				u = (User) userService.findObjById(User.class, user.getId());
				user.setUserName(u.getUserName());
				user.setPassWord(u.getPassWord());
				user.setAddTime(u.getAddTime());
			}else{
				user.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			}
			
			userService.saveOrUpdateObj(user);
			request.setAttribute("msg", 1);
			return selAllAccount();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("UserAction中方法addOrEditAccount出错");
			return selAllAccount();
		}
	}
	
	
	/**
	 * 删除指用户反馈信息
	 * @return
	 */
	public String delFeedBack(){
		try {
			String id = request.getParameter("id");
			userService.delObjById(FeedBack.class,Integer.parseInt(id));
			request.setAttribute("msg", 1);
			return selAllFeedBack();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("UserAction中方法delFeedBack出错");
			return selAllFeedBack();
		}
	}
	
	/**
	 * 用户注销
	 * @return
	 */
	public String logout(){
		try {
			session.invalidate();
			return "logout";
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("UserAction中方法logout出错");
		}
		return ERROR;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PageUtil getPu() {
		return pu;
	}

	public void setPu(PageUtil pu) {
		this.pu = pu;
	}

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
