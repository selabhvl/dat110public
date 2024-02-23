package no.hvl.dat110.tempsensor;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.rpcinterface.TempSensorInterface;

/**
 * dat110 DS: lab2
 * @author tdoy
 *
 */
public class TemperatureDevice extends Thread {

	private TemperatureSensor sn;
	
	public TemperatureDevice() {
		this.sn = new TemperatureSensor();
	}
	
	public void run() {
		
		System.out.println("Temp device started...");
		try {
			// Get the registry  (you need to specify the ip address/port of the registry if you're running from a different host)
			Registry registry = LocateRegistry.getRegistry(TempSensorInterface.SERVER_PORT);
					
			// Look up the registry for the remote object
			TempSensorInterface tsensor = (TempSensorInterface) registry.lookup(TempSensorInterface.REMOTE_IFACE_NAME);
			
			for(int i=0; i<5; i++) {		
				try {
					Thread.sleep(2000);								// take measurement every 2sec
					int temp = sn.read();							// read temp value by calling the read() method in TemperatureSensor
					System.out.println("Temp: "+temp);
					tsensor.putTemperature(String.valueOf(temp));  	// put temp measurement. 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			tsensor.setExit(true);									// inform that operation is finished, server can shutdown
		
		} catch(RemoteException | NotBoundException e) {
			//e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		TemperatureDevice tempdevice = new TemperatureDevice();		// Start the Temp device that reads sensor
		
		tempdevice.start();
		
		try {
			tempdevice.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
