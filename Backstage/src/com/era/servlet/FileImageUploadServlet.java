package com.era.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

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

import com.era.orm.Share;
import com.era.util.BaseUtils;

/**
 * 文件上传Servle
 * @author jiajiantao
 * @fileName  FileImageUploadServlet.java
 * @packageName com.era.servlet
 * @createTime 2013-5-17下午7:51:29
 * @updateTime 2013-5-17下午7:51:29
 * @version V1.0
 *
 */
public class FileImageUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletFileUpload upload;
	private final long MAXSize = 4194304 * 2L;// 4*2MB
	private String filedir = null;

	/**
	 * Constructor of the object.
	 */
	public FileImageUploadServlet() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fileName = "";		
		String imgUrl_str = "";
		String results;
		String userId = request.getParameter("userId");
		String villageId = request.getParameter("villageId");
		String bife = "";

		JSONObject json = new JSONObject();
		
		boolean torf = BaseUtils.isChinaese(request.getParameter("bife"));
		if (torf) {
			try {
				// 用于IOS客户端传递过来的用户名
				bife = new String(request.getParameter("bife").getBytes("ISO-8859-1"),"UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			bife = request.getParameter("bife");
		}
		
			boolean flag = true;
			try {
				List<FileItem> items = this.upload.parseRequest(request);

				if (items != null && !items.isEmpty()) {
					for (FileItem fileItem : items) {
						fileName = fileItem.getName();
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
					imgUrl_str = BaseUtils.SERVER_IP_STR + fileName;

				} else {
					flag = false;
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
				response.getWriter().write("上传文件失败:" + e.getMessage());
				flag = false;
			}

			if(flag){
	
				String sql = "";
				sql = "insert into Share (addtime,bife,userId,praise,villageId,images) values ('"+BaseUtils.getNowStringDateTime(new Date())+"','"+bife+"',"+userId+",0,"+villageId+",'"+imgUrl_str+"')";
				Connection conn = null;
				PreparedStatement pstmt = null;
				int num = -1;
				try {
					// 获得conn连接
					conn = BaseUtils.getConnection();					pstmt = conn.prepareStatement(sql);
					num = pstmt.executeUpdate();
					// 关闭连接
					pstmt.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (num > 0) {

					json.put("responseCode", "0");
					json.put("addtime", BaseUtils.getNowStringDateTime(new Date()));
					json.put("bife", bife);
					json.put("imgUrl_str", imgUrl_str);
				} else {

					json.put("responseCode", "1");
				}
			}else{

				json.put("responseCode", "1");
			}
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");


		response.getWriter().print(json.toString());

		System.out.println("json object :" + json.toString());
		System.out.println("JqueryAjaxServlet : end");

		response.getWriter().close();
	}
}
