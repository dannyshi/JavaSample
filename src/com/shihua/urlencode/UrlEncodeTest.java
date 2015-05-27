package com.shihua.urlencode;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlEncodeTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String c = "name我=shi&hua/;:";
		String enc = "utf-8";
		String enc2="GBK";
		String enc3 = "iso8859-1";
		String encodedString = URLEncoder.encode(c, enc3);
		System.out.println(encodedString);
		
		System.out.println(URLDecoder.decode(encodedString, enc3));

	}

}
