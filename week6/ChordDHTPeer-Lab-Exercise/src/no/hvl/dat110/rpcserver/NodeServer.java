package no.hvl.dat110.rpcserver;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.rpc.interfaces.NodeInterface;

/**
 * dat110: DS Lab 2
 */


public class NodeServer {
	
	private String nodename;
	private int port;
	
	public NodeServer(String nodename, int port) {
		this.nodename = nodename;
		this.port = port;
	}
	
	public void start() {
		
		try {
			
			// create registry and start it on port 9091
			Registry registry = LocateRegistry.createRegistry(port);
			
			// Make a new instance (stub) of the implementation class
			NodeInterface stub = new Node(nodename);
			
			// Bind the remote object (stub) in the registry
			registry.bind(nodename, stub);
			System.out.println(nodename+" Server is running... ");
		}catch(Exception e) {
			System.err.println("Node Server: "+e.getMessage());
			e.printStackTrace();
		}
	}

}
