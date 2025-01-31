package no.hvl.dat110.rpcserver;

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

			
			// Make a new instance (stub) of the implementation class

			
			// Bind the remote object (stub) in the registry using the name TempSensorInterface.REMOTE_IFACE_NAME

		}catch(Exception e) {
			System.err.println("Temp RPCServer: "+e.getMessage());
			e.printStackTrace();
		}
	}

}
