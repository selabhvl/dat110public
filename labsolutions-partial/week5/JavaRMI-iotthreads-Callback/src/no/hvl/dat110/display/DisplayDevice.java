package no.hvl.dat110.display;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.rpcinterface.DisplayCallbackInterface;
//import no.hvl.dat110.rpcinterface.DisplayCallbackInterface;
import no.hvl.dat110.rpcinterface.TempSensorInterface;

public class DisplayDevice extends Thread {
		
	
	public void run() {
		
		System.out.println("Display device started...");	
		
		try {

			// Get the registry  (you need to specify the ip address/port of the registry if you're running from a different host)
			Registry registry = LocateRegistry.getRegistry(TempSensorInterface.SERVER_PORT);
			
			// Look up the registry for the remote object
			TempSensorInterface rpcsensor = (TempSensorInterface) registry.lookup(TempSensorInterface.REMOTE_IFACE_NAME);
			
			// register the clientcallback object with the remote server object
			DisplayCallbackInterface displaycallbackobj = new DisplayCallbackImpl();
			
			rpcsensor.registerDisplayCallback(displaycallbackobj);
			
			System.out.println("Display: Temp value will be displayed once notified by TempRPCServer...");
			while (!rpcsensor.isExit()) {
				try {
					
					System.out.println(".");	
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			System.out.println();
			
			// shutdown the server to close the opened port
			rpcsensor.shutdown();
			
		}catch(RemoteException | NotBoundException e) {
			//e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
	
		DisplayDevice display = new DisplayDevice();				// Start the display device that display temp value received from the sensor
		
		display.start();
		
		try {
			display.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
