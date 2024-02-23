package no.hvl.dat110.week3.exercise4;

public class DisplayDevice extends Thread {

	private TemperatureMeasurement tm;

	private static final int COUNT = 10;

	public DisplayDevice(TemperatureMeasurement tm) {
		this.tm = tm;
	}

	public void run() {
		
		System.out.println("Display started");

		for (int i = 0; i < COUNT; i++) {

			int temp = tm.getTemperature();

			System.out.println("DISPLAY: " + temp);
		}
		System.out.println("Display stopping ... ");
	}
	
}
