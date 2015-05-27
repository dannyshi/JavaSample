package com.shihua.designmodle.observer.jdk;

import java.util.Observable;

public class NameChangeSubject extends Observable {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.setChanged();
		this.notifyObservers();
	}
	
	

}
