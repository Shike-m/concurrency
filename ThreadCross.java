package testConcurrency;

import java.util.ArrayList;
import java.util.List;

public class ThreadCross {
	private volatile int count=0;
	
	public synchronized void doLoop() {
		while(count<10000) {
			count++;
		}
	}

	public static void main(String[] args) {
		
		ThreadCross t = new ThreadCross();
		
		List<Thread> ts= new ArrayList<>();
		
		for(int i=0;i<10;i++) {
			ts.add(new Thread(t::doLoop,"t"+i));
		}
		
		ts.forEach(s->s.start());
		ts.forEach(s->{
			try {
				s.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		System.out.println(t.count);
	}

}
