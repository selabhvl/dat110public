package no.hvl.dat110.tempsensor;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.rpcinterface.TempSensorInterface;

public class TemperatureDevice extends Thread {

	private TemperatureSensor sn;
	
	public TemperatureDevice() {
		this.sn = new TemperatureSensor();
	}
	
	public void run() {
		
		System.out.println("Temperature device started...");
		try {
			// Get the registry  (you need to specify the ip address/port of the registry if you're running from a different host)
			Registry registry = LocateRegistry.getRegistry(TempSensorInterface.SERVER_PORT);
					
			// Look up the registry for the remote object
			TempSensorInterface tsensor = (TempSensorInterface) registry.lookup(TempSensorInterface.REMOTE_IFACE_NAME);
			
			// loop 10 times
			for(int i=0; i<10; i++) {			
				try {
					int temp = sn.read();
					//System.out.println("Sensor Reading: "+temp);
					tsensor.setTemperature(String.valueOf(temp));  	// add temp measurement to remote queue
					Thread.sleep(1000);								// take measurement every 1sec
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		} catch(RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
	}
}
