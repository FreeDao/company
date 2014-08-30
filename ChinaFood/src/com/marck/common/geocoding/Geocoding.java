package com.marck.common.geocoding;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import net.sf.json.JSONObject;

public class Geocoding {

	public static String key = "ZKZnABzs5B9yFhMd8VLgfeZj";
	
	/**
	 * 获取经纬度
	 * @param address
	 * @param lng
	 * @param lat
	 * @return
	 */
	public static boolean getLatLng(String address,Map<String, Object> map){
		String json = getResponseCode(address);
		if(null == json){
			return false;
		}
		JSONObject js = new JSONObject().fromObject(json);
		js = js.getJSONObject("result").getJSONObject("location");
		map.put("lng", js.get("lng"));
		map.put("lat", js.get("lat"));
		return true;
	}

	public static String getResponseCode(String address){
		try {
			URL url = new URL("http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak="+key);
			HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();  
			int responseCode = httpConnection.getResponseCode();  
			if (responseCode == HttpURLConnection.HTTP_OK) {  
				httpConnection.connect();  
		        InputStream inputStream = httpConnection.getInputStream();  
		        //对应的字符编码转换  
		        Reader reader = new InputStreamReader(inputStream, "UTF-8");  
		        BufferedReader bufferedReader = new BufferedReader(reader);  
		        String str = null;  
		        StringBuffer sb = new StringBuffer();  
		        while ((str = bufferedReader.readLine()) != null) {  
		            sb.append(str);  
		        }  
		        reader.close();  
		        httpConnection.disconnect();
		        return sb.toString();
			}else{
				return null;
			}  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
