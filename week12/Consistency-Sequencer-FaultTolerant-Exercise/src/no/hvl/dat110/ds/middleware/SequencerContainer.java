package no.hvl.dat110.ds.middleware;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.ds.middleware.iface.ProcessInterface;


public class SequencerContainer {
	
	public SequencerContainer(String procName, int port) {
		try {
			// create registry and start it if not started 
			Registry registry = LocateRegistry.createRegistry(port);
			
			// Make a new instance of the implementation class
			ProcessInterface proc = new Sequencer();
			
			// bind the remote object (stub) in the registry			
			registry.bind(procName, proc);
			
			System.out.println(procName+ " process registry is running");
		}catch(Exception e) {
			System.err.println("Process Container: "+e.getMessage());
			e.printStackTrace();
		}
	}

}
