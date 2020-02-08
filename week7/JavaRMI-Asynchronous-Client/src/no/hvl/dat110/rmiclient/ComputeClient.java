package no.hvl.dat110.rmiclient;

/**
 * For demonstration purpose in dat110 course
 */

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

import no.hvl.dat110.rmiinterface.ClientCallbackInterface;
import no.hvl.dat110.rmiinterface.ServerMainInterface;


public class ComputeClient {
	
	public static void main(String args[]) {
		
		try {
			
			Random r = new Random();
			int a = r.nextInt(100);
			int b = r.nextInt(100);
			
			// Get the registry  (you need to specify the ip address/port of the registry if you're running from a different host)
			Registry registry = LocateRegistry.getRegistry(9010);
			
			// Look up the registry for the remote ServerCallback object
			ServerMainInterface sc = (ServerMainInterface) registry.lookup(ServerMainInterface.SERVER_INAME);
			
			// register the clientcallback object with the remote servercallback object
			ClientCallbackInterface clientcallbackobj = new ClientCallbackImplement();
			
			sc.registerClientCallbackObject(clientcallbackobj);  // register a callback handler for the client on the server
				
			// hand the remote operation to this thread to wait for the result from server		
			Runnable runnable = () -> {
				try { 
					System.out.println("Computing: "+a+" + "+b);
					sc.doOperation(a, b);
				 }catch (RemoteException e) { 
					 e.printStackTrace(); 
				 } 
			};
			Thread thread = new Thread(runnable);
			thread.start();
			 
			// continue with other things until this client is notified of the result from the server
			while(!clientcallbackobj.isNotified()) {
				Thread.sleep(1000);
				System.out.println("RPC Client still busy with other things while waiting for result...");
			}
			
			System.out.println("Operation completed! Client will terminate server...");
			
			sc.shutdown();	

		} catch(Exception e) {
			System.exit(0);
		}
	}

}
