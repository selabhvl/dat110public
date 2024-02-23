package no.hvl.dat110.rpcserver;

/**
 * For demonstration purpose in dat110 course
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.rpcinterface.TempSensorInterface;


public class TempRPCServer {
	
	public static void main(String args[]) {
		
		TempRPCServer server = new TempRPCServer();
		server.start();

	}
	
	public void start() {
		
		try {
			// create registry and start it on port 9091
			Registry registry = LocateRegistry.createRegistry(TempSensorInterface.SERVER_PORT);
			
			// Make a new instance of the implementation class
			TempSensorInterface tsensor = new TempSensorImpl();
			
			// Bind the remote object (stub) in the registry
			registry.bind(TempSensorInterface.REMOTE_IFACE_NAME, tsensor);
			
			System.out.println("Temp RPCServer is running...");
		}catch(Exception e) {
			System.err.println("Temp RPCServer: "+e.getMessage());
			e.printStackTrace();
		}
	}

}
