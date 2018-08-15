package testConcurrency;

import java.util.concurrent.TimeUnit;

public class DirtyReadTest {
	
	private String name;
	private int balance;
	
	public synchronized void set(String name,int bal) {
		this.name=name;
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.balance=bal;
	}
	
	public synchronized int getBal() {
		return balance;
	}

	public static void main(String[] args) {
		DirtyReadTest t=new DirtyReadTest();
		
		new Thread(()->t.set("Jacky",1000),"t1").start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(t.getBal());
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(t.getBal());
		

	}

}
