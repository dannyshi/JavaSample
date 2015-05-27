package com.shihua.designmodle.observer.jdk;

public class JdkObserverDesignModleMain {

	public static void main(String[] args) {
		NameChangeSubject s = new NameChangeSubject();
		
		NameChangeObserver o1 = new NameChangeObserver("Observer_1");
		NameChangeObserver o2 = new NameChangeObserver("Observer_2");
		NameChangeObserver o3 = new NameChangeObserver("Observer_3");
		NameChangeObserver o4 = new NameChangeObserver("Observer_4");
		NameChangeObserver o5 = new NameChangeObserver("Observer_5");
		
		s.addObserver(o1);
		s.addObserver(o2);
		s.addObserver(o3);
		s.addObserver(o4);
		s.addObserver(o5);
		
		s.setName("shihua");
		

	}

}
