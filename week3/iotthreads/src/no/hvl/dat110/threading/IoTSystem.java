package no.hvl.dat110.threading;

public class IoTSystem {

	public static void main(String[] args) {
		
		System.out.println("System starting ... ");
		
		TemperatureMeasurement tm = new TemperatureMeasurement();
		
		TemperatureDevice tempdevice = new TemperatureDevice(tm);
		TemperatureDevice tempdevice2 = new TemperatureDevice(tm);
		TemperatureDevice tempdevice3 = new TemperatureDevice(tm);
		
		DisplayDevice disdevice = new DisplayDevice(tm);
		DisplayDevice disdevice2 = new DisplayDevice(tm);
		DisplayDevice disdevice3 = new DisplayDevice(tm);
		
		tempdevice.start();
		tempdevice2.start();
		tempdevice3.start();
		
		disdevice.start();
		disdevice2.start();
		disdevice3.start();
		
		try {
			tempdevice.join();
			tempdevice2.join();
			tempdevice3.join();
			
			disdevice.join();
			disdevice2.join();
			disdevice3.join();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("System shutting down ... ");	
		
	}

}
