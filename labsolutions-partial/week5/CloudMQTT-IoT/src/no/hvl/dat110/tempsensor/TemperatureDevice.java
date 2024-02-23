package no.hvl.dat110.tempsensor;

import no.hvl.dat110.mqtt.brokerclient.MQTTPubClient;

public class TemperatureDevice extends Thread {

	private TemperatureSensor sn;
	
	public TemperatureDevice() {
		this.sn = new TemperatureSensor();
	}
	
	public void run() {
		
		System.out.println("temperature device started");
		
		// Implement
		//call MQTTPubClient (create a new instance) and make connection
		MQTTPubClient temppub = new MQTTPubClient();
		temppub.connect();
		
		// read the temp from the TemperatureSensor
		
		// use the MQTTPublClient instance object to publish the temp to the CloudMQTT Broker 
		
		// loop 10 times to read temp 
		
		int i=0;
		while(i<10) {			
			try {
				int temp = sn.read();
				System.out.println("Sensor Reading [Temp]: "+temp);
				
				// publish temp measurement to MQTT broker
				temppub.publish(String.valueOf(temp));
				
				Thread.sleep(2000);		// take measurement every 1sec
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
		
		//throw new RuntimeException("RPC TemperatureDevice Client not yet implemented...");
		
	}
	
}
