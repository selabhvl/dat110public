package no.hvl.dat110.display;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.rpcinterface.TempSensorInterface;
import no.hvl.dat110.rpcserver.TempSensorImpl;

public class DisplayDevice extends Thread {
		
	
	public void run() {
		
		System.out.println("Display started...");	
		
		// TODO
		Registry registry = null;
		TempSensorInterface ti = null;
		// Get a reference to the registry using the port
		try {
			registry = LocateRegistry.getRegistry(9001);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Look up the registry for the remote object (TempSensorInterface) using the name TempSensorInterface.REMOTE_IFACE_NAME
		
		try {
			ti = (TempSensorInterface)registry.lookup(TempSensorInterface.REMOTE_IFACE_NAME);
		} catch (Exception e) {
			
		}
		// loop 10 times and read the temp value from the TemperatureSensor each time
		for (int i = 0; i < 10; i++) {
			// get the temperature value by calling the getTemperature remote method via the object of TempSensorInterface
			int temp = ti.getTemperatur();
			// print the temperature value to console
			System.out.println(temp);
		}
			
			
			
		
		
		
		
		
		
		
	}
}
