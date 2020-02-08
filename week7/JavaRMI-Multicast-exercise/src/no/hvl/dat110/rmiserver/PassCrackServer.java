package no.hvl.dat110.rmiserver;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.rmiinterface.PassCrackInterface;

/**
 * dat110: DS-Lab 2
 * @author tdoy
 */
public class PassCrackServer {
	
	private int port;
	private String name;
	
	public PassCrackServer(int port, String name) {
		this.port = port;
		this.name = name;
	}
	
	public static void main(String args[]) {
		
		PassCrackServer server = new PassCrackServer(9002, "worker1");
		server.start();
	}
	
	public void start() {
		try {
			
			// create registry of stub object
			Registry registry = LocateRegistry.createRegistry(port);
			
			// Make a new instance of the implementation class
			PassCrackInterface stub = new PassCrackImpl();
			
			// bind the remote object (stub) in the registry
			registry.bind(name, stub);
			
			System.out.println(name + " server is running...");
		}catch(Exception e) {
			System.err.println(name + " server: "+e.getMessage());
			e.printStackTrace();
		}
	}

}
