package com.era.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PiaoJuTong {

	/**
	    * 对文件全文生成MD5摘要
	    *
	    * @param file
	    *            要加密的文件
	    * @return MD5摘要码
	    */

	   static char hexdigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',

	        '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	   /**
		 * md5文件算法
		 * @param file
		 * @return
		 */
		public static String Md5(File file) {

			 

		      FileInputStream fis = null;

		      try {

		        MessageDigest md = MessageDigest.getInstance("MD5");

		        fis = new FileInputStream(file);

		        byte[] buffer = new byte[2048];

		        int length = -1;

		        long s = System.currentTimeMillis();

		        while ((length = fis.read(buffer)) != -1) {

		           md.update(buffer, 0, length);

		        }

		        byte[] b = md.digest();

		        return byteToHexString(b);

		        // 16位加密

		        // return buf.toString().substring(8, 24);

		      } catch (Exception ex) {

		        ex.printStackTrace();

		        return null;

		      } finally {

		        try {

		           fis.close();

		        } catch (IOException ex) {

		           ex.printStackTrace();

		        }

		      }

		   }
		
		/** */

		   /**

		    * 把byte[]数组转换成十六进制字符串表示形式

		    * @param tmp    要转换的byte[]

		    * @return 十六进制字符串表示形式

		    */

		   private static String byteToHexString(byte[] tmp) {

		      String s;

		      // 用字节表示就是 16 个字节

		      char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，

		      // 所以表示成 16 进制需要 32 个字符

		      int k = 0; // 表示转换结果中对应的字符位置

		      for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节

		        // 转换成 16 进制字符的转换

		        byte byte0 = tmp[i]; // 取第 i 个字节

		        str[k++] = hexdigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,

		        // >>> 为逻辑右移，将符号位一起右移

		        str[k++] = hexdigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换

		      }

		      s = new String(str); // 换后的结果转换为字符串

		      return s;

		   }
	   
	   
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
}