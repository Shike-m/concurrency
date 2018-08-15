package testConcurrency;

import java.util.ArrayList;
import java.util.List;

//Ali interview question. Build a contain with two methods: add and size. 
//when size reach 5. notify the add stop.

public class AliTest {
	
	volatile List<Object> list = new ArrayList<>();
	
	public void add(Object obj) {
		list.add(obj);
	}
	
	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		
		AliTest a =new AliTest();
		
		final Object o = new Object();
		
		new Thread(()->{
				System.out.println("t1 thread start...");
				synchronized(o) {
				if(a.size()!=5) {
					try {
						o.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					}
				
				System.out.println("t1 thread end...");
				o.notify();
				}
			},"t1").start();
			
		
		
		new Thread(()->{
			synchronized(o) {
				System.out.println("t2 thread start");
				for(int i=0;i<10;i++) {
					a.add(new Object());
					System.out.println("add No. "+i);
					if(a.size()==5) {
						o.notify();
						try {
							o.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				}
				System.out.println("t2 thread end");
			
		},"t2").start();
			
		
	}

}
