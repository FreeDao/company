package com.marck.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//1,doFilter方法的第�?��参数为ServletRequest对象。此对象给过滤器提供了对进入的信息（包括表单数据、cookie和HTTP请求头）的完全访问�?第二个参数为ServletResponse，�?常在�?��的过滤器中忽略此参数。最后一个参数为FilterChain，此参数用来调用servlet或JSP页�?

		  HttpServletRequest request = (HttpServletRequest)servletRequest;//;//如果处理HTTP请求，并且需要访问诸如getHeader或getCookies等在ServletRequest中无法得到的方法，就要把此request对象构�?成HttpServletRequest
		  HttpServletResponse response = (HttpServletResponse)servletResponse;

		  String currentURL = request.getRequestURI();//取得根目录所对应的绝对路�?

		  String targetURL = currentURL.substring(currentURL.indexOf("/", 1), currentURL.length());  //截取到当前文件名用于比较
		  
		  HttpSession session = request.getSession(false);
		  
		  if (!"/bg/user!login".equals(targetURL) ) {//判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循�?
		   if (session == null || session.getAttribute("userSession") == null) {//*用户登录以后�?��动添加session
		    request.setAttribute("msg", "*请先登陆");
		    response.sendRedirect("../login.jsp");//如果session为空表示用户没有登录就重定向到login.jsp页面
		    return;
		   }
		  }
		  //加入filter链继续向下执�?
		  filterChain.doFilter(request, response);//.调用FilterChain对象的doFilter方法。Filter接口的doFilter方法取一个FilterChain对象作为它的�?��参数。在调用此对象的doFilter方法时，�?��下一个相关的过滤器�?如果没有另一个过滤器与servlet或JSP页面关联，则servlet或JSP页面被激活�?

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
