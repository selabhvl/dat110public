package no.hvl.dat110.rmiserver;

/**
 * For demonstration purpose in dat110 course
 * Asynchronous RPC
 */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.rmiinterface.ServerCallbackInterface;


public class ComputeServer {
	
//	public static void main(String args[]) {
//
//		ComputeServer cs = new ComputeServer();
//		cs.start();
//
//	}
	
	public void start() {
		try {
			
			// start the registry
			Registry registry = LocateRegistry.createRegistry(9010);
					
			// Make a new instance of the implementation class/callback class
			ServerCallbackInterface servercallback = new ServerCallbackImplement();
			
			// bind the remote object (stub) in the registry
			registry.bind(ServerCallbackInterface.SERVER_INAME, servercallback);
			
			System.out.println("RPC ComputeServer is ready");
		}catch(Exception e) {
			System.err.println("ComputeServer: "+e.getMessage());
			e.printStackTrace();
		}
	}

}
