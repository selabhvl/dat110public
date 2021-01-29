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
	
	public void start() {
		try {
			
			// TODO
			// create registry where impl instance can be stored and retrieved
			
			
			// Make a new instance of the implementation class
			
			
			// bind the remote object with a name in the registry
			
			
			System.out.println(name + " server is running...");
		}catch(Exception e) {
			System.err.println(name + " server: "+e.getMessage());
			e.printStackTrace();
		}
	}

}
