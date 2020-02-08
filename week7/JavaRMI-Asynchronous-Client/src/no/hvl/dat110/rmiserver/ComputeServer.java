package no.hvl.dat110.rmiserver;

/**
 * For demonstration purpose in dat110 course
 * Asynchronous RPC
 */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.rmiinterface.ServerMainInterface;


public class ComputeServer {
	
	public static void main(String args[]) {

		
		try {
			
			// start the registry
			Registry registry = LocateRegistry.createRegistry(9010);
					
			// Make a new instance of the implementation class/callback class
			ServerMainInterface servercallback = new ServerMainImplement();
			
			// bind the remote object (stub) in the registry
			registry.bind(ServerMainInterface.SERVER_INAME, servercallback);
			
			System.out.println("RPC ComputeServer is running...");
		}catch(Exception e) {
			System.err.println("ComputeServer: "+e.getMessage());
			e.printStackTrace();
		}
	}

}
