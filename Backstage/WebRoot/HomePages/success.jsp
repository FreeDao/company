<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <html>
   <head>
     <base href="<%=basePath%>">
     <title>小区通</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  
  
    <script src="<%=request.getContextPath() %>/book/js/jquery-1.10.2.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath() %>/book/js/jquery.easyui.min.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath() %>/book/js/easyui-lang-zh_CN.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath() %>/book/js/index.js" type="text/javascript"></script>        
  <link href="<%=request.getContextPath() %>/book/css/default/easyui.css" rel="stylesheet" type="text/css" />
  <link href="<%=request.getContextPath() %>/book/css/icon.css" rel="stylesheet" type="text/css" />
  <link href="<%=request.getContextPath() %>/book/icons/icon-all.css" rel="stylesheet" type="text/css" />
  <script src="<%=request.getContextPath() %>/book/portal/jquery.portal.js" type="text/javascript"></script>
  <link href="<%=request.getContextPath() %>/book/portal/portal.css" rel="stylesheet" type="text/css" />
  <script src="<%=request.getContextPath() %>/book/js/tabs.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath() %>/book/js/menu.js" type="text/javascript"></script>
  </head>
   <body  class="easyui-layout" data-options="fit: true"  style="padding: 0px; ">
	   <div data-options="region: 'west', title: '小区通菜单导航栏', iconCls: 'icon-redo', split: true, minWidth: 250, maxWidth: 500" style="width: 250px; padding: 1px;">
		   		<div  class="easyui-accordion" data-options="fit: true,border:false"> 
		   			 
		   			 <c:if test="${LoginUser.role == 1 }">
		   			 <div    data-options="title:'账号管理',iconCls: 'icon-txl', selected:true" style=" padding: 10px;">		   			 	
					                <div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="javascript:void(0)" attr="book/city/account.jsp">城市账号</a>
					                            </div>
					                        </li>
					                    </ul>
					                </div>  
					                <div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="javascript:void(0)" attr="book/city/busmarket.jsp">小区主账号</a>
					                            </div>
					                        </li>
					                    </ul>
					                </div>  
					                <div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="javascript:void(0)" attr="book/city/property.jsp">物业账号</a>
					                            </div>
					                        </li>
					                    </ul>
					                </div>             
					                <div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="javascript:void(0)" attr="book/market/seller.jsp">商户账号</a>
					                            </div>
					                        </li>
					                    </ul>
					                </div>     
		   			 </div>	
		   			 <div    data-options="title:'购物站数据',iconCls: 'icon-dx'" style=" padding: 10px;">	
		   			 		<div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/market/commodity.jsp">购物站管理</a></div>
			                        </li>
			                    </ul>
			                </div> 
		   			 </div>
		   			 <div  data-options="title:'基础数据维护',iconCls: 'icon-qt'" style=" padding: 10px;">
					   		 <div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/city/city.jsp">城市管理</a></div>
			                        </li>
			                    </ul>
			                </div>
					   		<div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/city/quarters.jsp">小区管理</a></div>
			                        </li>
			                    </ul>
			                </div>               
			           </div>
		   			 <!-- <div    data-options="title:'便民/小区',iconCls: 'icon-dx'" style=" padding: 10px;">		   			 	
						   		   <div>
				                    <ul>
				                        <li>
				                            <div>
				                                <a target="mainFrame" href="javascript:void(0)" attr="book/market/market.jsp">便民/小区 市场</a></div>
				                        </li>
				                    </ul>
				                </div>                
				                <div>
				                    <ul>
				                        <li>
				                            <div>
				                                <a target="mainFrame" href="javascript:void(0)" attr="book/market/seller.jsp">便民/小区 商户</a></div>
				                        </li>
				                    </ul>
				                </div>               
				                <div>
				                    <ul>
				                        <li>
				                            <div>
				                                <a target="mainFrame" href="javascript:void(0)" attr="book/market/share.jsp">小区分享</a></div>
				                        </li>
				                    </ul>
				                </div>               
		   			 </div>	
		   			 <div    data-options="title:'商家',iconCls: 'icon-qt'" style=" padding: 10px;">
			                <div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/seller/quarters.jsp">商家中奖信息</a></div>
			                        </li>
			                    </ul>
			                </div>	  
			                 <div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/seller/video.jsp">房屋租售</a></div>
			                        </li>
			                    </ul>
			                </div>		 			 	
		   			 </div>	
		   			 <div    data-options="title:'积分商城/购物站',iconCls: 'icon-sz'" style=" padding: 10px;">
							   		<div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="javascript:void(0)" attr="book/shop/official.jsp">商城信息</a></div>
					                        </li>
					                    </ul>
					                </div>                
					                <div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="javascript:void(0)" attr="book/shop/order.jsp">订单</a></div>
					                        </li>
					                    </ul>
					                </div>               
					                <div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="javascript:void(0)" attr="book/shop/shoptype.jsp">购物站分类</a></div>
					                        </li>
					                    </ul>
					                </div>               
					                <div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="javascript:void(0)" attr="book/shop/shoptypetwo.jsp">购物站第二类</a></div>
					                        </li>
					                    </ul>
                                    </div>               
		   			 </div>	
		   			 
		   			 <div  data-options="title:'小区/城市',iconCls: 'icon-qt'" style=" padding: 10px;">
					   		<div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/city/quarters.jsp">小区管理</a></div>
			                        </li>
			                    </ul>
			                </div>               
			                <div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/city/city.jsp">城市管理</a></div>
			                        </li>
			                    </ul>
			                </div>	  
			                 <div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/city/bife.jsp">社区头条</a></div>
			                        </li>
			                    </ul>
			                </div> 	
			                 <div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/city/official.jsp">官方抽奖</a></div>
			                        </li>
			                    </ul>
			                </div>		 	
		   			 </div>	
		   			 
		   			 
		   			 <div    data-options="title:'更多',iconCls: 'icon-qt'" style=" padding: 10px;">
					   		<div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/more/review.jsp">评论</a></div>
			                        </li>
			                    </ul>
			                </div>               
			                <div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/more/checkversion.jsp">版本更新</a></div>
			                        </li>
			                    </ul>
			                </div>	  
			                 <div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/more/putbackMessage.jsp">意见反馈</a></div>
			                        </li>
			                    </ul>
			                </div> 	
		   			 </div> -->	
		   			 
		   			 </c:if>
		   			 <c:if test="${LoginUser.role == 4 }">
		   			 <div    data-options="title:'商家',iconCls: 'icon-qt'" style=" padding: 10px;">
					   		<div>
			                    <ul>
			                        <li>
			                            <div>
			                                <strong><a target="mainFrame" href="javascript:void(0)" attr="book/seller/info.jsp">个人信息</a></strong></div>
			                        </li>
			                    </ul>
			                </div>  
					   		<div>
			                    <ul>
			                        <li>
			                            <div>
			                                <strong><a target="mainFrame" href="javascript:void(0)" attr="book/seller/official.jsp">商家活动</a></strong></div>
			                        </li>
			                    </ul>
			                </div>               
		   			 </div>	
		   			 </c:if>
		   			 
		   			  <c:if test="${LoginUser.role == 3 }">
		   			 <div    data-options="title:'小区主',iconCls: 'icon-qt'" style=" padding: 10px;">
					   		<div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/market/commodity.jsp">发布商品</a></div>
			                        </li>
			                    </ul>
			                </div>  
			                 <div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/market/seller.jsp">添加商户</a>
			                            </div>
			                        </li>
			                    </ul>
			                </div> 
		   			 </div>	
		   			 </c:if>
		   			 
		   			   <c:if test="${LoginUser.role == 5 }">
		   			 <div data-options="title:'物业管理',iconCls: 'icon-qt'" style=" padding: 10px;">
					   		<div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/city/bife.jsp">社区头条/通知</a>
			                                </div>
			                        </li>
			                    </ul>
			                </div>  
			                <div>
			                    <ul>
			                        <li>
			                           <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/city/element.jsp">发布物业</a>
			                           </div>
			                        </li>
			                    </ul>
			                </div> 
			                <div>
			                    <ul>
			                        <li>
			                           <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/city/repair.jsp">查看报修</a>
			                           </div>
			                        </li>
			                    </ul>
			                </div> 
		   			 </div>	
		   			 </c:if>
		   			 
		   			 <c:if test="${LoginUser.role == 2 }">
		   			 <div data-options="title:'城市管理人员',iconCls: 'icon-qt'" style=" padding: 10px;">
					   		<div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/city/busmarket.jsp">小区主管理</a>
			                                </div>
			                        </li>
			                    </ul>
			                </div>  
			                <div>
			                    <ul>
			                        <li>
			                           <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/market/seller.jsp">商家管理</a>
			                           </div>
			                        </li>
			                    </ul>
			                </div> 
			                <div>
			                    <ul>
			                        <li>
			                           <div>
			                                <a target="mainFrame" href="javascript:void(0)" attr="book/city/property.jsp">物业管理</a>
			                           </div>
			                        </li>
			                    </ul>
			                </div> 
		   			 </div>	
		   			 </c:if>
		   			 
		   		</div>        
	   </div>
	  
	   <div data-options="region: 'center'" style="padding: 1px;">
		        <div  id="tabs" class="easyui-tabs" data-options="fit: true, border: true">
			            <div  data-options="title: '主页', iconCls: 'icon-hamburg-home'">
			                    <div class="easyui-portal" data-options="fit: true, border: false">
			                            <div style="width: 100%;">
			                                <div data-options="title: '项目简介', height: 330, collapsible: true">
			                                
			                                </div>	 
			                                 <div data-options="title: '公司简介', height: 360, collapsible: true">
			                                
			                                </div>	                                     
			                            </div>
			                    </div>
			           </div>
			           
		       </div>
		       
     </div>
	   
	  <div data-options="region: 'east', title: '日历', iconCls: 'icon-calendar', split: true, minWidth: 200, maxWidth: 500" style="width: 220px; padding: 1px; border-left-width: 0px;">
				  <div  class="easyui-layout" data-options="fit: true">
		            <div data-options="region: 'north', split: false, border: false" style="height: 220px;">
		                <div class="easyui-calendar" data-options="fit: true"></div>
		            </div>
		            <div data-options="region: 'center', title: '友情链接'">
		            <br>
		            	<div>&nbsp;&nbsp;<a href="#">苹果app地址</a></div>
		            	<br>
		            	<div>&nbsp;&nbsp;<a href="#">安卓app地址</a></div>
		            	<br>
		            	<div>&nbsp;&nbsp;<a href="#">小区主后台地址</a></div>
		            	<br>
		            	<div>&nbsp;&nbsp;<a href="#">商户后台地址</a></div>
		            </div>		         
		        </div>
      </div>
	   	
	   <div data-options="region: 'south', title: '关于...', iconCls: 'icon-help', collapsed: true, border: false" style="height: 70px;"></div>
</body>
</html>