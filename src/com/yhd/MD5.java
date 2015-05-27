package com.yhd;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String getMd5XString(String content){
		
		String md5x = null;
		
		if(content == null ){
			return md5x;
		}
		
		try {
			StringBuffer sb = new StringBuffer();
			MessageDigest md = MessageDigest.getInstance("MD5");
			for(byte by : md.digest(content.getBytes("UTF-8"))){
				sb.append(String.format("%02X", by));
			}
			md5x = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return md5x;
	}

	public static void main(String[] args) throws Exception {
		String ss = "name=测试signature_method=md5timestamp=1411896964trader=iosSystem00000000";
		System.out.println(getMd5XString(ss));
	}
}
