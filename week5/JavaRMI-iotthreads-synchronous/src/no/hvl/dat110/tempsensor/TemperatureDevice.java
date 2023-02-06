package no.hvl.dat110.tempsensor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.rpcinterface.TempSensorInterface;

public class TemperatureDevice extends Thread {

	private TemperatureSensor sn;
	private int temp;
	
	public TemperatureDevice() {
		this.sn = new TemperatureSensor();
		this.temp = 0;
	}
	
	public void run() {
		
		System.out.println("temperature device started");
		TempSensorInterface ti = null;
		// TODO
		
		// Get a reference to the registry using the port
				
		// Look up the registry for the remote object (TempSensorInterface) using the name TempSensorInterface.REMOTE_IFACE_NAME
		
		// loop 10 times and read the temp value from the TemperatureSensor each time
		
		// set the temperature value by calling the setTemperature remote method via the object of TempSensorInterface
		Registry rmiRegistry = null;
	
		try {
			rmiRegistry = LocateRegistry.getRegistry(9001);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ti = (TempSensorInterface) rmiRegistry.lookup(TempSensorInterface.REMOTE_IFACE_NAME);
		} catch (Exception e) {
			
		}
		
		for (int i = 0; i < 10; i++) {
			try {
				temp = sn.read();
				ti.setTemperatur(temp);
			} catch(Exception e) {
				
			}
			
		}
	}
}
