<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册市场</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link type="text/css" href="${pageContext.request.contextPath}/css/center.css" rel="stylesheet" />
  </head>
  
  <body>
    <br><br><!--平台-->
    <form action="../AgentSystem/System/addPlatform.action" enctype="multipart/form-data" method="post">
    <div id="all1">
         <div class="cen">
              <div class="fd"><img src="../AgentSystem/images/ss.png" width="60" height="40" /></div>
              <div class="tc"><div> 市场资料</div></div>
              <div class="fg">
                   <table>
                   		<tr>
                            <td align="right">市场名称：</td>
                            <td><input type="text" id="platformname" size="15" name="platformname"/></td>
                        </tr>
                        <tr>
                            <td align="right">市场类别：</td>
                            <td><select id="ss" name="platformtype">
                                        <option value="地方市场" selected="selected">地方市场</option>
                                        <option value="顶级市场">顶级市场</option>
                            </select></td>
                        </tr>
                        <tr>
                            <td height="21" align="right">市场年限：</td>
                            <td><select id="sele" name="platformyear">
                                        <option value="1" selected="selected">一年</option>
                                        <option value="2">三年</option>
                                        <option value="3">五年</option>
                            </select></td>
                            </tr>
                   </table>
              </div>
         </div>
         <div class="cen1">
              <div class="fd"><img src="../AgentSystem/images/ss.png" width="60" height="40" /></div>
              <div class="zl"><div>客户资料</div></div>
              <div class="mn">
                   <table border="0" cellpadding="0" cellspacing="0">
                          <tr>
                              <td align="right">客户姓名：</td>
                              <td><input type="text" id="names" size="15" name="customername" /></td>
                          </tr>
                          <tr>
                              <td align="right">联系方式：</td>
                              <td><input type="text" id="relation" size="15" name="customerphone" /></td>
                          </tr>
                          <tr>
                              <td align="right">家庭住址：</td>
                              <td><input type="text" id="address" size="15" name="customeraddress" /></td>
                          </tr>
                          <tr>
                              <td align="right">有效证件类型：</td>
                              <td><select id="ss" name="paperstype">
                                          <option value="身份证" selected="selected">身份证</option>
                                          <option value="工商营业执照">工商营业执照</option>
                                          <option value="护照">护照</option>
                                          <option value="其他">其他</option>
                              </select></td>
                          </tr>
                          <tr>
                              <td align="right">证件号码：</td>
                              <td><input type="text" id="hao" size="15" name="papersnum" /></td>
                          </tr>
                          <tr>
                              <td align="right">邮箱地址：</td>
                              <td><input type="text" id="mail" size="15" name="customeremail" /></td>
                          </tr>
                          <tr>
                              <td colspan="2" align="center"><input type="submit" id="names" value="确定" onclick="show2()" /></td>
                          </tr>
                   </table>
              </div>
         </div>
    </div>
    </form>
  </body>
</html>
