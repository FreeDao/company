package com.era.action;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.era.orm.Comment;
import com.era.orm.Seller;
import com.era.service.CommentService;
import com.era.service.SellerService;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

public class AppAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private CommentService commentService;
	private SellerService sellerService;
	
	private Map<String, Object> map;
	
	/**
	 * 跳转到详细内容页面
	 * @return
	 */
	public String detail(){
		try {
			int total = commentService.countComment(Integer.valueOf(request.getParameter("sellerId")));
			List<Comment> list = commentService.getCommentInfo(Integer.valueOf(request.getParameter("sellerId")),1,3);
			request.setAttribute("sellerId", request.getParameter("sellerId"));
			request.setAttribute("total", total);
			request.setAttribute("log", request.getParameter("log"));
			request.setAttribute("dim", request.getParameter("dim"));
			request.setAttribute("commentList", list);
			request.setAttribute("type", URLDecoder.decode(request.getParameter("type"),"UTF-8"));
			request.setAttribute("typeId", request.getParameter("typeId"));
			request.setAttribute("cityId", request.getParameter("cityId"));
			request.setAttribute("logo", request.getParameter("logo"));
			request.setAttribute("title", URLDecoder.decode(request.getParameter("title"),"UTF-8"));
			if( null != request.getParameter("level") && !"".equals(request.getParameter("level"))){
				request.setAttribute("level", request.getParameter("level").substring(0, 1));
			}else{
				request.setAttribute("level", 1);
			}
			request.setAttribute("preferen", request.getParameter("preferen"));
			request.setAttribute("phone", request.getParameter("phone"));
			request.setAttribute("address", URLDecoder.decode(request.getParameter("address"),"UTF-8"));
			request.setAttribute("product", URLDecoder.decode(request.getParameter("product"),"UTF-8"));
			request.setAttribute("productImgs", request.getParameter("productImgs"));
			request.setAttribute("brief", URLDecoder.decode(request.getParameter("brief"),"UTF-8"));
			request.setAttribute("briefImgs", request.getParameter("briefImgs"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到详细内容页面(分享接口)
	 * @return
	 */
	public String detailShare(){
		try {
			String sellerId = request.getParameter("sellerId");
			
			Seller seller = sellerService.getSellerOneInfo(Integer.parseInt(sellerId));
			
			int total = commentService.countComment(Integer.valueOf(request.getParameter("sellerId")));
			List<Comment> list = commentService.getCommentInfo(Integer.valueOf(request.getParameter("sellerId")),1,3);
			
			
			request.setAttribute("sellerId", sellerId);
			request.setAttribute("total", total);
			request.setAttribute("log", seller.getLog());
			request.setAttribute("dim", seller.getDim());
			request.setAttribute("commentList", list);
			request.setAttribute("type", seller.getType());
			request.setAttribute("typeId", seller.getTypeId());
			request.setAttribute("cityId", seller.getCityId());
			request.setAttribute("logo", seller.getLogo());
			request.setAttribute("title", seller.getTitile());
			if( null != seller.getLevel() && !"".equals(seller.getLevel())){
				request.setAttribute("level", seller.getLevel().toString().substring(0, 1));
			}else{
				request.setAttribute("level", 1);
			}
			request.setAttribute("preferen", seller.getPreferential());
			request.setAttribute("phone", seller.getPhone());
			request.setAttribute("address", seller.getAddress());
			request.setAttribute("product", seller.getName());
			request.setAttribute("productImgs", seller.getProductImgs());
			request.setAttribute("brief", seller.getBrief());
			request.setAttribute("briefImgs", seller.getSellerImgs());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询所有的评论
	 * @return
	 */
	public String comment(){
		try {
			Integer pageNow = null == request.getParameter("pageNow") || "".equals(request.getParameter("pageNow"))? 1 : Integer.parseInt(request.getParameter("pageNow"));
			Integer limit =  null == request.getParameter("limit") || "".equals(request.getParameter("limit"))? 15 : Integer.parseInt(request.getParameter("limit"));
			int total = commentService.countComment(Integer.valueOf(request.getParameter("sellerId")));
			List<Comment> list = commentService.getCommentInfo(Integer.valueOf(request.getParameter("sellerId")),pageNow,limit);
			request.setAttribute("total", total);
			request.setAttribute("commentList", list);
			request.setAttribute("sellerId", request.getParameter("sellerId"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	/**
	 * 微信平台开发者验证
	 * @return
	 */
	public String urlVaild(){
		try {
			
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostring = request.getParameter("echostr");
		String token = "pengyou";
		
		String[] tempArry = {token,timestamp,nonce};
		Arrays.sort(tempArry);
		
		String total = "";
        for (String string : tempArry) {
            total = total + string;
        }
        
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        sha1.update(total.getBytes());
        
        byte[] codedBytes = sha1.digest();
        String codedString = new BigInteger(1, codedBytes).toString(16);//将加密后的字节数组转换成字符串。参见http://hi.baidu.com/aotori/item/c94813c4f15caa60f6c95d4a
        if (codedString.equals(signature)) { //将加密的结果与请求参数中的signature比对，如果相同，原样返回echostr参数内容
            OutputStream os = response.getOutputStream();
            BufferedWriter resBr = new BufferedWriter(new OutputStreamWriter(os));
            resBr.write(echostring);
            resBr.flush();
            resBr.close();
        }else{
        	response.getWriter().print("错误！");
        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * app下载页面
	 * @return
	 */
	public String download(){
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public SellerService getSellerService() {
		return sellerService;
	}

	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

}
