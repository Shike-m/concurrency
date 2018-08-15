package testConcurrency;

import java.util.concurrent.TimeUnit;

public class EmbededLock {

	public synchronized void action() {
		System.out.println("action start...");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("action end...");
	}
	
	public static void main(String[] args) {
		new Thread(()->new subEmbeded().action(),"t1").start();;

	}

}

class subEmbeded extends EmbededLock{
	@Override
	public synchronized void action() {
		System.out.println("Child of action start");
		super.action();
		System.out.println("Child of action end");
	}
}
