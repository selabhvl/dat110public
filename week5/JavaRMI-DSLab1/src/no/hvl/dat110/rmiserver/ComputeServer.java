package no.hvl.dat110.rmiserver;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
/**
 * For demonstration purpose in dat110 course
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//import java.rmi.server.UnicastRemoteObject;

import no.hvl.dat110.rmiinterface.ComputeInterface;


public class ComputeServer {
	
	public static void main(String args[]) {

		
		try {
			// create registry
			Registry registry = LocateRegistry.createRegistry(9000);
			
			// Make a new instance of the implementation class
			ComputeInterface stub = new ComputeImpl();
			
			//Export the object of implementation class (remote object)
			//ComputeInterface stub = (ComputeInterface) UnicastRemoteObject.exportObject(comp1, 0);
			
			// bind the remote object (stub) in the registry			
			registry.bind("ComputeInterface", stub);
			
			System.out.println("Compute RPCServer is ready");
			
		}catch(RemoteException | AlreadyBoundException e) {
			System.err.println("Compute RPCServer: "+e.getMessage());
			e.printStackTrace();
		}
	}

}
