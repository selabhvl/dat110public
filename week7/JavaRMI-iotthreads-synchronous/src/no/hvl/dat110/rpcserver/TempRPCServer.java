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
			// create registry and start it on port 9091

			
			// Make a new instance (stub) of the implementation class

			
			// Bind the remote object (stub) in the registry

		}catch(Exception e) {
			System.err.println("Temp RPCServer: "+e.getMessage());
			e.printStackTrace();
		}
	}

}
