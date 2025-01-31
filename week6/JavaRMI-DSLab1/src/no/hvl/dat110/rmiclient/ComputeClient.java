package no.hvl.dat110.rmiclient;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.rmiinterface.ComputeInterface;

/**
 * For demonstration purpose in dat110 course.
 * This is a simple client that uses the registry as a middleware where a client stub is generated using the
 * exposed interface provided by the server.
 * The client's stub is used to marshall the parameters (int a, int b, addNumbers), send them as a message to the server's stub, 
 * and unmarshall the result from the server which is now stored in the sum variable and written to the console.
 * Note that, the registry must know the correct port on which the rpcserver is running. In this case, port 9000
 * @author tdoy
 */

public class ComputeClient {
	
	
	public static void main(String args[]) {

		
		try {
			
			// very simple example
			int a = 25;
			int b = 60;
						
			// Get the registry  (you need to specify the ip address/port of the registry if you're running from a different host)
			Registry registry = LocateRegistry.getRegistry(9000);
			
			// Look up the registry for the remote object
			ComputeInterface ci = (ComputeInterface) registry.lookup("ComputeInterface");
			
			System.out.println("Sending: "+a+"+"+b+" to ComputeServer");
			
			int sum = ci.addNumbers(a, b);
			System.out.println("Sum of "+a+" and "+b+" = "+ sum);
			
		} catch(RemoteException | NotBoundException e) {
			System.err.println("Error in RMI "+e.getMessage());
			e.getStackTrace();
		}
	}

}
