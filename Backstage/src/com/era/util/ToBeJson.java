package com.era.util;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
/**
 * 转化为Json格式 （返回jquery EasyUI datagrid ）
 * 
 * @param total
 *           数据总数
 * @param list 
 *           显示的数据
 */
public class ToBeJson {
    public static void execute( List list,int total) throws Exception{  
           HttpServletResponse response = ServletActionContext.getResponse();  
           JSONObject jobj = new JSONObject();           
           jobj.accumulate("total",total );
           jobj.accumulate("rows", list); 
           response.setCharacterEncoding("utf-8"); 
           response.getWriter().write(jobj.toString());
       }
}
