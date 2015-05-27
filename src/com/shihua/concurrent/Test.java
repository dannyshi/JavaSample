package com.shihua.concurrent;

import java.util.concurrent.ConcurrentHashMap;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConcurrentHashMap<String, String> ch = new ConcurrentHashMap<String, String>();
		ch.put("sh", "sh");
		ch.get("sh");
	}

}
