package com.yhd;

import java.util.Arrays;
import java.util.Map;

public class VenusSignatureGen {

	public static void main(String[] args) {
		getSignature(null,null);
	}
	
	/**
	 * 
	 * @param bizPara 业务入参map
	 * @param secretKey 签名密钥
	 * @return
	 */
	public static String getSignature(Map<String, String> bizPara, String secretKey){
		StringBuffer content = new StringBuffer();
		String[] keys = (String[])bizPara.keySet().toArray(new String[0]);
		Arrays.sort(keys);
		for(String key : keys){
			content.append(key).append("=").append(bizPara.get(key));
		}
		
		content.append(secretKey);
		
		return MD5.getMd5XString(content.toString());
	}

}
