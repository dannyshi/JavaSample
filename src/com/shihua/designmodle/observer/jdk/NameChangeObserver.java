package com.shihua.designmodle.observer.jdk;

import java.util.Observable;
import java.util.Observer;

public class NameChangeObserver implements Observer {
	
	private String name;
	
	public NameChangeObserver(String name){
		this.name = name;
	}

	@Override
	public void update(Observable o, Object arg) {
		NameChangeSubject s = (NameChangeSubject)o;
		System.out.println(String.format("this is observer %s and subject new name is %s", this.name, s.getName()));

	}

}
