package com.shihua.designmodle.observer;

public class ObserverDesignModleMain {

	public static void main(String[] args) {
		ConcreteSubject subject = new ConcreteSubject();

		ConcreteObserver o1 = new ConcreteObserver(subject, "observer_1");
		subject.addObserver(o1);
		subject.addObserver(new ConcreteObserver(subject, "observer_2"));
		subject.addObserver(new ConcreteObserver(subject, "observer_3"));
		subject.addObserver(new ConcreteObserver(subject, "observer_4"));
		subject.addObserver(new ConcreteObserver(subject, "observer_5"));
		
		subject.setEvent("hello");
		subject.notifyObservers();
		
		subject.removeObserver(o1);
		subject.setEvent("funck");
		subject.notifyObservers();

	}

}
