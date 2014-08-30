package com.era.util;

import java.io.IOException;

public class text {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		String city = BaseUtils.getaddressforxybyGooglehttpconnection("106.338272","29.621855");
		System.out.println(city);
	}

}
