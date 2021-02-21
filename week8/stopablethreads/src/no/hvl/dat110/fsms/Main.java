package no.hvl.dat110.fsms;

public class Main {

	public static int SLEEPTIME = 2000;
	
	public static void main(String[] args) {
		
		Receiver r = new Receiver();
		Transmitter t = new Transmitter(r);
		
		System.out.println("Main thread - start");
		r.start();
		t.start();
		
		try {
			
			t.do_open();
			
			for (int i = 0; i<5; i++) {
				t.do_send();
				Thread.sleep(SLEEPTIME); 
			}
			
			t.do_close();
			
			t.do_send(); // what will happen?
			
			System.out.println("Main thread - doStop");
			t.doStop();
			r.doStop();
			
			System.out.println("Main thread - join");
			t.join();	
			r.join();	
			
			System.out.println("Main thread - done");
			
		} catch (InterruptedException ex) {

			System.out.println("Main thread " + ex.getMessage());
			ex.printStackTrace();
		}
		
	}
}
