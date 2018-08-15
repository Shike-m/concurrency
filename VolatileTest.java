package testConcurrency;

import java.util.concurrent.TimeUnit;

public class VolatileTest {

	private volatile int i=20;
	
	public void action() {
		for(;i>0;i--) {
			System.out.println("The countdown: "+i);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("action end");
	}
	public static void main(String[] args) {
		VolatileTest v = new VolatileTest();
		 
		new Thread(v::action,"v1").start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		v.i=-1;

	}

}
