package no.hvl.dat110.threading;

public class DisplayDevice extends Thread {

	private TemperatureMeasurement tm;
	
	private static final int COUNT = 10;
	
	public DisplayDevice (TemperatureMeasurement tm) {
	
		this.tm = tm;
		
	}	
	
	public void run() {
		
		System.out.println("Display device started");	
		
		for (int i = 0; i<COUNT;i++) {
			//synchronized(tm) {
				try {
					tm.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				int temp = tm.getTemperature();
				
				System.out.println("DISPLAY: " + temp);
				
				tm.notifyAll();
			//}
		}
		
		System.out.println("Display device stopping ... ");
		
	}
}
