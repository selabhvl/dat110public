package no.hvl.dat110.stopablethreads;

public class StopableExampleMain {

	private static int RUNNINGTIME = 5000;
	
	public static void main(String[] args) {
		
		System.out.println("main thread starting");
		
		StopableExample t = new StopableExample();
		
		t.start();
		
		try {
			
			Thread.sleep(RUNNINGTIME); 
			
			System.out.println("main thread - doStop");
			t.doStop();
			
			System.out.println("main thread - join");
			t.join();	
			
		} catch (InterruptedException ex) {

			System.out.println("Main thread " + ex.getMessage());
			ex.printStackTrace();
		}
		
		System.out.println("main thread stopping");
	}

}
