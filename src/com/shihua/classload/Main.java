package com.shihua.classload;

import java.lang.reflect.InvocationTargetException;

public class Main {

	public static void main(String[] args) {
		String name = new String("shihua");
		ClassLoader cl = name.getClass().getClassLoader();
		System.out.println(cl);
		
		Dog dog = new Dog("dd");
		System.out.println(dog.getClass().getClassLoader());
		System.out.println(dog.getClass().getClassLoader().getParent());
		System.out.println(dog.getClass().getClassLoader().getParent().getParent());
		
		try {
			Dog dog2 = Dog.class.newInstance();
			System.out.println("dog2"+dog2);
			
			Dog dog3 = (Dog) Class.forName("com.shihua.classload.Dog").newInstance();
			System.out.println("dog3"+dog3);
			
			String dn = (String) Dog.class.getMethod("getName").invoke(dog);
			System.out.println(dn);
			
			Dog.class.getMethod("setName", String.class).invoke(dog, "newdog");
			System.out.println(dog.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
