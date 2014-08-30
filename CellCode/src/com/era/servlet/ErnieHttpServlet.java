package com.era.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.era.util.BaseUtils;
import com.era.util.alertInFo;

public class ErnieHttpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletFileUpload upload;
	private final long MAXSize = 4194304 * 2L;// 4*2MB
	private String filedir = null;
	public StringBuffer url;  
	public String projectUrl; 

	/**
	 * Constructor of the object.
	 */
	public ErnieHttpServlet() {
		super();
	}

	/**
	 * 设置文件上传的初始化信息 Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {

		FileItemFactory factory = new DiskFileItemFactory();
		this.upload = new ServletFileUpload(factory);
		this.upload.setSizeMax(this.MAXSize);
		filedir = config.getServletContext().getRealPath("/uploadImgs/");
		System.out.println("filedir=" + filedir);
	}
	
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.url = request.getRequestURL();
		this.projectUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).toString();
		
		JSONObject json = new JSONObject();
		try {
			String fileName = "";		
			String imgUrl_str = "";
			String title = "";//标题
			String address = "";//标题
			String prize = "";//奖品
			String bife = "";
			Integer personal = Integer.parseInt(request.getParameter("personal"));
			Integer sellerId = Integer.parseInt(request.getParameter("sellerId"));
			String price = request.getParameter("price");//价格
			String num = request.getParameter("num");//份数
			if ( BaseUtils.isChinaese(request.getParameter("address"))) {
				// 用于IOS客户端传递过来的用户名
				address = new String(request.getParameter("address").getBytes("ISO-8859-1"),"UTF-8");
			} else {
				address = request.getParameter("address");
			}
			Map<String, String> json1 = alertInFo.getGeocoderLatitude(address);
			if(json == null || json.equals(""))
			{
				json.put("responseCode", "1");
				json.put("msg", "未找到此地址");
			}
			else
			{
				if ( BaseUtils.isChinaese(request.getParameter("title"))) {
					// 用于IOS客户端传递过来的用户名
					title = new String(
							request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
				} else {
					title = request.getParameter("title");
				}
				if ( BaseUtils.isChinaese(request.getParameter("bife"))) {
					// 用于IOS客户端传递过来的用户名
					bife = new String(
							request.getParameter("bife").getBytes("ISO-8859-1"), "UTF-8");
				} else {
					bife = request.getParameter("bife");
				}
					boolean flag = false;
					try {
						List<FileItem> items = this.upload.parseRequest(request);
	
						for (FileItem fileItem : items) {
							fileName = fileItem.getName();
							String typeName = fileName.substring(fileName.lastIndexOf("."));
							fileName = BaseUtils.Md5(fileItem.getInputStream()) + typeName;
							
							String filepath = filedir + File.separator + fileName;
							File file = new File(filepath);
							InputStream inputSteam = fileItem.getInputStream();
							BufferedInputStream fis = new BufferedInputStream(
									inputSteam);
							FileOutputStream fos = new FileOutputStream(file);
							int f;
							while ((f = fis.read()) != -1) {
								fos.write(f);
							}
							fos.flush();
							fos.close();
							fis.close();
							inputSteam.close();
						}
						flag = true;
						imgUrl_str = projectUrl+"/uploadImgs/" + fileName;
					} catch (FileUploadException e) {
						e.printStackTrace();
						response.getWriter().write("上传文件失败:" + e.getMessage());
						flag = false;
					}
					if(flag){
						String sql = "";
						sql = "insert into activities (title,bife,logo,addtime,lotteryAddtime,prize,personal,rate,rootQode,address,iphone,sellId,log,dim,price,num) values ('"+title+"','"+bife+"','"+imgUrl_str+"','"+BaseUtils.getNowStringDateTime(new Date())+"','"+request.getParameter("lotteryAddtime")+"','"+prize+"',"+personal+","+request.getParameter("rate")+","+request.getParameter("or")+",'"+address+"','"+request.getParameter("iphone")+"',"+sellerId+",'"+json1.get("lng")+"','"+json1.get("lat")+"','"+price+"','"+num+"')";
						Connection conn = null;
						PreparedStatement pstmt = null;
						int temp = -1;
						try {
							// 获得conn连接
							conn = BaseUtils.getConnection();					pstmt = conn.prepareStatement(sql);
							temp = pstmt.executeUpdate();
							// 关闭连接
							pstmt.close();
							conn.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (temp > 0) {
							json.put("responseCode", "0");
						} else {

							json.put("responseCode", "1");
						}
					}else{

						json.put("responseCode", "1");
					}
			}
		} catch (Exception e) {
			json.put("responseCode", "1");
			json.put("msg", "添加异常");
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");


		response.getWriter().print(json.toString());

		response.getWriter().close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
