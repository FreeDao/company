<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <html>
   <head>
     <base href="<%=basePath%>">
     <title>My JSP 'index.jsp' starting page</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <script src="book/js/jquery-1.10.2.js" type="text/javascript"></script>
  <script src="book/js/jquery.easyui.min.js" type="text/javascript"></script>
  <script src="book/js/easyui-lang-zh_CN.js" type="text/javascript"></script>
  <script src="book/js/index.js" type="text/javascript"></script>        
  <link href="book/css/default/easyui.css" rel="stylesheet" type="text/css" />
  <link href="book/css/icon.css" rel="stylesheet" type="text/css" />
  <link href="book/icons/icon-all.css" rel="stylesheet" type="text/css" />
  <script src="book/portal/jquery.portal.js" type="text/javascript"></script>
  <link href="book/portal/portal.css" rel="stylesheet" type="text/css" />
  <script src="book/js/tabs.js" type="text/javascript"></script>
  <script src="book/js/menu.js" type="text/javascript"></script>
  </head>
   <body  class="easyui-layout" data-options="fit: true"  style="padding: 0px; ">
	
	   <div data-options="region: 'north', border: false" style="height: 30px;">
		   	   	<div style="text-align: center; font-weight: bold">
	          通讯录Demo
	            </div> 
	   	</div>
	   	
	   <div data-options="region: 'west', title: '菜单导航栏', iconCls: 'icon-redo', split: true, minWidth: 250, maxWidth: 500" style="width: 250px; padding: 1px;">
		   		<div  class="easyui-accordion" data-options="fit: true,border:false"> 
		   			 <div    data-options="title:'通讯录',iconCls: 'icon-txl', selected:true" style=" padding: 10px;">		   			 	
							   			<div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="book/txl/txl.jsp">全部信息</a>
					                            </div>
					                        </li>
					                    </ul>
					                </div>              
					                 <div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="book/txl/txl.jsp">朋友</a>
					                            </div>
					                        </li>
					                    </ul>
					                </div>               
					                  <div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="book/txl/txl.jsp">同学</a>
					                            </div>
					                        </li>
					                    </ul>
					                </div>               
					                   <div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="book/txl/txl.jsp">未分组</a>
					                            </div>
					                        </li>
					                    </ul>
					                </div>                	   			 	
		   			 </div>	
		   			 <div    data-options="title:'短信',iconCls: 'icon-dx'" style=" padding: 10px;">		   			 	
						   		   <div>
				                    <ul>
				                        <li>
				                            <div>
				                                <a target="mainFrame" href="dx/dx.htm">收件箱</a></div>
				                        </li>
				                    </ul>
				                </div>                
				                <div>
				                    <ul>
				                        <li>
				                            <div>
				                                <a target="mainFrame" href="dx/dx.htm">发件箱</a></div>
				                        </li>
				                    </ul>
				                </div>               
				                <div>
				                    <ul>
				                        <li>
				                            <div>
				                                <a target="mainFrame" href="dx/dx.htm">收藏夹</a></div>
				                        </li>
				                    </ul>
				                </div>               
				                <div>
				                    <ul>
				                        <li>
				                            <div>
				                                <a target="mainFrame" href="dx/dx.htm">草稿箱</a></div>
				                        </li>
				                    </ul>
				                </div>                		   			 	
		   			 </div>	
		   			 <div    data-options="title:'通话记录',iconCls: 'icon-sz'" style=" padding: 10px;">
							   			<div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="txjl/txjl.htm">全部通话</a></div>
					                        </li>
					                    </ul>
					                </div>                
					                <div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="txjl/txjl.htm">播出通话</a></div>
					                        </li>
					                    </ul>
					                </div>               
					                <div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="txjl/txjl.htm">已接来电</a></div>
					                        </li>
					                    </ul>
					                </div>               
					                <div>
					                    <ul>
					                        <li>
					                            <div>
					                                <a target="mainFrame" href="txjl/txjl.htm">未接来电</a></div>
					                        </li>
					                    </ul>
                                    </div>               
		   			 </div>	
		   			 <div    data-options="title:'浏览器管理',iconCls: 'icon-qt'" style=" padding: 10px;">
					   		<div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="llq/llq.htm">系统浏览器</a></div>
			                        </li>
			                    </ul>
			                </div>               
			                <div>
			                    <ul>
			                        <li>
			                            <div>
			                                <a target="mainFrame" href="llq/llq.htm">QQ浏览器</a></div>
			                        </li>
			                    </ul>
			                </div>	   			 	
		   			 </div>	
		   			  <div    data-options="title:'关于',iconCls: 'icon-help'" style="padding: 10px;">
						   	    <h4>
				                   预设置
				                </h4>
		   			 </div>	
		   		</div>        
	   </div>
	   
	   <div data-options="region: 'center'" style="padding: 1px;">
		        <div  id="tabs" class="easyui-tabs" data-options="fit: true, border: true">
			            <div  data-options="title: '主页', iconCls: 'icon-hamburg-home'">
			                    <div class="easyui-portal" data-options="fit: true, border: false">
			                            <div style="width: 33%;">
			                                <div data-options="title: '项目简介', height: 300, collapsible: true, closable: true"></div>	                                 
			                                <div data-options="title: '更新日志', height: 360, collapsible: true, closable: true"></div>
			                            </div>
			                            <div style="width: 34%;">
			                                <div data-options="title: '最新项目', height: 300, iniframe: true, collapsible: true, closable: true"></div>
			                                <div data-options="title: '学习资料', height: 360, collapsible: true, closable: true" style="padding: 10px;"> </div>	                             		               
			                            </div>
			                            <div style="width: 33%;">
			                                <div data-options="title: '开源项目', height: 300, closable: true, collapsible: true, closable: true" >	</div>
			                                <div data-options="title: '更多信息...', height: 360, collapsible: true, closable: true"></div>
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
		            <div data-options="region: 'center', title: '友情链接'"></div>		         
		        </div>
      </div>
	   	
	   <div data-options="region: 'south', title: '关于...', iconCls: 'icon-help', collapsed: true, border: false" style="height: 70px;"></div>
</body>
</html>