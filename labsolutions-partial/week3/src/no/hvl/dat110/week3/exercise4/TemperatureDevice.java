package no.hvl.dat110.week3.exercise4;

import java.util.Random;

public class TemperatureDevice extends Thread {

	private TemperatureMeasurement tm;
	private TemperatureSensor sn;

	private static final int COUNT = 10;

	public TemperatureDevice(TemperatureMeasurement tm) {
		this.tm = tm;
		this.sn = new TemperatureSensor();
	}

	public void run() {

		System.out.println("Temperature device started");

		// create a bit more random behaviour between the threads
		Random random = new Random();
		
		for (int i = 0; i < COUNT; i++) {

			int temp = sn.read();
			System.out.println("READING: " + temp);

			tm.setTemperature(temp);

			try {
				Thread.sleep(1000  + random.nextInt(2000));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		System.out.println("Temperature device stopping ... ");

	}
}
