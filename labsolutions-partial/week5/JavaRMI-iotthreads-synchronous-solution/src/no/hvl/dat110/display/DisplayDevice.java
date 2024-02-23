package no.hvl.dat110.display;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.rpcinterface.TempSensorInterface;

public class DisplayDevice extends Thread {
		
	
	public void run() {
		
		System.out.println("Display device started...");	
		
		try {

			// Get the registry  (you need to specify the ip address/port of the registry if you're running from a different host)
			Registry registry = LocateRegistry.getRegistry(TempSensorInterface.SERVER_PORT);
			
			// Look up the registry for the remote object
			TempSensorInterface tsensor = (TempSensorInterface) registry.lookup(TempSensorInterface.REMOTE_IFACE_NAME);
			
			// loop 10 times and 
			for(int i=0; i<10; i++) {
				try {
					String temp = tsensor.getTemperature();
					
					System.out.println("Display: [Temp] "+temp);
					
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}catch(RemoteException | NotBoundException e) {
			//
		}
		
	}
}
