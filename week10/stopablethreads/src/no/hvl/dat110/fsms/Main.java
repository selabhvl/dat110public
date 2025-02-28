package no.hvl.dat110.fsms;

public class Main {

	public static int SLEEPTIME = 2000;
	
	public static void main(String[] args) {
		
		Receiver receiver = new Receiver();
		Transmitter transmitter = new Transmitter(receiver);
		
		System.out.println("Main thread - start");
		receiver.start();
		transmitter.start();
		
		try {
			
			transmitter.do_open();
			
			for (int i = 0; i<5; i++) {
				transmitter.do_send();
				Thread.sleep(SLEEPTIME); 
			}
			
			transmitter.do_close();
			
			transmitter.do_send(); // what will happen?
			
			System.out.println("Main thread - doStop");
			transmitter.doStop();
			receiver.doStop();
			
			System.out.println("Main thread - join");
			transmitter.join();	
			receiver.join();	
			
			System.out.println("Main thread - done");
			
		} catch (InterruptedException ex) {

			System.out.println("Main thread " + ex.getMessage());
			ex.printStackTrace();
		}
		
	}
}
