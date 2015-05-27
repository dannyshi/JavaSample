package com.shihua.threadlock;

import java.util.ArrayList;
import java.util.List;

public class NotifyWaitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object _lock = new Object();
		List<Thread> list = new ArrayList<Thread>();
		for(int i=0; i<100; i++){
			Thread t = new Thread(new Choujiang(String.valueOf(i)));
			list.add(t);
		}
		
		synchronized (_lock) {
			
		}

	}
	
	public static class Choujiang implements Runnable{
		
		public String award;
		
		public Choujiang(){};
		
		public Choujiang(String award){
			this.award = award;
		}

		@Override
		public void run() {
			System.out.println(this.award);
		}
	}
	
	public class Num{
		int num;
		
	}

}
