package no.hvl.dat110.tempsensor;


public class TemperatureDevice extends Thread {

	private TemperatureSensor sn;
	
	public TemperatureDevice() {
		this.sn = new TemperatureSensor();
	}
	
	public void run() {
		
		System.out.println("temperature device started");
		
		// Implement me
		
		throw new RuntimeException("RPC TemperatureDevice Client not yet implemented...");
		
	}
}
