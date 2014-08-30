package com.era.util;

public class CommonUtil {
	
	public static String changeIos8859ToUtf8(String str) {
		if(null == str){
			return null;
		}
		try {
			boolean flag = false;
			byte[] temp = null;
			try {
				temp = str.getBytes("ISO-8859-1");
			} catch (Exception e) {
				flag = false;
			}
			int i = 0;
			for (i = 0; i < temp.length; i++) {
				if (temp[i] < 0) {
					flag = true;
					i = temp.length;
				}
			}
			
			if(flag){
				return new String(str.getBytes("ISO-8859-1"),"UTF-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return str;
	}
}
