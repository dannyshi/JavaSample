package com;

import java.util.Properties;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getModule("/pass"));
		System.out.println(isModulePath("/a/"));
	}
	
	public static String getModule(String data) {
        int secondSlashIndex = data.indexOf("/", 1);
        return secondSlashIndex == -1 ? data : data.substring(0, secondSlashIndex);
    }
	
	public static boolean isModulePath(String s) {
        return s.indexOf("/", 1) == s.length() - 1;
    }

}
