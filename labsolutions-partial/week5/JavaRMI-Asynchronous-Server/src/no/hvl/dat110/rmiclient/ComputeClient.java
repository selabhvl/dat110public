package no.hvl.dat110.rmiclient;

/**
 * For demonstration purpose in dat110 course
 */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

import no.hvl.dat110.rmiinterface.ClientCallbackInterface;
import no.hvl.dat110.rmiinterface.ServerCallbackInterface;


public class ComputeClient extends Thread {
	
	public void run() {
		try {
			
			// randomize
			Random r = new Random();
			int a = r.nextInt(5000);
			int b = r.nextInt(5000);			
			
			// Get the registry  (you need to specify the ip address/port of the registry if you're running from a different host)
			Registry registry = LocateRegistry.getRegistry(9010);
			
			// Look up the registry for the remote ServerCallback object
			ServerCallbackInterface sc = (ServerCallbackInterface) registry.lookup(ServerCallbackInterface.SERVER_INAME);
			
			// register the clientcallback object with the remote servercallback object
			ClientCallbackInterface clientcallbackobj = new ClientCallbackImplement();
			
			sc.registerClientCallbackObject(clientcallbackobj); 	// remote
			sc.addNumbers(a, b);									// remote

			while(!clientcallbackobj.isNotified()) {	// keep writing until server returns result
				System.out.println("Waiting for server but doing something else...");
				Thread.sleep(2000);
			}
			
			System.out.println("Operation completed! Client will terminate...");
			System.exit(0);

		} catch(Exception e) {
			System.err.println("Error in RMI "+e.getMessage());
			e.getStackTrace();
		}
	}
}
