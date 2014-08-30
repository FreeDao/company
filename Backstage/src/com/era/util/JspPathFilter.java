package com.era.util;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.ObjectHelper;

import com.opensymphony.xwork2.ActionContext;

/**
 * 过滤未登录用户直接通过走jsp页面访问资源
 * @author 蛮牛
 *
 */
public class JspPathFilter implements Filter {

	@SuppressWarnings("unused")
	private FilterConfig filterConfig = null;

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getServletPath();
		HttpServletRequest request1 = (HttpServletRequest)request;
		String igpath = path.substring(path.lastIndexOf("/") + 1);
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		// 如果用户未登录，通过在IE地址栏走login.jsp或者register.jsp的页面可以直接访问资源，否则就进行拦截
		if (igpath.equalsIgnoreCase("Index.jsp")) {
			chain.doFilter(request, response);
		} else {
			// 如果用户已经登录，则用户可以在同一个IE浏览器通过url来访问资源，否则直接进入登录页面
			if (session == null || session.getAttribute("LoginUser") == null) {
				String url = "<script language='javascript'>window.top.location.href='"
						+ req.getContextPath() + "/Index.jsp'</script>";
				Writer writer = response.getWriter();
				writer.write(url);
				writer.flush();
				writer.close();
				
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
	}

}
