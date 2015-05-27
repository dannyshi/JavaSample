package com.shihua.designmodle.observer;

public class ConcreteObserver extends Observer {
	
	private String observerName;
	
	private String event;
	
	private ConcreteSubject subject;
	
	public ConcreteObserver(ConcreteSubject subject, String name){
		this.subject = subject;
		this.observerName = name;
	}

	@Override
	public void update() {
		this.event =  this.subject.getEvent();
		System.out.println(String.format("This is observer %s and receive event %s", this.observerName, this.event));
	}

}
