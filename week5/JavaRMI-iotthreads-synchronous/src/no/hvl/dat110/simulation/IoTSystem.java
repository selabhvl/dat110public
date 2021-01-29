package no.hvl.dat110.simulation;

import no.hvl.dat110.display.DisplayDevice;
import no.hvl.dat110.rpcserver.TempRPCServer;
import no.hvl.dat110.tempsensor.TemperatureDevice;

public class IoTSystem {

	public static void main(String[] args) {
		
		System.out.println("System starting ... ");
		
		TempRPCServer server = new TempRPCServer();
		server.start();												// start the temp rpc server
		
		TemperatureDevice tempdevice = new TemperatureDevice();		// Start the Temp device that reads sensor 
		DisplayDevice display = new DisplayDevice(); 				// Start the display device that display temp value received from the sensor
		
		tempdevice.start();
		display.start();
		try {
			tempdevice.join();
			display.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("System shutting ... ");		
		System.exit(0);
		
	}

}
