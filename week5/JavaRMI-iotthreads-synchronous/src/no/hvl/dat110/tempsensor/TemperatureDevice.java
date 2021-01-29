package no.hvl.dat110.tempsensor;


public class TemperatureDevice extends Thread {

	private TemperatureSensor sn;
	
	public TemperatureDevice() {
		this.sn = new TemperatureSensor();
	}
	
	public void run() {
		
		System.out.println("temperature device started");
		
		// TODO
		
		// Get a reference to the registry using the port
				
		// Look up the registry for the remote object (TempSensorInterface) using the name TempSensorInterface.REMOTE_IFACE_NAME
		
		// loop 10 times and read the temp value from the TemperatureSensor each time
		
		// set the temperature value by calling the setTemperature remote method via the object of TempSensorInterface
		
		throw new RuntimeException("RPC TemperatureDevice Client not yet implemented...");
		
	}
}
