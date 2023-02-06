package no.hvl.dat110.threading;

public class TemperatureDevice extends Thread {

	private TemperatureMeasurement tm;
	private TemperatureSensor sn;
	
	private static final int COUNT = 10;
	
	public TemperatureDevice(TemperatureMeasurement tm) {
		this.tm = tm;
		this.sn = new TemperatureSensor();
	}
	
	public void run() {
		
		System.out.println("temperature device started");
		
		for (int i = 0; i<COUNT;i++) {
			
			int temp = sn.read();
			System.out.println("READING: " + temp);
			
			tm.setTemperature(temp);
			
			tm.notifyAll();
		}
		System.out.println("Temperature device stopping ... ");
	}
}
