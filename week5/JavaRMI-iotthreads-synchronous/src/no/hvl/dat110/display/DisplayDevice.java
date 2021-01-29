package no.hvl.dat110.display;


public class DisplayDevice extends Thread {
		
	
	public void run() {
		
		System.out.println("Display started...");	
		
		// TODO
		
		// Get a reference to the registry using the port
		
		// Look up the registry for the remote object (TempSensorInterface) using the name TempSensorInterface.REMOTE_IFACE_NAME
		
		// loop 10 times and read the temp value from the TemperatureSensor each time
		
			// get the temperature value by calling the getTemperature remote method via the object of TempSensorInterface
			
			// print the temperature value to console
		
		throw new RuntimeException("RPC DisplayDevice Client Not yet implemented...");
		
	}
}
