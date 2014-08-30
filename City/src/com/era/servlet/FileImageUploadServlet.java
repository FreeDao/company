package com.era.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

		System.out.println("------------------FileImageUploadServlet---------------doPost---------------------------");

		String fileName = "";		
		String imgUrl_str = "";
		String results = "";
		String name_str = request.getParameter("userName");
		

		System.out.println("FileImageUploadServlet name ----->" + name_str+ "<---------");

		// PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();

		if (name_str != null) {
			System.out.println("FileImageUploadServlet imgUrl ----->"+ imgUrl_str + "<---------");

			boolean flag = false;
			try {
				List<FileItem> items = this.upload.parseRequest(request);
				System.out.println(items.size());

				if (items != null && !items.isEmpty()) {
					for (FileItem fileItem : items) {
						fileName = fileItem.getName();
						System.out.println(" fileName " + fileName);
						String filepath = filedir + File.separator + fileName;
						System.out.println("文件保存路径为:" + filepath);
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
						System.out.println("文件：" + fileName + "上传成功!");
					}
					System.out.println("上传文件成功!");

					flag = true;

					System.out.println("1 图片存放服务器地址：--->"+ BaseUtils.SERVER_IP_STR + fileName + "<---");

					imgUrl_str = BaseUtils.SERVER_IP_STR + fileName;

					System.out.println("2 imgUrl_str：--->"+ BaseUtils.SERVER_IP_STR + fileName + "<---");
				} else {
					flag = false;
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
				response.getWriter().write("上传文件失败:" + e.getMessage());
				flag = false;
			}

			if(flag){
				name_str = new String(name_str.getBytes("ISO-8859-1"),"UTF-8");

				String sql = "update user set imgUrl ='" + imgUrl_str+ "' where nickName = '" + name_str + "'";
				
				System.out.println(sql);
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				int num = -1;
				try {
					// 获得conn连接
					conn = BaseUtils.getConnection();
					pstmt = conn.prepareStatement(sql);
					num = pstmt.executeUpdate();
					// 关闭连接
					pstmt.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("num ==>" + num + "<==");

				if (num > 0) {
					results = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ imgUrl_str + "}";
				} else {
					results = "{\"responseCode\":\"" + 1 + "\"}";
				}
			}else{
				results = "{\"responseCode\":\"" + 1 + "\"}";
			}
		} else {
			results = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(results);
	}
}
