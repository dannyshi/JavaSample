package com.shihua.classload;

public class Dog {
	
	static{
		System.out.println("Dog static say hello....");
	}
	
	private String name;
	
	public Dog(){};
	
	public Dog(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
