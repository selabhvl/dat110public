package no.hvl.dat110.rpcserver;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.rpcinterface.TempSensorInterface;

/**
 * dat110: DS Lab 2
 */


public class TempRPCServer {
	
	public static void main(String args[]) {
		
		TempRPCServer server = new TempRPCServer();
		server.start();
	}
	
	public void start() {
		
		try {
			// TODO
			
			// create registry and start it on a port (e.g. 9091)
			Registry registry = LocateRegistry.createRegistry(9001);
			
			// Make a new instance (stub) of the implementation class
			TempSensorImpl stub = new TempSensorImpl();
			
			// Bind the remote object (stub) in the registry using the name TempSensorInterface.REMOTE_IFACE_NAME
			registry.bind(TempSensorInterface.REMOTE_IFACE_NAME, stub);
		}catch(Exception e) {
			System.err.println("Temp RPCServer: "+e.getMessage());
			e.printStackTrace();
		}
	}

}
