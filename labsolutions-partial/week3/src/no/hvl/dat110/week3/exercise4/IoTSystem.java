package no.hvl.dat110.week3.exercise4;

public class IoTSystem {

	public static void main(String[] args) {
		
		System.out.println("System starting ... ");

		TemperatureMeasurement tm = new TemperatureMeasurement();

		// 4.4 - multiple temperature devices/sensors
		TemperatureDevice tempdevice1 = new TemperatureDevice(tm);
		TemperatureDevice tempdevice2 = new TemperatureDevice(tm);

		// 4.3 - multiple display threads
		DisplayDevice disdevice1 = new DisplayDevice(tm);
		DisplayDevice disdevice2 = new DisplayDevice(tm);
	
		tempdevice1.start();
		tempdevice2.start();
		
		disdevice1.start();
		disdevice2.start();
		
		try {
			tempdevice1.join();
			tempdevice2.join();
			disdevice1.join();
			disdevice2.join();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("System shutting ... ");

	}

}
