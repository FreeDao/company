package com.gre.common;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonUtil {

	/**
	 * MD5加密算法
	 * @param plainText 要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String Md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return plainText;
	}
	
	 /**
	  * 判断是否为汉字，如果不是汉字则进行转码
	  * @param str
	  * @return
	  */
	public static String changeIos8859ToUtf8(String str) {
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
	
	
	public static void main(String[] args) {
		File f = new File("http://localhost:8080/Greening/uploadImg/zw1.jpg");
		try {
			f.createNewFile();
		} catch (IOException e) {
			System.out.println("error");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(f.exists());
	}
	
}
