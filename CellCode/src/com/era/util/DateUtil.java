package com.era.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

public class DateUtil {

	/**
	 * 将传入的String类型的时�?YYYY-MM-dd)转换成date类型并返�?
	 * @return
	 */
	public static Date StringToDate(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			Date time = sdf.parse(date);
			return time;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 将传入的Date类型的时�?YYYY-MM-dd)转换成String类型并返�?
	 * @return
	 */
	public static String DateToString(Date date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			String time = sdf.format(date);
			return time;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * 获取当前时间，返回字符串类型
	 * @return
	 */
	public static String getNowString(String format) {
		try {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String time = sdf.format(now);
			return time;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 获取当前时间，返回Date类型
	 * @return
	 */
	public static Date getNowDate(String format){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String temp = getNowString(format);
			return sdf.parse(temp);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	 /**   
     * @param date1 �?��比较的时�?不能为空(null),�?��正确的日期格�?  
     * @param date2 被比较的时间  为空(null)则为当前时间   
     * @param stype 返回值类�?  0为多少天�?为多少个月，2为多少年   
     * @return   
     */  
    public static int compareDate(Date date1,Date date2,int stype){   
        int n = 0;   
           
        date2 = date2==null?getNowDate("yyyy-MM-dd"):date2;   
           
        Calendar c1 = Calendar.getInstance();   
        Calendar c2 = Calendar.getInstance();   
        try {   
            c1.setTime(date1);   
            c2.setTime(date2);   
        } catch (Exception e3) {   
            System.out.println("wrong occured");   
        }   
        //List list = new ArrayList();   
        while (!c1.after(c2)) {                     // 循环对比，直到相等，n 就是�?��的结�?  
            //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组�?打印出来   
            n++;   
            if(stype==1){   
                c1.add(Calendar.MONTH, 1);          // 比较月份，月�?1   
            }   
            else{   
                c1.add(Calendar.DATE, 1);           // 比较天数，日�?1   
            }   
        }   
           
        n = n-1;   
           
        if(stype==2){   
            n = (int)n/365;   
        }      
           
        return n;   
    }   
       
    public static String getNowString(){
    	Calendar c=Calendar.getInstance();
    	int year = c.get(Calendar.YEAR);
    	int month = c.get(Calendar.MONTH)+1;
    	int day = c.get(Calendar.DATE);
    	int hour = c.get(Calendar.HOUR);
    	int minute = c.get(Calendar.MINUTE);
    	int second = c.get(Calendar.SECOND);
    	int millsecond = c.get(Calendar.MILLISECOND);
		return year+""+month+day+hour+minute+second+millsecond;
    }
    
    /**
     * 比较两个时间相差分钟数
     * @param data1 前面的时间
     * @param data2 后面的时间
     * @return
     */
    public static Long getDateMinute(String data1,String data2){
    	try {

        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date d1 = sdf.parse(data1);
        	Date d2 = sdf.parse(data2);
        	
        	return (d2.getTime() - d1.getTime())/1000/60;
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return (long) 100;
    }
    
    /**
     * 比较两个时间相差分钟数
     * @param data1 前面的时间
     * @param data2 后面的时间
     * @return
     */
    public static String getTenMinute(){
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date d1 = new Date(new Date().getTime() - 600*1000);
    	
    	return sdf.format(d1);
    }
    
    public static void main(String[] args) throws Exception {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date d1 = new Date(new Date().getTime() - 600*1000);
    	
    	System.out.println(sdf.format(d1));
    	
    	
	}
    
}
